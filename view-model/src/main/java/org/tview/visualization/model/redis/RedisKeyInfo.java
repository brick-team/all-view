package org.tview.visualization.model.redis;

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
