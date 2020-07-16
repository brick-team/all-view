package org.tview.visualization.redis.impl;

import java.util.Set;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.tview.visualization.inter.redis.RedisZSetOperation;
import org.tview.visualization.redis.factory.AbsRedisTemplate;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactory;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactoryImpl;

class RedisZSetOperationImplTest extends AbsRedisTemplate {

  public static final String ZSET_KEY = "zset";
  RedisZSetOperation zSetOperation = new RedisZSetOperationImpl();
  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  @BeforeEach
  public void delete() {
    factory.factory(config).delete(ZSET_KEY);
  }

  @Test
  void add() {
    zSetOperation.add(this.config, ZSET_KEY, 2.0, "a");
    zSetOperation.add(this.config, ZSET_KEY, 2.0, "b");
    Set set = zSetOperation.get(this.config, ZSET_KEY);
    Assert.assertTrue(set.size() == 2);
  }

  @Test
  void del() {
    zSetOperation.add(this.config, ZSET_KEY, 2.0, "a");
    zSetOperation.add(this.config, ZSET_KEY, 1.0, "b");
    zSetOperation.del(this.config, ZSET_KEY, "a");
    Set set = zSetOperation.get(this.config, ZSET_KEY);
    Assert.assertTrue(set.size() == 1);
  }

  @Test
  void update() {
    zSetOperation.add(this.config, ZSET_KEY, 2.0, "a");
    zSetOperation.update(this.config, ZSET_KEY, 2.3, "a");
    Set<TypedTuple> set = zSetOperation.get(this.config, ZSET_KEY);
  }
}
