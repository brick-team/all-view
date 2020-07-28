package org.tview.visualization.model.jvm;

public class JavaThreadInfo {
  private long id;
  private Thread.State state;
  private String name;
  private long cpu;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Thread.State getState() {
    return state;
  }

  public void setState(Thread.State state) {
    this.state = state;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getCpu() {
    return cpu;
  }

  public void setCpu(long cpu) {
    this.cpu = cpu;
  }
}
