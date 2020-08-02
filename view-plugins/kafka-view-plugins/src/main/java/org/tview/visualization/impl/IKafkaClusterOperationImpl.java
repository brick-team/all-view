package org.tview.visualization.impl;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tview.visualization.factory.KafkaConnectionFactory;
import org.tview.visualization.factory.KafkaConnectionFactoryImpl;
import org.tview.visualization.inter.kafka.IKafkaClusterOperation;
import org.tview.visualization.model.kafka.ClusterDescription;
import org.tview.visualization.model.kafka.KafkaConnectionConfig;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

public class IKafkaClusterOperationImpl implements IKafkaClusterOperation {
  private static final Logger LOG = LoggerFactory.getLogger(IKafkaClusterOperationImpl.class);
  KafkaConnectionFactory factory = new KafkaConnectionFactoryImpl();
  /**
   * kafka 集群信息
   *
   * @return
   */
  @Override
  public ClusterDescription description(KafkaConnectionConfig kafkaConnectionConfig) {
    AdminClient adminClient = factory.factoryAdminClient(kafkaConnectionConfig);
    DescribeClusterResult describeClusterResult = adminClient.describeCluster();
    try {
      Collection<Node> nodes = describeClusterResult.nodes().get();
      KafkaFuture<Node> controller = describeClusterResult.controller();
      String clusterId = describeClusterResult.clusterId().get();

      ClusterDescription clusterDescription = new ClusterDescription();
      clusterDescription.setClusterId(clusterId);
      clusterDescription.setController(controller.get());
      clusterDescription.setNodes(nodes);
      return clusterDescription;
    } catch (InterruptedException | ExecutionException e) {
      LOG.error("获取卡夫卡集群失败", e);
    }
    return null;
  }
}
