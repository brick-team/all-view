package org.tview.visualization.redis.impl;

import java.util.List;
import org.tview.visualization.inter.redis.RedisHashOperation;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactory;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactoryImpl;

public class RedisHashOperationImpl implements RedisHashOperation {

  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  @Override
  public void add(RedisConnectionConfig config, String k, String field, String v) {
    factory.factory(config).opsForHash().put(k, field, v);
  }

  @Override
  public Object get(RedisConnectionConfig config, String k) {
    return factory.factory(config).opsForHash().entries(k);
  }

  @Override
  public void del(RedisConnectionConfig config, String k, String field) {
    factory.factory(config).opsForHash().delete(k, List.of(field));
  }

  @Override
  public void update(RedisConnectionConfig config, String k, String field, String v) {
    factory.factory(config).opsForHash().put(k, field, v);
  }
}
