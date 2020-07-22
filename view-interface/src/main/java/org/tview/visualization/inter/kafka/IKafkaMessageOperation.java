package org.tview.visualization.inter.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.tview.visualization.model.kafka.KafkaConnectionConfig;
import org.tview.visualization.serializer.Deserializers;

public interface IKafkaMessageOperation {
    boolean sendMessage(String topic, String key, String value, KafkaConnectionConfig kafkaConnectionConfig);

    List<ConsumerRecord> getMessage(String topicName, Deserializers deserializer, KafkaConnectionConfig kafkaConnectionConfig);
}
