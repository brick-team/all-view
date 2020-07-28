package org.tview.visualization.model.jvm;

public class JavaDeadlockCheck {
  private boolean hasdeadlock;
  private String info;

  public boolean isHasdeadlock() {
    return hasdeadlock;
  }

  public void setHasdeadlock(boolean hasdeadlock) {
    this.hasdeadlock = hasdeadlock;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }
}
