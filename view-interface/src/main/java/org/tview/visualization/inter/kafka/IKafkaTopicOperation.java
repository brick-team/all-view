package org.tview.visualization.inter.kafka;

import java.util.Map;
import org.tview.visualization.model.kafka.CreateTopicParam;
import org.tview.visualization.model.kafka.KafkaConnectionConfig;
import org.tview.visualization.model.kafka.topic.TopicVO;

public interface IKafkaTopicOperation {

  /**
   * 创建topic
   *
   * @param createTopicParam 创建topic的请求参数
   * @return true 成功, false 失败
   */
  boolean createTopic(CreateTopicParam createTopicParam, KafkaConnectionConfig kafkaConnectionConfig);

  /**
   * 删除 topic
   *
   * @param topic topic 名称
   * @return true 成功, false 失败
   */
  boolean deleteTopic(String topic, KafkaConnectionConfig kafkaConnectionConfig);

  /**
   * 获取topic信息
   *
   * @return key: topic name , value: topic 信息
   */
  Map<String, TopicVO> getTopics(KafkaConnectionConfig kafkaConnectionConfig);

  /**
   * 获取topic信息
   *
   * @param topic topic名称
   * @return topic 信息
   */
  TopicVO topicInfo(String topic, KafkaConnectionConfig kafkaConnectionConfig);
}
