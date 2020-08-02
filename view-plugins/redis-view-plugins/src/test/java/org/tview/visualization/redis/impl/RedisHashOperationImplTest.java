package org.tview.visualization.redis.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tview.visualization.inter.redis.RedisHashOperation;
import org.tview.visualization.redis.factory.AbsRedisTemplate;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactory;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactoryImpl;

import java.util.Map;

class RedisHashOperationImplTest extends AbsRedisTemplate {

  public static final String HASH_KEY = "hash";
  RedisHashOperation hashOperation = new RedisHashOperationImpl();
  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  @BeforeEach
  public void delete() {
    factory.factory(config).delete(HASH_KEY);
  }

  @Test
  void add() {
    hashOperation.add(this.config, HASH_KEY, "A", "A");
    hashOperation.add(this.config, HASH_KEY, "B", "b");

    Map map = hashOperation.get(this.config, HASH_KEY);
    Assert.assertTrue(map.size() == 2);
  }

  @Test
  void del() {
    hashOperation.add(this.config, HASH_KEY, "A", "A");
    hashOperation.add(this.config, HASH_KEY, "B", "b");
    hashOperation.del(this.config, HASH_KEY, "A");
    Map map = hashOperation.get(this.config, HASH_KEY);
    Assert.assertTrue(map.size() == 1);
  }

  @Test
  void update() {
    hashOperation.add(this.config, HASH_KEY, "A", "A");
    hashOperation.update(this.config, HASH_KEY, "A", "NEW_VALUE");
    Map map = hashOperation.get(this.config, HASH_KEY);
    Object a = map.get("A");
    Assert.assertTrue(a.equals("NEW_VALUE"));
  }
}
