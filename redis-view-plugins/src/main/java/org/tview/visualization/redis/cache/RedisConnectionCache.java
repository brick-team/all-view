package org.tview.visualization.redis.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.tview.visualization.cache.impl.LruCache;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.redis.singlet.RedisSinglet;

/**
 * @see RedisSinglet#getRedisConnectionCache()
 */
public class RedisConnectionCache extends LruCache<RedisConnectionConfig, RedisTemplate> {

  public RedisConnectionCache(int size) {
    super(size);
  }

  @Override
  public int size() {
    return super.size();
  }

  @Override
  public void put(RedisConnectionConfig key, RedisTemplate value) {
    super.put(key, value);
  }

  @Override
  public RedisTemplate get(RedisConnectionConfig key) {
    return super.get(key);
  }
}
