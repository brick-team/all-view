package org.tview.visualization.inter.zk;

import java.util.Map;
import org.tview.visualization.model.zk.ZookeeperState;

/**
 * zookeeper 状态接口
 *
 * @see ZookeeperState
 */
public interface ZookeeperStateService {
  /**
   * 获取zookeeper的状态
   *
   * @param host IP地址
   * @param port 端口
   * @return 状态
   */
  ZookeeperState srvr(String host, int port) throws Exception;

  Map<String, String> mntr(String host, int port) throws Exception;

  Map<String, String> conf(String host, int port) throws Exception;

    Map<String, String> envi(String host, int port) throws Exception;
}
