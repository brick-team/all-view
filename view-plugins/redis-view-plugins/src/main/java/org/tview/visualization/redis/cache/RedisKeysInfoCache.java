package org.tview.visualization.redis.cache;

import org.tview.visualization.cache.impl.LruCache;
import org.tview.visualization.model.redis.RedisKeyInfo;
import org.tview.visualization.redis.singlet.RedisSinglet;

/** @see RedisSinglet#getRedisKeysInfoCache() */
public class RedisKeysInfoCache extends LruCache<String, RedisKeyInfo> {

  public RedisKeysInfoCache(int size) {
    super(size);
  }

  @Override
  public int size() {
    return super.size();
  }

  @Override
  public void put(String key, RedisKeyInfo value) {
    super.put(key, value);
  }

  @Override
  public RedisKeyInfo get(String key) {
    return super.get(key);
  }
}
