package org.tview.visualization.factory;

import java.util.Arrays;
import java.util.Collections;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.config.ConfigResource;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KafkaConnectionFactoryImplTest extends AbsKafkaConfig {

  KafkaConnectionFactory factory = new KafkaConnectionFactoryImpl();

  @DisplayName("test_admin_client")
  @Test
  void factoryAdminClient() {
    AdminClient adminClient = factory.factoryAdminClient(this.config());
    AdminClient adminClient1 = factory.factoryAdminClient(this.config());
    Assert.assertEquals(adminClient, adminClient1);
    adminClient.close();

    Assert.assertEquals(adminClient, adminClient1);
    Assert.assertEquals(adminClient, factory.factoryAdminClient(this.config()));

  }

  @Test
  @DisplayName("test_consumer")
  void factoryConsumer() {
    KafkaConsumer<byte[], byte[]> kafkaConsumer = factory.factoryConsumer(this.config());
    KafkaConsumer<byte[], byte[]> kafkaConsumer1 = factory.factoryConsumer(this.config());
    Assert.assertEquals(kafkaConsumer, kafkaConsumer1);

  }

  @Test
  @DisplayName("test_producer")
  void factoryProducer() {
    KafkaProducer<byte[], byte[]> kafkaProducer = factory.factoryProducer(this.config());
    KafkaProducer<byte[], byte[]> kafkaProducer1 = factory.factoryProducer(this.config());
    Assert.assertEquals(kafkaProducer, kafkaProducer1);
  }
}
