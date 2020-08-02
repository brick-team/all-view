package org.tview.visualization.redis.impl;

import org.tview.visualization.inter.redis.RedisSetOperation;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactory;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactoryImpl;

import java.util.Collection;

public class RedisSetOperationImpl implements RedisSetOperation {

  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  @Override
  public void add(RedisConnectionConfig config, String k, String v) {
    factory.factory(config).opsForSet().add(k, v);
  }

  @Override
  public Collection get(RedisConnectionConfig config, String k) {
    return factory.factory(config).opsForSet().members(k);
  }

  @Override
  public void update(RedisConnectionConfig config, String k, String ov, String nv) {
    del(config, k, ov);
    factory.factory(config).opsForSet().add(k, nv);
  }

  @Override
  public void del(RedisConnectionConfig config, String k, String v) {
    factory.factory(config).opsForSet().remove(k, v);
  }
}
