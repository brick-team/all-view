package org.tview.visualization.app.listener;

import java.sql.SQLException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tview.visualization.cache.impl.FifoCache;
import org.tview.visualization.inter.redis.IRedisServerInfo;
import org.tview.visualization.model.enums.PerformanceEnums;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.model.redis.RedisMemoryTaskData;
import org.tview.visualization.model.redis.info.RedisCliInfoMemory;
import org.tview.visualization.redis.cache.RedisMemoryCache;
import org.tview.visualization.redis.impl.IRedisServiceInfoImpl;

@Service
public class NewRedisPerformance extends AbsPerformanceListener implements IListenerWork {

  protected Logger log = LoggerFactory.getLogger(NewRedisPerformance.class);

  IRedisServerInfo redisServerInfo = new IRedisServiceInfoImpl();
  private RedisConnectionConfig config;
  private String name;

  @Override
  public void work() throws SQLException {
    synchronized (this) {
      String time = formatDate();
      Map<String, FifoCache> cache = this.getCache(PerformanceEnums.REDIS);
      RedisMemoryTaskData redisMemoryTaskData = redisMemoryTaskData(config);
      if (!cache.containsKey(name)) {
        log.info("开始记录 redis 性能指标,time=[{}]", time);
        RedisMemoryCache mysqlPerformanceCache =
            new RedisMemoryCache(getPerformanceConfiguration().getRedis().getSize());
        mysqlPerformanceCache.put(time, redisMemoryTaskData);
        cache.put(name, mysqlPerformanceCache);
      } else {
        log.info("开始记录 redis 性能指标,time=[{}]", time);
        cache.get(name).put(time, redisMemoryTaskData);
      }
    }
  }

  @Override
  public void createWork(
      String name,
      ConfigInterface absConfig,
      IListenerWork work,
      PerformanceEnums performanceEnums) {
    this.config = (RedisConnectionConfig) absConfig.get();
    this.name = name;
    if (performanceEnums == null) {
      super.createWork(name, absConfig, this, performanceEnums);
    } else {
      super.createWork(name, absConfig, work, performanceEnums);
    }
  }

  private RedisMemoryTaskData redisMemoryTaskData(RedisConnectionConfig config) {
    RedisCliInfoMemory memory = redisServerInfo.memory(config);
    return new RedisMemoryTaskData(
        Long.parseLong(memory.getUsedMemory()),
        Long.parseLong(memory.getUsedMemoryRss()),
        Long.parseLong(memory.getUsedMemoryPeak()),
        Long.parseLong(memory.getUsedMemoryLua()));
  }
}
