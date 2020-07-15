package org.tview.visualization.redis.event;

import org.tview.visualization.model.redis.RedisConnectionConfig;

public class RedisMemoryEvent {

  private RedisConnectionConfig config;
  private int size;

  public RedisMemoryEvent() {
  }

  public RedisMemoryEvent(RedisConnectionConfig config, int size) {
    this.config = config;
    this.size = size;
  }

  public RedisConnectionConfig getConfig() {
    return config;
  }

  public void setConfig(RedisConnectionConfig config) {
    this.config = config;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }
}
