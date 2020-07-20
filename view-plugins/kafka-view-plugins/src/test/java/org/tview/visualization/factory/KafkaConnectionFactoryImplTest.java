package org.tview.visualization.factory;

import org.apache.kafka.clients.admin.AdminClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KafkaConnectionFactoryImplTest extends AbsKafkaConfig {

  KafkaConnectionFactory factory = new KafkaConnectionFactoryImpl();

  @DisplayName("org.tview.visualization.factory.KafkaConnectionFactoryImpl.factoryAdminClient")
  @Test
  void factoryAdminClient() {
    AdminClient adminClient = factory.factoryAdminClient(this.config());
    AdminClient adminClient1 = factory.factoryAdminClient(this.config());
    System.out.println();
  }

  void factoryConsumer() {
    factory.factoryConsumer(this.config());
  }

  void factoryProducer() {
    factory.factoryProducer(this.config());
  }
}
