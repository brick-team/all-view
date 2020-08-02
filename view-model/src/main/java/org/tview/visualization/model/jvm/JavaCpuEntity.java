package org.tview.visualization.model.jvm;

public class JavaCpuEntity {
  private long totalSwapSpaceSize;
  private long processCpuTime;
  private long freePhysicalMemorySize;
  private long freeMemorySize;
  private long totalPhysicalMemorySize;
  private double systemCpuLoad;
  private double processCpuLoad;
  private long committedVirtualMemorySize;

  public long getTotalSwapSpaceSize() {
    return totalSwapSpaceSize;
  }

  public void setTotalSwapSpaceSize(long totalSwapSpaceSize) {
    this.totalSwapSpaceSize = totalSwapSpaceSize;
  }

  public long getProcessCpuTime() {
    return processCpuTime;
  }

  public void setProcessCpuTime(long processCpuTime) {
    this.processCpuTime = processCpuTime;
  }

  public long getFreePhysicalMemorySize() {
    return freePhysicalMemorySize;
  }

  public void setFreePhysicalMemorySize(long freePhysicalMemorySize) {
    this.freePhysicalMemorySize = freePhysicalMemorySize;
  }

  public long getFreeMemorySize() {
    return freeMemorySize;
  }

  public void setFreeMemorySize(long freeMemorySize) {
    this.freeMemorySize = freeMemorySize;
  }

  public long getTotalPhysicalMemorySize() {
    return totalPhysicalMemorySize;
  }

  public void setTotalPhysicalMemorySize(long totalPhysicalMemorySize) {
    this.totalPhysicalMemorySize = totalPhysicalMemorySize;
  }

  public double getSystemCpuLoad() {
    return systemCpuLoad;
  }

  public void setSystemCpuLoad(double systemCpuLoad) {
    this.systemCpuLoad = systemCpuLoad;
  }

  public double getProcessCpuLoad() {
    return processCpuLoad;
  }

  public void setProcessCpuLoad(double processCpuLoad) {
    this.processCpuLoad = processCpuLoad;
  }

  public long getCommittedVirtualMemorySize() {
    return committedVirtualMemorySize;
  }

  public void setCommittedVirtualMemorySize(long committedVirtualMemorySize) {
    this.committedVirtualMemorySize = committedVirtualMemorySize;
  }
}
