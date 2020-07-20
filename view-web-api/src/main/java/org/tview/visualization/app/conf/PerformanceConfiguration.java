package org.tview.visualization.app.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "performance")
public class PerformanceConfiguration {


  private PerInfo mysql;
  private PerInfo redis;

  public PerInfo getMysql() {
    return mysql;
  }

  public void setMysql(PerInfo mysql) {
    this.mysql = mysql;
  }

  public PerInfo getRedis() {
    return redis;
  }

  public void setRedis(PerInfo redis) {
    this.redis = redis;
  }

  public static class PerInfo {

    private String cron;
    private int size;

    public String getCron() {
      return cron;
    }

    public void setCron(String cron) {
      this.cron = cron;
    }

    public int getSize() {
      return size;
    }

    public void setSize(int size) {
      this.size = size;
    }
  }

}
