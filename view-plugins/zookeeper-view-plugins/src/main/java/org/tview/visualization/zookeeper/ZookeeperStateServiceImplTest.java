package org.tview.visualization.zookeeper;

import org.tview.visualization.inter.zk.ZookeeperStateService;

import java.util.Map;

class ZookeeperStateServiceImplTest {

  ZookeeperStateService zookeeperStateService = new ZookeeperStateServiceImpl();

  @org.junit.jupiter.api.Test
  void mntr() throws Exception {
    Map<String, String> mntr = zookeeperStateService.mntr("localhost", 2181);
    mntr.forEach(
        (k, v) -> {
          System.out.println(k + "\t" + v);
        });
  }

}
