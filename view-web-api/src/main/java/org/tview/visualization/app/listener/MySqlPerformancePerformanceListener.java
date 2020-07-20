package org.tview.visualization.app.listener;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.tview.visualization.inter.db.IDBPerformanceOperation;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.res.DbPerformance;
import org.tview.visualization.mysql.cache.MysqlPerformanceCache;
import org.tview.visualization.mysql.impl.MysqlPerformanceOperationImpl;

@Service
public class MySqlPerformancePerformanceListener {

  private final Map<String, ScheduledFuture<?>> futureMap = new HashMap<>();
  private final Map<String, MysqlPerformanceCache> dbPerformanceMap = new HashMap<>();
  protected Logger log = LoggerFactory.getLogger(MySqlPerformancePerformanceListener.class);
  IDBPerformanceOperation performanceOperation = new MysqlPerformanceOperationImpl();
  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  @Autowired private ThreadPoolTaskScheduler threadPoolTaskScheduler;

  public void createWork(String name, ConfigInterface config, IListenerWork work) {

    ScheduledFuture<?> scheduledFuture =
        this.threadPoolTaskScheduler.scheduleWithFixedDelay(
            () -> {
              try {
                synchronized (this) {
                  DbPerformance performance = performance((DBConnectionConfig) config.get());
                  MysqlPerformanceCache mysqlPerformanceCache = dbPerformanceMap.get(name);
                  if (mysqlPerformanceCache == null) {
                    mysqlPerformanceCache = new MysqlPerformanceCache(10);
                    mysqlPerformanceCache.put(
                        dateTimeFormatter.format(LocalDateTime.now()), performance);
                  } else {
                    mysqlPerformanceCache.put(
                        dateTimeFormatter.format(LocalDateTime.now()), performance);
                  }
                  log.info("开始设置mysql的指标,name = [{}]", name);
                  dbPerformanceMap.put(name, mysqlPerformanceCache);
                }
              } catch (Exception e) {
                log.error("mysql 指标获取失败,{}", e);
                e.printStackTrace();
              }
            },
            2000);

    futureMap.put(name, scheduledFuture);
  }

  private DbPerformance performance(DBConnectionConfig config) throws SQLException {
    DbPerformance dbPerformance = new DbPerformance();
    dbPerformance.setQps(performanceOperation.qps(config));
    dbPerformance.setTps(performanceOperation.tps(config));
    dbPerformance.setKeyBufferRead(performanceOperation.keyBufferRead(config));
    dbPerformance.setKeyBufferWrite(performanceOperation.keyBufferWrite(config));
    dbPerformance.setInnoDBBuffer(performanceOperation.innoDBBuffer(config));
    dbPerformance.setQueryCache(performanceOperation.queryCache(config));
    dbPerformance.setTableCache(performanceOperation.tableCache(config));
    dbPerformance.setThreadCache(performanceOperation.threadCache(config));
    dbPerformance.setTmpTable(performanceOperation.tmpTable(config));
    dbPerformance.setBinlogCache(performanceOperation.binlogCache(config));
    dbPerformance.setInnodbLogWaits(performanceOperation.innodbLogWaits(config));
    return dbPerformance;
  }

  public Object get(String name) {
    return dbPerformanceMap.get(name);
  }

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
