package org.tview.visualization.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tview.visualization.factory.KafkaConnectionFactory;
import org.tview.visualization.factory.KafkaConnectionFactoryImpl;
import org.tview.visualization.inter.kafka.IKafkaMessageOperation;
import org.tview.visualization.model.kafka.KafkaConnectionConfig;
import org.tview.visualization.serializer.Deserializers;
import org.tview.visualization.serializer.MessageDeserializer;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class IKafkaMessageOperationImpl implements IKafkaMessageOperation {
  private static final Logger LOG = LoggerFactory.getLogger(IKafkaMessageOperationImpl.class);
  KafkaConnectionFactory factory = new KafkaConnectionFactoryImpl();

  @Override
  public boolean sendMessage(
      String topic, String key, String value, KafkaConnectionConfig kafkaConnectionConfig) {
    KafkaProducer<byte[], byte[]> kafkaProducer = factory.factoryProducer(kafkaConnectionConfig);
    Future<RecordMetadata> send =
        kafkaProducer.send(new ProducerRecord<>(topic, key.getBytes(), value.getBytes()));
    try {
      send.get();
      LOG.info("发送消息成功 topic = [{}] key = [{}] value = [{}]", topic, key, value);
      return true;
    } catch (InterruptedException | ExecutionException e) {
      LOG.error("消息发送失败", e);
    }
    return false;
  }

  @Override
  public List<ConsumerRecord> getMessage(
      String topicName, Deserializers deserializer, KafkaConnectionConfig kafkaConnectionConfig) {
    KafkaConsumer<byte[], byte[]> kafkaConsumer = factory.factoryConsumer(kafkaConnectionConfig);
    // default all
    int count = Integer.MIN_VALUE;

    List<PartitionInfo> partitionInfos = kafkaConsumer.partitionsFor(topicName);
    List<TopicPartition> partitions =
        partitionInfos.stream()
            .map(
                partitionInfo ->
                    new TopicPartition(partitionInfo.topic(), partitionInfo.partition()))
            .collect(Collectors.toList());

    // 指定消费的分区
    kafkaConsumer.assign(partitions);

    // key:topic value: size
    Map<TopicPartition, Long> topicPartitionLongMap = kafkaConsumer.endOffsets(partitions);

    for (TopicPartition partition : partitions) {
      long offset = Math.max(0, topicPartitionLongMap.get(partition) - 1);
      kafkaConsumer.seek(partition, Math.max(0, offset - count));
    }

    int totalCount = count * partitions.size();
    Map<TopicPartition, ArrayList<ConsumerRecord<byte[], byte[]>>> rawRecords =
        partitions.stream().collect(Collectors.toMap(p -> p, p -> new ArrayList(count)));

    boolean moreRecords = true;
    // loop for records data
    while (rawRecords.size() < totalCount && moreRecords) {
      ConsumerRecords<byte[], byte[]> poll = kafkaConsumer.poll(Duration.ofMillis(300));
      moreRecords = false;
      for (TopicPartition partition : poll.partitions()) {
        List<ConsumerRecord<byte[], byte[]>> records = poll.records(partition);
        if (!records.isEmpty()) {
          rawRecords.get(partition).addAll(records);
          moreRecords =
              records.get(records.size() - 1).offset() < topicPartitionLongMap.get(partition) - 1;
        }
      }
    }
    return rawRecords.values().stream()
        .flatMap(Collection::stream)
        .map(
            r ->
                new ConsumerRecord(
                    r.topic(),
                    r.partition(),
                    r.offset(),
                    r.timestamp(),
                    r.timestampType(),
                    0l,
                    r.serializedKeySize(),
                    r.serializedValueSize(),
                    deserialize(deserializer.getKeyDeserializer(), r.key()),
                    deserialize(deserializer.getValueDeserializer(), r.value()),
                    r.headers(),
                    r.leaderEpoch()))
        .collect(Collectors.toList());
  }

  private String deserialize(MessageDeserializer deserializer, byte[] value) {
    return value != null ? deserializer.deserializeMessage(ByteBuffer.wrap(value)) : "";
  }
}
