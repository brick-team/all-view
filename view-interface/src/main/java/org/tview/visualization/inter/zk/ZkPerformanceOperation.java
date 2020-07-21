package org.tview.visualization.inter.zk;

import org.tview.visualization.model.zk.ZkPerformance;

public interface ZkPerformanceOperation {

  ZkPerformance performance(String host, int port) throws Exception;
}
