package org.tview.visualization.model.redis;

/** redis event 配置信息 */
public class RedisEventProperties {

  private boolean open = true;
  private int size = 100;
  private long period = 1000;

  public RedisEventProperties() {}

  public RedisEventProperties(boolean open, int size) {
    this.open = open;
    this.size = size;
  }

  public long getPeriod() {
    return period;
  }

  public void setPeriod(long period) {
    this.period = period;
  }

  public boolean isOpen() {
    return open;
  }

  public void setOpen(boolean open) {
    this.open = open;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }
}
