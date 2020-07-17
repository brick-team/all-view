package org.tview.visualization.redis.cache;

import java.util.Map;
import org.tview.visualization.cache.impl.FifoCache;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.redis.singlet.RedisSinglet;

/** @see RedisSinglet#getRedisNameConfigCache() */
public class RedisNameConfigCache extends FifoCache<String, RedisConnectionConfig> {

  public RedisNameConfigCache(int size) {
    super(size);
  }

  @Override
  public int size() {
    return super.size();
  }

  @Override
  public void put(String key, RedisConnectionConfig value) {
    super.put(key, value);
  }

  @Override
  public RedisConnectionConfig get(String key) {
    return super.get(key);
  }

  @Override
  public Map<String, RedisConnectionConfig> getMap() {
    return super.getMap();
  }
}
