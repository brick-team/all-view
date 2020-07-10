package org.tview.visualization.inter.zk;

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
     *
     * @return 状态
     */
    ZookeeperState getState(String host, int port) throws Exception;
}
