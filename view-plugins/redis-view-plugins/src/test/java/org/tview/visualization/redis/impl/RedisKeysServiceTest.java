package org.tview.visualization.redis.impl;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.tview.visualization.inter.redis.RedisKeysOperation;
import org.tview.visualization.model.redis.RedisKeyInfo;
import org.tview.visualization.redis.factory.AbsRedisTemplate;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactory;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactoryImpl;

class RedisKeysServiceTest extends AbsRedisTemplate {

  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  RedisKeysOperation keysOperation = new RedisKeysService();

  @Test
  void keys() {
    List<RedisKeyInfo> keys = keysOperation.keys(this.config, "*");
    List<RedisKeyInfo> ff = keysOperation.keys(this.config, "hash");
    System.out.println();
  }
}
