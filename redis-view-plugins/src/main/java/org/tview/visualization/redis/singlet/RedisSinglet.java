package org.tview.visualization.redis.singlet;

import org.tview.visualization.redis.cache.RedisConnectionCache;
import org.tview.visualization.redis.cache.RedisKeysInfoCache;
import org.tview.visualization.redis.cache.RedisMemoryCache;
import org.tview.visualization.redis.cache.RedisNameConfigCache;

public class RedisSinglet {

  private static RedisConnectionCache redisConnectionCache = null;

  private static RedisKeysInfoCache redisKeysInfoCache = null;

  private static RedisNameConfigCache redisNameConfigCache = null;

  private static RedisMemoryCache memoryCache = null;
  public static RedisMemoryCache getMemoryCache(){
    if (memoryCache == null) {
      synchronized (RedisSinglet.class) {
        if (memoryCache == null) {
          memoryCache = new RedisMemoryCache(10);
        }
      }
    }
    return memoryCache;
  }

  public static RedisNameConfigCache getRedisNameConfigCache() {
    if (redisNameConfigCache == null) {
      synchronized (RedisSinglet.class) {
        if (redisNameConfigCache == null) {
          redisNameConfigCache = new RedisNameConfigCache(10);
        }
      }
    }
    return redisNameConfigCache;

  }

  public static RedisKeysInfoCache getRedisKeysInfoCache() {
    if (redisKeysInfoCache == null) {
      synchronized (RedisSinglet.class) {
        if (redisKeysInfoCache == null) {
          redisKeysInfoCache = new RedisKeysInfoCache(2014);
        }
      }
    }
    return redisKeysInfoCache;
  }

  public static RedisConnectionCache getRedisConnectionCache() {
    if (redisConnectionCache == null) {
      synchronized (RedisSinglet.class) {
        if (redisConnectionCache == null) {
          redisConnectionCache = new RedisConnectionCache(10);
        }
      }
    }
    return redisConnectionCache;
  }
}
