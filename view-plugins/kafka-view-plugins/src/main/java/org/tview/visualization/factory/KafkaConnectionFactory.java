package org.tview.visualization.factory;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.tview.visualization.model.kafka.KafkaConnectionConfig;

public interface KafkaConnectionFactory {

  AdminClient factoryAdminClient(KafkaConnectionConfig kafkaConnectionConfig);

  KafkaConsumer<byte[], byte[]> factoryConsumer(KafkaConnectionConfig kafkaConnectionConfig);

  KafkaProducer<byte[], byte[]> factoryProducer(KafkaConnectionConfig kafkaConnectionConfig);
}
