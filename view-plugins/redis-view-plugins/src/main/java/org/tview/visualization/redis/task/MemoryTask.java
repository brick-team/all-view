package org.tview.visualization.redis.task;

import org.tview.visualization.inter.redis.IRedisServerInfo;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.model.redis.RedisMemoryTaskData;
import org.tview.visualization.model.redis.info.RedisCliInfoMemory;
import org.tview.visualization.redis.cache.RedisMemoryCache;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactory;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactoryImpl;
import org.tview.visualization.redis.impl.IRedisServiceInfoImpl;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class MemoryTask {

  public static final long time_out = 10000;
  RedisMemoryCache cache = new RedisMemoryCache(30);
  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  IRedisServerInfo redisServerInfo = new IRedisServiceInfoImpl();
  Timer timer = new Timer();

  public void task(RedisConnectionConfig config) {
    timer.schedule(
        new TimerTask() {
          @Override
          public void run() {
            RedisCliInfoMemory memory = redisServerInfo.memory(config);
            cache.put(
                String.valueOf(System.currentTimeMillis()),
                new RedisMemoryTaskData(
                    Long.parseLong(memory.getUsedMemory()),
                    Long.parseLong(memory.getUsedMemoryRss()),
                    Long.parseLong(memory.getUsedMemoryPeak()),
                    Long.parseLong(memory.getUsedMemoryLua())));
          }
        },
        0,
        5000);
  }

  public void shutdown() {
    timer.cancel();
  }

  public List<RedisMemoryTaskData> get() {
    Map<String, RedisMemoryTaskData> map = cache.getMap();
    return map.values().stream().collect(Collectors.toList());
  }
}
