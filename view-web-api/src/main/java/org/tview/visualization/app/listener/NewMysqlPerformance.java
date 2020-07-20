package org.tview.visualization.app.listener;

import java.sql.SQLException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tview.visualization.cache.impl.FifoCache;
import org.tview.visualization.inter.db.IDBPerformanceOperation;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.enums.PerformanceEnums;
import org.tview.visualization.model.res.DbPerformance;
import org.tview.visualization.mysql.cache.MysqlPerformanceCache;
import org.tview.visualization.mysql.impl.MysqlPerformanceOperationImpl;

@Service
public class NewMysqlPerformance extends AbsPerformanceListener implements IListenerWork {

  protected Logger log = LoggerFactory.getLogger(NewMysqlPerformance.class);

  IDBPerformanceOperation performanceOperation = new MysqlPerformanceOperationImpl();
  private DBConnectionConfig config;

  private String name;

  @Override
  public void createWork(String name, ConfigInterface absConfig, IListenerWork work,
      PerformanceEnums performanceEnums) {
    log.info("开始执行mysql的性能监控,name=[{}]", name);
    this.name = name;
    config = (DBConnectionConfig) absConfig.get();
    if (work == null) {
      super.createWork(name, absConfig, this, performanceEnums);
    } else {
      super.createWork(name, absConfig, work, performanceEnums);
    }
  }

  @Override
  public void work() throws SQLException {
    synchronized (this) {
      String time = formatDate();
      DbPerformance performance = performance(config);
      Map<String, FifoCache> cache = this.getCache(PerformanceEnums.MYSQL);
      if (!cache.containsKey(name)) {
        System.out.println("not key");
        log.info("开始记录 mysql 性能指标,time=[{}]", time);
        MysqlPerformanceCache mysqlPerformanceCache = new MysqlPerformanceCache(
            getPerformanceConfiguration().getMysql().getSize());
        mysqlPerformanceCache.put(time, performance);
        cache.put(name, mysqlPerformanceCache);
      } else {
        System.out.println("has key");
        log.info("开始记录 mysql 性能指标,time=[{}]", time);
        cache.get(name).put(time, performance);
      }
    }
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
}
