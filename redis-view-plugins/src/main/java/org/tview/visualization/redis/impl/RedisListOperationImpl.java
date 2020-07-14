package org.tview.visualization.redis.impl;

import org.tview.visualization.inter.redis.RedisListOperation;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactory;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactoryImpl;

public class RedisListOperationImpl implements RedisListOperation {

  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  @Override
  public void add(RedisConnectionConfig conf, String k, String v) {
    factory.factory(conf).opsForList().rightPush(k, v);
  }

  @Override
  public Object get(RedisConnectionConfig conf, String k) {
    return factory.factory(conf).opsForList().range(k, 0, -1);
  }

  @Override
  public void update(RedisConnectionConfig conf, String k, String v) {
    factory.factory(conf).opsForList().leftPush(k, v);
  }

  @Override
  public void del(RedisConnectionConfig conf, String k) {
    factory.factory(conf).opsForList().leftPop(k);
  }
}
