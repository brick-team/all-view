package org.tview.visualization.model.redis;

public enum RedisDataType {
  STRING("STRING"),
  HASH("HASH"),
  ZSET("ZSET"),
  LIST("LIST"),
  SET("SET"),
  ;

  private String name;

  RedisDataType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
