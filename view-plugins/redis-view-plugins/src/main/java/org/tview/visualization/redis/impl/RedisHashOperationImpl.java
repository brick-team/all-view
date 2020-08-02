package org.tview.visualization.redis.impl;

import org.tview.visualization.inter.redis.RedisHashOperation;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactory;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactoryImpl;

import java.util.Map;

public class RedisHashOperationImpl implements RedisHashOperation {

  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  @Override
  public void add(RedisConnectionConfig config, String k, String field, String v) {
    factory.factory(config).opsForHash().put(k, field, v);
  }

  @Override
  public Map get(RedisConnectionConfig config, String k) {
    return factory.factory(config).opsForHash().entries(k);
  }

  @Override
  public void del(RedisConnectionConfig config, String k, String field) {
    factory.factory(config).opsForHash().delete(k, field);
  }

  @Override
  public void update(RedisConnectionConfig config, String k, String field, String v) {
    factory.factory(config).opsForHash().put(k, field, v);
  }
}
