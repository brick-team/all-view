package org.tview.visualization.model.jvm;

import java.util.HashMap;
import java.util.List;

public class JavaThreadEntity {
  private HashMap<Thread.State, Integer> state;

  private List<JavaThreadInfo> detail;
  private long total;
  private long time;
  private long deamon;

  public HashMap<Thread.State, Integer> getState() {
    return state;
  }

  public void setState(HashMap<Thread.State, Integer> state) {
    this.state = state;
  }

  public List<JavaThreadInfo> getDetail() {
    return detail;
  }

  public void setDetail(List<JavaThreadInfo> detail) {
    this.detail = detail;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public long getDeamon() {
    return deamon;
  }

  public void setDeamon(long deamon) {
    this.deamon = deamon;
  }
}
