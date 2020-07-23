package org.tview.visualization.zookeeper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.tview.visualization.inter.zk.ZookeeperStateService;

class ZookeeperStateServiceImplTest {

  ZookeeperStateService zookeeperStateService = new ZookeeperStateServiceImpl();

  @org.junit.jupiter.api.Test
  void envi() {
  }

  @org.junit.jupiter.api.Test
  void conf() {
  }

  @org.junit.jupiter.api.Test
  void mntr() throws Exception {
    Map<String, String> mntr = zookeeperStateService.mntr("localhost", 2181);
    mntr.forEach(
        (k, v) -> {
          System.out.println(k + "\t" + v);
        }
    );
  }

  @org.junit.jupiter.api.Test
  void srvr() {
  }
}