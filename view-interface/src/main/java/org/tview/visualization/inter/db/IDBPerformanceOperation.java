package org.tview.visualization.inter.db;

import java.math.BigDecimal;
import java.sql.SQLException;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.mysql.MysqlSlaveStatus;

public interface IDBPerformanceOperation {

  /**
   * qps 计算： questions / uptime
   *
   * @param config
   * @return
   */
  BigDecimal qps(DBConnectionConfig config) throws SQLException;

  /**
   * TPS 计算: (Com_commit + Com_rollback) / uptime
   *
   * @param config
   * @return
   */
  BigDecimal tps(DBConnectionConfig config) throws SQLException;

  /**
   * key buffer 命中率:(1-key_reads /key_read_requests) * 100%
   *
   * @param config
   * @return
   */
  BigDecimal keyBufferRead(DBConnectionConfig config) throws SQLException;

  /**
   * key buffer 命中率: (1-key_writes /key_write_requests) * 100%
   *
   * @param config
   * @return
   */
  BigDecimal keyBufferWrite(DBConnectionConfig config) throws SQLException;


  /**
   * InnoDB Buffer命中率: (1 -innodb_buffer_pool_reads / innodb_buffer_pool_read_requests) * 100%
   *
   * @param config
   * @return
   */
  BigDecimal innoDBBuffer(DBConnectionConfig config) throws SQLException;


  /**
   * Query Cache命中率:  (Qcahce_hits /(Qcache_hits + Qcache_inserts )) * 100%;
   *
   * @return
   */
  BigDecimal queryCache(DBConnectionConfig config) throws SQLException;


  /**
   * show global  status like 'open%';
   *
   * @return
   */
  BigDecimal tableCache(DBConnectionConfig config);

  /**
   * Thread Cache 命中率: (1 - Threads_created /connections ) * 100%
   *
   * @return
   */
  BigDecimal threadCache(DBConnectionConfig config) throws SQLException;


  /**
   * 复制延时量
   *
   * @return
   */
  MysqlSlaveStatus slaveStatus(DBConnectionConfig config) throws SQLException;


  /**
   * 临时表状况 Created_tmp_disk_tables/Created_tmp_tables
   *
   * @return
   */
  BigDecimal tmpTable(DBConnectionConfig config) throws SQLException;


  /**
   * Binlog Cache 使用状况
   *
   * @return
   */
  BigDecimal binlogCache(DBConnectionConfig config) throws SQLException;

  /**
   * Innodb_log_waits 量
   *
   * @return
   */
  BigDecimal innodbLogWaits(DBConnectionConfig config) throws SQLException;
}

