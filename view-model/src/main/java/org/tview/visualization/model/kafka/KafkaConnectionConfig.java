package org.tview.visualization.model.kafka;

import java.util.Objects;
import org.tview.visualization.model.label.AbsConfig;

/** kafka 连接配置 */
public class KafkaConnectionConfig extends AbsConfig {

  /** kafka 地址: ip:port */
  private String brokerConnect;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KafkaConnectionConfig that = (KafkaConnectionConfig) o;
    return Objects.equals(brokerConnect, that.brokerConnect);
  }

  @Override
  public int hashCode() {
    return Objects.hash(brokerConnect);
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

  public KafkaConnectionConfig() {}

  public String getBrokerConnect() {
    return brokerConnect;
  }

  public void setBrokerConnect(String brokerConnect) {
    this.brokerConnect = brokerConnect;
  }
}
