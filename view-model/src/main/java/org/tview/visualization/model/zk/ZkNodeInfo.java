package org.tview.visualization.model.zk;

/** zookeeper 节点信息 */
public class ZkNodeInfo {

  /** zk stat */
  private ZkStat stat;
  /** 数据 */
  private String data;
  /**
   * * 节点类型:
   *
   * <ol>
   *   <li>持久化节点
   *   <li>临时节点
   * </ol>
   */
  private String type;

  public ZkNodeInfo(ZkStat stat, String data, String type) {
    this.stat = stat;
    this.data = data;
    this.type = type;
  }

  public ZkStat getStat() {
    return stat;
  }

  public void setStat(ZkStat stat) {
    this.stat = stat;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
