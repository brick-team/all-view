package org.tview.visualization.inter.zk;

import org.tview.visualization.model.zk.ZkNodeCreateParam;
import org.tview.visualization.model.zk.ZkNodeInfo;
import org.tview.visualization.model.zk.ZkNodeTree;
import org.tview.visualization.model.zk.ZkNodeUpdateParam;

/** zookeeper node 操作接口 */
public interface ZkNodeOperation {
  /**
   * 创建 node
   *
   * @param createParam 创建参数
   * @param hostPort zk的ip+port
   * @return true: 创建成功, false 创建失败
   */
  boolean createNode(ZkNodeCreateParam createParam, String hostPort);

  /**
   * 查看节点信息
   *
   * @param path 节点
   * @param hostPort zk的ip+port
   * @return 单个节点的信息
   */
  ZkNodeInfo getNode(String path, String hostPort);

  /**
   * 获取整个zookeeper的节点数据,树结构
   *
   * @param hostPort zk的ip+port
   * @return zk 节点数据结构
   * @throws Exception zookeeper 客户端异常
   */
  ZkNodeTree tree(String hostPort) throws Exception;

  /**
   * 更新zookeeper节点信息
   *
   * @param updateNodeReq 更新的参数
   * @param hostPort zk的ip+port
   * @return true 成功, false 失败
   */
  boolean update(ZkNodeUpdateParam updateNodeReq, String hostPort);

  /**
   * 删除节点
   *
   * @param path 节点地址
   * @param hostPort zk的ip+port
   * @return true 成功, false 失败
   */
  boolean del(String path, String hostPort);
}
