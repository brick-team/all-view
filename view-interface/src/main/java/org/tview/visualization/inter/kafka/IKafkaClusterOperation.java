package org.tview.visualization.inter.kafka;

import org.tview.visualization.model.kafka.ClusterDescription;
import org.tview.visualization.model.kafka.KafkaConnectionConfig;

/** 集群操作 */
public interface IKafkaClusterOperation {
  /**
   * kafka 集群信息
   *
   * @return
   */
  ClusterDescription description(KafkaConnectionConfig kafkaConnectionConfig);
}
