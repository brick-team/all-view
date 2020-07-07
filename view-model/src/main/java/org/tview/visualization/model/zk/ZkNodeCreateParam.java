package org.tview.visualization.model.zk;

/**
 * 创建 zookeeper node 的参数
 *
 * @see org.tview.visualization.model.zk.ZkNodeInfo
 */
public class ZkNodeCreateParam {
  private String path;

  private String type;

  private String data;

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}
