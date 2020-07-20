package org.tview.visualization.redis.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.tview.visualization.model.redis.RedisConnectionConfig;

public class AbsRedisTemplate {

  public RedisConnectionConfig config;

  @DisplayName(value = "init_redis_config")
  @BeforeEach
  public void init() {
    config = new RedisConnectionConfig();
    config.setHost("127.0.0.1");
    config.setPort(6379);
    config.setPwd("");
    config.setDbIndex(1);
  }
}
