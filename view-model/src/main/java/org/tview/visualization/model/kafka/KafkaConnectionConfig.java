package org.tview.visualization.model.kafka;

import org.tview.visualization.model.label.AbsConfig;

/**
 * kafka 连接配置
 */
public class KafkaConnectionConfig extends AbsConfig {

  /**
   * kafka 地址: ip:port
   */
  private String brokerConnect;


  public KafkaConnectionConfig() {
  }

  @Override
  public String toString() {
    return "{\"KafkaConnectionConfig\":{"
        + "\"brokerConnect\":\""
        + brokerConnect
        + '\"'
        + "},\"super-KafkaConnectionConfig\":"
        + super.toString()
        + "}";
  }

  public String getBrokerConnect() {
    return brokerConnect;
  }

  public void setBrokerConnect(String brokerConnect) {
    this.brokerConnect = brokerConnect;
  }
}
