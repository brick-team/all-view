package org.tview.visualization.model.zk;

import java.util.List;

/**
 * zookeeper 节点树
 */
public class ZkNodeTree {

  /**
   * 节点全路径
   */
  private String path;
  /**
   * 展示的节点路径,最后一个
   */
  private String showName;
  /**
   * 子节点
   */
  private List<ZkNodeTree> child;

  public ZkNodeTree(String path, String showName, List<ZkNodeTree> child) {
    this.path = path;
    this.showName = showName;
    this.child = child;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getShowName() {
    return showName;
  }

  public void setShowName(String showName) {
    this.showName = showName;
  }

  public List<ZkNodeTree> getChild() {
    return child;
  }

  public void setChild(List<ZkNodeTree> child) {
    this.child = child;
  }
}
