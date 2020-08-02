package org.tview.visualization.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tview.visualization.inter.zk.ZkNodeOperation;
import org.tview.visualization.model.zk.*;

import java.util.ArrayList;
import java.util.List;

/** zookeeper 节点操作实现接口 */
public class ZookeeperNodeOperationImpl implements ZkNodeOperation {
  protected Logger log = LoggerFactory.getLogger(ZookeeperNodeOperationImpl.class);

  /**
   * 计算子节点
   *
   * @param pathList 节点列表
   * @param path 节点根
   * @return 子节点列表
   */
  private static List<ZkNodeTree> child(List<String> pathList, String path) {
    List<ZkNodeTree> zkNodeTrees = new ArrayList<>();

    for (String string : pathList) {
      if (path.equals("/")) {
        zkNodeTrees.add(new ZkNodeTree(path + string, string, null));
      } else {

        ZkNodeTree hc1 = new ZkNodeTree(path + "/" + string, string, null);
        zkNodeTrees.add(hc1);
      }
    }
    return zkNodeTrees;
  }

  /**
   * 创建 node
   *
   * @param createParam 创建参数
   * @param hostPort zk的ip+port
   * @return true: 创建成功, false 创建失败
   */
  public boolean createNode(ZkNodeCreateParam createParam, String hostPort) {
    CuratorFramework curator = startCurator(hostPort);

    if ("PERSISTENT".equals(createParam.getType())) {// 持久化节点
      return createPersistent(curator, createParam);
    } else if ("EPHEMERAL".equals(createParam.getType())) {// 临时节点
      return createEphemeral(curator, createParam);
    }

    return false;
  }

  /**
   * 创建临时节点
   *
   * @param curator zk 客户端
   * @param createParam 创建参数
   * @return true 创建成功, false 创建失败
   */
  private boolean createEphemeral(CuratorFramework curator, ZkNodeCreateParam createParam) {
    try {
      if (checkTempPath(createParam.getPath())) {
        throw new IllegalArgumentException("临时节点不允许有子节点");
      }
      if (!exists(curator, createParam.getPath())) {

        curator
            .create()
            .withMode(CreateMode.EPHEMERAL)
            .forPath(createParam.getPath(), createParam.getData().getBytes());

        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      log.error("创建临时节点失败", e);
      return false;
    }
  }

  /**
   * 临时节点的判断
   *
   * @param path 节点地址
   * @return true 是, false 不是
   */
  private boolean checkTempPath(String path) {
    String[] split = path.split("/");
    return split.length > 2;
  }

  /**
   * 创建持久化节点
   *
   * @param curator zk 客户端
   * @param createParam 创建参数
   * @return true 创建成功, false 创建失败
   */
  private boolean createPersistent(CuratorFramework curator, ZkNodeCreateParam createParam) {
    try {
      if (!exists(curator, createParam.getPath())) {
        curator
            .create()
            .creatingParentsIfNeeded()
            .withMode(CreateMode.PERSISTENT)
            .forPath(createParam.getPath(), createParam.getData().getBytes());
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      log.info("创建持久化节点失败", e);
      return false;
    }
  }

  /**
   * start zookeeper client
   *
   * @param hostPort host and port
   * @return zookeeper client
   */
  private CuratorFramework startCurator(String hostPort) {
    CuratorFramework curator =
        CuratorFrameworkFactory.builder()
            .connectString(hostPort)
            .sessionTimeoutMs(30000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 10))
            .build();
    curator.start();
    return curator;
  }

  /**
   * 是否存在这个节点
   *
   * @param curator zookeeper client
   * @param path 节点地址
   * @return true: 存在,false: 不存在
   */
  private boolean exists(CuratorFramework curator, String path) {
    try {
      return curator.checkExists().forPath(path) != null;
    } catch (Exception e) {
      log.error("判断是否存在节点失败", e);
      return false;
    }
  }

  /**
   * 查看节点信息
   *
   * @param path 节点
   * @param hostPort zk的ip+port
   * @return 单个节点的信息
   */
  public ZkNodeInfo getNode(String path, String hostPort) {

    try {

      CuratorFramework curator = startCurator(hostPort);
      Stat stat = new Stat();
      byte[] bytes = curator.getData().storingStatIn(stat).forPath(path);

      String data;
      if (bytes != null && bytes.length > 0) {
        data = new String(bytes);
      } else {
        data = "";
      }
      curator.close();
      return new ZkNodeInfo(
          new ZkStat(
              stat.getCzxid(),
              stat.getMzxid(),
              stat.getCtime(),
              stat.getMtime(),
              stat.getVersion(),
              stat.getCversion(),
              stat.getAversion(),
              stat.getEphemeralOwner(),
              stat.getDataLength(),
              stat.getNumChildren(),
              stat.getPzxid()),
          data,
          nodeType(stat));
    } catch (Exception e) {
      log.error("获取节点信息失败", e);
      return null;
    }
  }

  /**
   * 获取节点状态
   *
   * @param stat zk stat
   * @return 临时节点\持久化节点
   */
  private String nodeType(Stat stat) {
    return stat.getEphemeralOwner() > 0 ? "临时节点" : "持久化节点";
  }

  /**
   * 获取整个zookeeper的节点数据,树结构
   *
   * @param hostPort zk的ip+port
   * @return zk 节点数据结构
   * @throws Exception zookeeper 客户端异常
   */
  public ZkNodeTree tree(String hostPort) throws Exception {
    CuratorFramework curator = startCurator(hostPort);
    ZkNodeTree zkNodeTree = new ZkNodeTree("/", "/", null);
    calcTree(curator, "/", zkNodeTree);
    return zkNodeTree;
  }

  /**
   * 计算zookeeper树节点
   *
   * @param curator zk 客户端
   * @param path
   * @param zkNodeTree
   */
  private void calcTree(CuratorFramework curator, String path, ZkNodeTree zkNodeTree)
      throws Exception {
    List<String> strings = curator.getChildren().forPath(path);
    zkNodeTree.setPath(path);
    zkNodeTree.setChild(child(strings, path));

    for (ZkNodeTree nodeTree : zkNodeTree.getChild()) {
      calcTree(curator, nodeTree.getPath(), nodeTree);
    }
  }

  /**
   * 更新zookeeper节点信息
   *
   * @param updateNodeReq 更新的参数
   * @param hostPort zk的ip+port
   * @return true 成功, false 失败
   */
  @Override
  public boolean update(ZkNodeUpdateParam updateNodeReq, String hostPort) {
    CuratorFramework curator = startCurator(hostPort);
    if (exists(curator, updateNodeReq.getPath())) {

      try {
        curator.setData().forPath(updateNodeReq.getPath(), updateNodeReq.getData().getBytes());
        return true;
      } catch (Exception e) {
        log.error("更新节点失败", e);
        return false;
      }
    } else {
      throw new IllegalArgumentException("当前节点地址不存在,path=[ " + updateNodeReq.getPath() + " ]");
    }
  }

  /**
   * 删除节点
   *
   * @param path 节点地址
   * @param hostPort zk的ip+port
   * @return true 成功, false 失败
   */
  @Override
  public boolean del(String path, String hostPort) {
    CuratorFramework curator = startCurator(hostPort);
    if (exists(curator, path)) {
      try {
        curator.delete().forPath(path);
        return true;
      } catch (Exception e) {
        log.error("删除节点失败", e);
        return false;
      }
    }
    return false;
  }
}
