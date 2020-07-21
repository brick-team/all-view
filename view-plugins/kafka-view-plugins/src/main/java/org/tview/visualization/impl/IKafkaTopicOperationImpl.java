package org.tview.visualization.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsOptions;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.PartitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tview.visualization.factory.KafkaConnectionFactory;
import org.tview.visualization.factory.KafkaConnectionFactoryImpl;
import org.tview.visualization.inter.kafka.IKafkaTopicOperation;
import org.tview.visualization.model.kafka.CreateTopicParam;
import org.tview.visualization.model.kafka.KafkaConnectionConfig;
import org.tview.visualization.model.kafka.topic.TopicPartitionVO;
import org.tview.visualization.model.kafka.topic.TopicVO;
import org.tview.visualization.singlet.KafkaSinglet;

public class IKafkaTopicOperationImpl implements IKafkaTopicOperation {

  private static final Logger LOG = LoggerFactory.getLogger(IKafkaTopicOperationImpl.class);
  KafkaConnectionFactory factory = new KafkaConnectionFactoryImpl();

  /**
   * 创建topic
   *
   * @param createTopicParam      创建topic的请求参数
   * @param kafkaConnectionConfig
   * @return true 成功, false 失败
   */
  @Override
  public boolean createTopic(CreateTopicParam createTopicParam,
      KafkaConnectionConfig kafkaConnectionConfig) {
    NewTopic newTopic = new NewTopic(createTopicParam.getName(),
        createTopicParam.getPartitionsNum(),
        createTopicParam.getReplicationFactor());
    AdminClient adminClient = factory.factoryAdminClient(kafkaConnectionConfig);
    CreateTopicsResult topics = adminClient.createTopics(List.of(newTopic));
    boolean flg = false;

    try {
      topics.all().get();
      flg = true;
      LOG.info("Topic name=[{}] 创建成功.", newTopic.name());
    } catch (InterruptedException | ExecutionException e) {
      LOG.error("Topic name=[{}] 创建失败, {}", newTopic.name(), e);
    }

    return flg;
  }

  /**
   * 删除 topic
   *
   * @param topic                 topic 名称
   * @param kafkaConnectionConfig
   * @return true 成功, false 失败
   */
  @Override
  public boolean deleteTopic(String topic, KafkaConnectionConfig kafkaConnectionConfig) {
    DeleteTopicsOptions deleteTopicsOptions = new DeleteTopicsOptions();
    deleteTopicsOptions.timeoutMs(5000);
    AdminClient adminClient = factory.factoryAdminClient(kafkaConnectionConfig);
    DeleteTopicsResult deleteTopicsResult = adminClient
        .deleteTopics(List.of(topic), deleteTopicsOptions);
    boolean flg = false;
    try {
      deleteTopicsResult.all().get();
      LOG.info("Topic name=[{}] 删除成功.", topic);
      flg = true;
    } catch (InterruptedException | ExecutionException e) {
      LOG.error("Topic name=[{}] 删除失败. {}", topic, e);

    }
    return flg;
  }

  /**
   * 获取topic信息
   *
   * @param kafkaConnectionConfig
   * @return key: topic name , value: topic 信息
   */
  @Override
  public Map<String, TopicVO> getTopics(KafkaConnectionConfig kafkaConnectionConfig) {
    KafkaConsumer<byte[], byte[]> kafkaConsumer = factory.factoryConsumer(kafkaConnectionConfig);
    Set<String> topicSet = kafkaConsumer.listTopics().keySet();
    Map<String, TopicVO> result = new HashMap<>(topicSet.size());
    for (String topic : topicSet) {
      TopicVO topicInfo = topicInfo(topic, kafkaConnectionConfig);
      result.put(topic, topicInfo);
    }
    return result;

  }

  /**
   * 获取topic信息
   *
   * @param topic                 topic名称
   * @param kafkaConnectionConfig
   * @return topic 信息
   */
  @Override
  public TopicVO topicInfo(String topic, KafkaConnectionConfig kafkaConnectionConfig) {
    KafkaConsumer<byte[], byte[]> kafkaConsumer = factory.factoryConsumer(kafkaConnectionConfig);
    List<PartitionInfo> partitionInfos = kafkaConsumer.partitionsFor(topic);
    TopicVO topicVO = new TopicVO();
    topicVO.setName(topic);

    Map<Integer, TopicPartitionVO> partitions = new TreeMap<>();

    for (PartitionInfo partitionInfo : partitionInfos) {
      int partition = partitionInfo.partition();
      TopicPartitionVO topicPartitionVO = new TopicPartitionVO();
      topicPartitionVO.setId(partition);

      Set<Integer> inSyncReplicas = Arrays.stream(partitionInfo.inSyncReplicas())
          .map(Node::id)
          .collect(Collectors.toSet());
      Set<Integer> offlineReplicas = Arrays.stream(partitionInfo.offlineReplicas())
          .map(Node::id)
          .collect(Collectors.toSet());

      for (Node replica : partitionInfo.replicas()) {
        boolean isInSync = inSyncReplicas.contains(replica.id());
        boolean isInOffline = offlineReplicas.contains(replica.id());

        topicPartitionVO.addReplica(new TopicPartitionVO.PartitionReplica(
            replica.id(), isInSync, false, isInOffline)
        );
      }

      Node leader = partitionInfo.leader();
      if (leader != null) {
        topicPartitionVO
            .addReplica(new TopicPartitionVO.PartitionReplica(leader.id(), true, true, false));
      }
      partitions.put(partitionInfo.partition(), topicPartitionVO);
    }
    topicVO.setPartitionVOMap(partitions);
    return topicVO;
  }
}
