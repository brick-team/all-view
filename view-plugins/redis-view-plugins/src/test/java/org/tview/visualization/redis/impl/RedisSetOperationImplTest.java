package org.tview.visualization.redis.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tview.visualization.inter.redis.RedisSetOperation;
import org.tview.visualization.redis.factory.AbsRedisTemplate;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactory;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactoryImpl;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RedisSetOperationImplTest extends AbsRedisTemplate {

  public static final String SET_KEY = "set";
  RedisSetOperation setOperation = new RedisSetOperationImpl();
  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  @BeforeEach
  public void delete() {
    factory.factory(this.config).delete(SET_KEY);
  }

  @Test
  void add() {
    setOperation.add(this.config, SET_KEY, "1");
    setOperation.add(this.config, SET_KEY, "2");

    Collection set = setOperation.get(this.config, SET_KEY);
    int size = set.size();
    assertEquals(size, 2);
  }

  @Test
  void update() {
    setOperation.add(this.config, SET_KEY, "1");
    setOperation.add(this.config, SET_KEY, "2");
    setOperation.update(this.config, "set", "1", "3");

    Collection set = setOperation.get(this.config, SET_KEY);

    assertTrue(set.contains("3"));
  }

  @Test
  void del() {
    setOperation.add(this.config, SET_KEY, "1");
    setOperation.add(this.config, SET_KEY, "2");
    setOperation.del(this.config, SET_KEY, "1");
    Collection set = setOperation.get(this.config, SET_KEY);
    assertTrue(set.size() == 1);
  }
}
