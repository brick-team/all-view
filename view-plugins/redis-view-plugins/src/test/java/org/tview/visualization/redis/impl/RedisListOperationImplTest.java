package org.tview.visualization.redis.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tview.visualization.redis.factory.AbsRedisTemplate;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactory;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactoryImpl;

import java.util.List;

class RedisListOperationImplTest extends AbsRedisTemplate {

  public static final String LIST_KEY = "list";
  RedisListOperationImpl listOperation = new RedisListOperationImpl();
  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  @BeforeEach
  void delete() {
    factory.factory(this.config).delete(LIST_KEY);
  }

  @Test
  void add() {
    listOperation.add(this.config, LIST_KEY, "A");
    List list = listOperation.get(this.config, LIST_KEY);
    Assert.assertEquals(list.size(), 1);
  }

  @Test
  void update() {
    listOperation.add(this.config, LIST_KEY, "a");
    listOperation.add(this.config, LIST_KEY, "b");
    listOperation.add(this.config, LIST_KEY, "c");
    listOperation.update(this.config, LIST_KEY, "b", "ccc");
  }

  @Test
  void del() {
    listOperation.add(this.config, LIST_KEY, "A");
    listOperation.add(this.config, LIST_KEY, "b");
    listOperation.add(this.config, LIST_KEY, "c");
    listOperation.del(this.config, LIST_KEY);
    //    List list = listOperation.get(this.config, LIST_KEY);
    //    Assert.assertEquals(list.size(), 0);
  }

  @Test
  void removeValue() {
    listOperation.add(this.config, LIST_KEY, "A");
    listOperation.add(this.config, LIST_KEY, "b");
    listOperation.add(this.config, LIST_KEY, "c");
    listOperation.removeByRow(this.config, LIST_KEY, 2 - 1);
    List list = listOperation.get(this.config, LIST_KEY);
    Assert.assertEquals(list.size(), 2);
  }
}
