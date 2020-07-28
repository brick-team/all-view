package org.tview.visualization.model.jvm;

public class JavaUsageEntity {
  private long init;

  private long used;

  private long committed;

  private long max;

  public long getInit() {
    return init;
  }

  public void setInit(long init) {
    this.init = init;
  }

  public long getUsed() {
    return used;
  }

  public void setUsed(long used) {
    this.used = used;
  }

  public long getCommitted() {
    return committed;
  }

  public void setCommitted(long committed) {
    this.committed = committed;
  }

  public long getMax() {
    return max;
  }

  public void setMax(long max) {
    this.max = max;
  }
}
