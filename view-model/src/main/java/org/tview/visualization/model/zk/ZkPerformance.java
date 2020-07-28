package org.tview.visualization.model.zk;

/** * zookeeper 指标 */
public class ZkPerformance {

  private String zk_avg;
  private String min;
  private String zk_max_latency;
  private String zk_outstanding_requests;
  private String zk_packets_received;
  private String zk_packets_sent;

  public String getZk_avg() {
    return zk_avg;
  }

  public void setZk_avg(String zk_avg) {
    this.zk_avg = zk_avg;
  }

  public String getMin() {
    return min;
  }

  public void setMin(String min) {
    this.min = min;
  }

  public String getZk_max_latency() {
    return zk_max_latency;
  }

  public void setZk_max_latency(String zk_max_latency) {
    this.zk_max_latency = zk_max_latency;
  }

  public String getZk_outstanding_requests() {
    return zk_outstanding_requests;
  }

  public void setZk_outstanding_requests(String zk_outstanding_requests) {
    this.zk_outstanding_requests = zk_outstanding_requests;
  }

  public String getZk_packets_received() {
    return zk_packets_received;
  }

  public void setZk_packets_received(String zk_packets_received) {
    this.zk_packets_received = zk_packets_received;
  }

  public String getZk_packets_sent() {
    return zk_packets_sent;
  }

  public void setZk_packets_sent(String zk_packets_sent) {
    this.zk_packets_sent = zk_packets_sent;
  }
}
