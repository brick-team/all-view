package org.tview.visualization.redis.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.tview.visualization.inter.redis.RedisStringOperation;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactory;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactoryImpl;

public class RedisStringOperationImpl implements RedisStringOperation {
  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  @Override
  public void add(RedisConnectionConfig config, String k, String v) {
    getRedisTemplate(config).opsForValue().set(k, v);
  }

  private RedisTemplate getRedisTemplate(RedisConnectionConfig config) {
    return factory.factory(config);
  }

  @Override
  public Object get(RedisConnectionConfig config, String k, String v) {
    return getRedisTemplate(config).opsForValue().get(k);
  }

  @Override
  public void delete(RedisConnectionConfig config, String k) {
    getRedisTemplate(config).delete(k);
  }

  @Override
  public void update(RedisConnectionConfig config, String k, String v) {
    getRedisTemplate(config).opsForValue().set(k, v);
  }
}
