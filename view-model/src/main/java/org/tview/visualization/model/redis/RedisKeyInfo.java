package org.tview.visualization.model.redis;

import java.util.Objects;

/**
 * redis key的信息
 */
public class RedisKeyInfo {

  /**
   * key name
   */
  private String key;

  /**
   * 数据类型
   */
  private RedisDataType dataType;

  public RedisKeyInfo(String key, RedisDataType dataType) {
    this.key = key;
    this.dataType = dataType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RedisKeyInfo that = (RedisKeyInfo) o;
    return Objects.equals(key, that.key) && dataType == that.dataType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, dataType);
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public RedisDataType getDataType() {
    return dataType;
  }

  public void setDataType(RedisDataType dataType) {
    this.dataType = dataType;
  }
}
