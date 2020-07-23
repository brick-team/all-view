package org.tview.visualization.zookeeper;

import java.util.Map;
import org.tview.visualization.inter.zk.ZkPerformanceOperation;
import org.tview.visualization.inter.zk.ZookeeperStateService;
import org.tview.visualization.model.zk.ZkPerformance;

public class ZookeeperPerformance implements ZkPerformanceOperation {

  ZookeeperStateService zookeeperStateService = new ZookeeperStateServiceImpl();

  @Override
  public ZkPerformance performance(String host, int port) throws Exception {
    Map<String, String> mntr = zookeeperStateService.mntr(host, port);
    ZkPerformance zkPerformance = new ZkPerformance();
    zkPerformance.setZk_max_latency(mntr.get("zk_max_latency"));
    zkPerformance.setZk_outstanding_requests(mntr.get("zk_outstanding_requests"));
    zkPerformance.setZk_packets_received(mntr.get("zk_packets_received"));
    zkPerformance.setZk_packets_sent(mntr.get("zk_packets_sent"));
    return zkPerformance;
  }
}
