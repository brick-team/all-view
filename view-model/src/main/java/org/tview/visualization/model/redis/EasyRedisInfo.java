package org.tview.visualization.model.redis;

public class EasyRedisInfo {
  private String version;
  private long useMemory;
  private int client;
  private long execSize;
  private long runTime;

  private String runDesc;

  public String getRunDesc() {
    return runDesc;
  }

  public void setRunDesc(String runDesc) {
    this.runDesc = runDesc;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public long getUseMemory() {
    return useMemory;
  }

  public void setUseMemory(long useMemory) {
    this.useMemory = useMemory;
  }

  public int getClient() {
    return client;
  }

  public void setClient(int client) {
    this.client = client;
  }

  public long getExecSize() {
    return execSize;
  }

  public void setExecSize(long execSize) {
    this.execSize = execSize;
  }

  public long getRunTime() {
    return runTime;
  }

  public void setRunTime(long runTime) {
    this.runTime = runTime;
  }
}
