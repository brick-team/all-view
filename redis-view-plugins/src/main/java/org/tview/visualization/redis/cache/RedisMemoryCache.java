package org.tview.visualization.redis.cache;

import org.tview.visualization.cache.impl.FifoCache;
import org.tview.visualization.model.redis.RedisMemoryTaskData;

public class RedisMemoryCache extends FifoCache<String, RedisMemoryTaskData> {

  public RedisMemoryCache(int size) {
    super(size);
  }

  @Override
  public int size() {
    return super.size();
  }

  @Override
  public void put(String key, RedisMemoryTaskData value) {
    super.put(key, value);
  }

  @Override
  public RedisMemoryTaskData get(String key) {
    return super.get(key);
  }

}
