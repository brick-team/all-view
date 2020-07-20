package org.tview.visualization.app.listener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.tview.visualization.app.conf.PerformanceConfiguration;
import org.tview.visualization.cache.impl.FifoCache;
import org.tview.visualization.model.enums.PerformanceEnums;

@Service
public abstract class AbsPerformanceListener implements IPerformanceListener {

  private final Map<String, ScheduledFuture<?>> futureMap = new HashMap<>();
  private final Map<String, FifoCache> MYSQL = new HashMap<>();
  private final Map<String, FifoCache> REDIS = new HashMap<>();
  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  @Autowired private ThreadPoolTaskScheduler threadPoolTaskScheduler;
  @Autowired private PerformanceConfiguration performanceConfiguration;

  public PerformanceConfiguration getPerformanceConfiguration() {
    return performanceConfiguration;
  }

  public String formatDate() {
    return dateTimeFormatter.format(LocalDateTime.now());
  }

  public Map<String, FifoCache> getCache(PerformanceEnums performanceEnums) {
    if (performanceEnums == PerformanceEnums.MYSQL) {
      return MYSQL;
    } else if (performanceEnums == PerformanceEnums.REDIS) {
      return REDIS;
    }
    return null;
  }

  @Override
  public void createWork(
      String name, ConfigInterface absConfig, IListenerWork work, PerformanceEnums performanceEnums) {
    ScheduledFuture<?> scheduledFuture =
        this.threadPoolTaskScheduler.schedule(
            () -> {
              try {
                work.work();
              } catch (Exception e) {
                e.printStackTrace();
              }
            },
            triggerContext -> {
              String cron = cron(performanceEnums);
              if (StringUtils.isEmpty(cron)) {
                throw new IllegalArgumentException("定时任务时间为空");
              }
              CronTrigger trigger = new CronTrigger(cron);
              return trigger.nextExecutionTime(triggerContext);
            });
    this.futureMap.put(name, scheduledFuture);
  }

  private String cron(PerformanceEnums performanceEnums) {
    if (performanceEnums == PerformanceEnums.MYSQL) {
      this.performanceConfiguration.getMysql().getCron();
    } else if (performanceEnums == PerformanceEnums.REDIS) {
      this.performanceConfiguration.getRedis().getCron();
    }
    return null;
  }

  @Override
  public Object get(String name, PerformanceEnums performanceEnums) {
    if (performanceEnums == PerformanceEnums.MYSQL) {
      return MYSQL.get(name);
    } else if (performanceEnums == PerformanceEnums.REDIS) {
      return REDIS.get(name);
    }
    return null;
  }

  @Override
  public void remove(String name) {
    if (!futureMap.isEmpty()) {
      if (futureMap.containsKey(name)) {
        ScheduledFuture<?> scheduledFuture = futureMap.get(name);
        scheduledFuture.cancel(true);
        futureMap.remove(name);
      }
    }
  }
}
