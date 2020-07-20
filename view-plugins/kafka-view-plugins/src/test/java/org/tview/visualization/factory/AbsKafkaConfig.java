package org.tview.visualization.factory;

import org.tview.visualization.model.kafka.KafkaConnectionConfig;

public class AbsKafkaConfig {

  public KafkaConnectionConfig config() {
    KafkaConnectionConfig kafkaConnectionConfig = new KafkaConnectionConfig();
    kafkaConnectionConfig.setBrokerConnect("localhost:123");
    return kafkaConnectionConfig;
  }
}
