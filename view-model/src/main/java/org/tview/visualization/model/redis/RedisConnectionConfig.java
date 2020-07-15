package org.tview.visualization.model.redis;

import java.util.Objects;
import org.tview.visualization.model.label.AbsConfig;

/** redis 的链接配置对象 */
public class RedisConnectionConfig extends AbsConfig {
  private String host;
  private Integer port;
  private String pwd;
  private int dbIndex;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RedisConnectionConfig that = (RedisConnectionConfig) o;
    return dbIndex == that.dbIndex &&
        Objects.equals(host, that.host) &&
        Objects.equals(port, that.port) &&
        Objects.equals(pwd, that.pwd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(host, port, pwd, dbIndex);
  }

  public int getDbIndex() {
    return dbIndex;
  }

  public void setDbIndex(int dbIndex) {
    this.dbIndex = dbIndex;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }
}
