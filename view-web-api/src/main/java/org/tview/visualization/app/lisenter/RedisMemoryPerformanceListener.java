package org.tview.visualization.app.lisenter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.tview.visualization.inter.redis.IRedisServerInfo;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.model.redis.RedisMemoryTaskData;
import org.tview.visualization.model.redis.info.RedisCliInfoMemory;
import org.tview.visualization.redis.cache.RedisMemoryCache;
import org.tview.visualization.redis.impl.IRedisServiceInfoImpl;

@Service
public class RedisMemoryPerformanceListener implements IPerformanceListener {

  private final Map<String, ScheduledFuture<?>> futureMap = new HashMap<>();
  private final Map<String, RedisMemoryCache> memoryCacheMap = new HashMap<>();
  protected Logger log = LoggerFactory.getLogger(RedisMemoryPerformanceListener.class);
  IRedisServerInfo redisServerInfo = new IRedisServiceInfoImpl();
  @Autowired
  private ThreadPoolTaskScheduler threadPoolTaskScheduler;

  @Value("${redis-memory.cron:/5 * * * * ?}")
  private String redisMemoryCron;

  @Value("${redis-memory.size:50}")
  private Integer redisMemorySize;


  public void createWork(String name, ConfigInterface config) {
    ScheduledFuture<?> schedule =
        threadPoolTaskScheduler.scheduleWithFixedDelay(
            () -> {
              synchronized (this) {
                RedisCliInfoMemory memory = redisServerInfo.memory(
                    (RedisConnectionConfig) config.get());
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss");
                RedisMemoryTaskData redisMemoryTaskData =
                    new RedisMemoryTaskData(
                        Long.parseLong(memory.getUsedMemory()),
                        Long.parseLong(memory.getUsedMemoryRss()),
                        Long.parseLong(memory.getUsedMemoryPeak()),
                        Long.parseLong(memory.getUsedMemoryLua()));

                RedisMemoryCache redisMemoryCache = memoryCacheMap.get(name);
                log.debug("开始设置redis内存监控缓存,name=[{}],value=[{}]", name, redisMemoryTaskData);
                if (redisMemoryCache == null) {
                  redisMemoryCache = new RedisMemoryCache(redisMemorySize);
                  redisMemoryCache
                      .put(dateTimeFormatter.format(LocalDateTime.now()), redisMemoryTaskData);
                }
                else {
                  redisMemoryCache
                      .put(dateTimeFormatter.format(LocalDateTime.now()), redisMemoryTaskData);
                }
                log.info("开始设置redis组级别的缓存,name=[{}]", name);
                memoryCacheMap.put(name, redisMemoryCache);
              }
            },
            //            triggerContext -> new
            // CronTrigger(redisMemoryCron).nextExecutionTime(triggerContext)
            2000);

    futureMap.put(name, schedule);
  }

  public Object get(String name) {
    return memoryCacheMap.get(name);
  }

  public void remove(String name) {
    log.info("开始删除redis组级别缓存,name=[{}]", name);
    if (!futureMap.isEmpty()) {
      if (futureMap.containsKey(name)) {
        ScheduledFuture<?> scheduledFuture = futureMap.get(name);
        scheduledFuture.cancel(true);
        futureMap.remove(name);
      }
    }
  }
}
