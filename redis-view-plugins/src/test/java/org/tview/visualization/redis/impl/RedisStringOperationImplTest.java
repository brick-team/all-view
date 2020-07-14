package org.tview.visualization.redis.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.tview.visualization.inter.redis.RedisStringOperation;
import org.tview.visualization.redis.factory.AbsRedisTemplate;

class RedisStringOperationImplTest extends AbsRedisTemplate {

  RedisStringOperation stringOperation = new RedisStringOperationImpl();
  String data = "data";
  String key = "zs:12";

  @Test
  void add() {

    stringOperation.add(this.config, key, data);
    Object o = stringOperation.get(this.config, key);
    assertEquals(data, o);
  }

  @Test
  void get() {
    assertEquals(data, stringOperation.get(this.config, key));
  }

  @Test
  void delete() {
    stringOperation.delete(this.config, key);
    Object o = stringOperation.get(this.config, key);
    assertNull(o);

  }

  @Test
  void update() {
    stringOperation.update(config, key, "new_data");
    Object o = stringOperation.get(config, key);
    assertEquals("new_data", o);
  }
}