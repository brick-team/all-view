package org.tview.visualization.model.db;

import java.util.Objects;

/** 数据库链接配置 */
public class DBConnectionConfig {
  /** host */
  private String host;
  /** port */
  private Integer port;
  /** username */
  private String username;
  /** password */
  private String password;
  /** timezone */
  private String timeZone;

  private String dbType;

  private String dbName;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DBConnectionConfig that = (DBConnectionConfig) o;
    return Objects.equals(host, that.host)
        && Objects.equals(port, that.port)
        && Objects.equals(username, that.username)
        && Objects.equals(password, that.password)
        && Objects.equals(timeZone, that.timeZone)
        && Objects.equals(dbType, that.dbType)
        && Objects.equals(dbName, that.dbName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(host, port, username, password, timeZone, dbType, dbName);
  }

  public String getDbName() {
    return dbName;
  }

  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  public DBConnectionConfig() {}

  public String getDbType() {
    return dbType;
  }

  public void setDbType(String dbType) {
    this.dbType = dbType;
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
  }
}
