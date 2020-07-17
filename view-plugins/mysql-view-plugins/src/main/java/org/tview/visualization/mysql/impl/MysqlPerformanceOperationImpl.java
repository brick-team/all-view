package org.tview.visualization.mysql.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.tview.visualization.inter.db.IDBPerformanceOperation;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.mysql.MysqlSlaveStatus;
import org.tview.visualization.model.db.mysql.ShowStatusEntity;
import org.tview.visualization.mysql.MysqlConstantSql;
import org.tview.visualization.mysql.factory.jdbc.JdbcFactory;
import org.tview.visualization.mysql.factory.jdbc.JdbcTemplateFactory;
import org.tview.visualization.mysql.row.MysqlSlaveStatusRowMapper;
import org.tview.visualization.mysql.row.ShowStatusEntityRowMapper;

public class MysqlPerformanceOperationImpl implements IDBPerformanceOperation {

  JdbcFactory jdbcFactory = new JdbcTemplateFactory();

  /**
   * qps 计算： questions / uptime
   *
   * @param config
   * @return
   */
  @Override
  public BigDecimal qps(DBConnectionConfig config) throws SQLException {
    JdbcTemplate jdbcTemplate = jdbcFactory.create(config);
    ShowStatusEntity question = jdbcTemplate
        .queryForObject(MysqlConstantSql.Question, new ShowStatusEntityRowMapper());
    ShowStatusEntity uptime = jdbcTemplate
        .queryForObject(MysqlConstantSql.uptime, new ShowStatusEntityRowMapper());
    return new BigDecimal(question.getValue())
        .divide(new BigDecimal(uptime.getValue()), 8, RoundingMode.DOWN);
  }

  /**
   * TPS 计算: (Com_commit + Com_rollback) / uptime
   *
   * @param config
   * @return
   */
  @Override
  public BigDecimal tps(DBConnectionConfig config) throws SQLException {
    JdbcTemplate jdbcTemplate = jdbcFactory.create(config);
    ShowStatusEntity commit = jdbcTemplate
        .queryForObject(MysqlConstantSql.Com_commit, new ShowStatusEntityRowMapper());
    ShowStatusEntity rollback = jdbcTemplate
        .queryForObject(MysqlConstantSql.Com_rollback, new ShowStatusEntityRowMapper());
    ShowStatusEntity uptime = jdbcTemplate
        .queryForObject(MysqlConstantSql.uptime, new ShowStatusEntityRowMapper());

    return new BigDecimal(commit.getValue()).add(new BigDecimal(rollback.getValue()))
        .divide(new BigDecimal(uptime.getValue()), 8, RoundingMode.DOWN);
  }

  /**
   * key buffer 命中率:(1-key_reads /key_read_requests) * 100%
   *
   * @param config
   * @return
   */
  @Override
  public BigDecimal keyBufferRead(DBConnectionConfig config) throws SQLException {
    JdbcTemplate jdbcTemplate = jdbcFactory.create(config);
    ShowStatusEntity key_read_requests = jdbcTemplate
        .queryForObject(MysqlConstantSql.key_read_requests, new ShowStatusEntityRowMapper());

    ShowStatusEntity key_reads = jdbcTemplate
        .queryForObject(MysqlConstantSql.key_reads, new ShowStatusEntityRowMapper());
    if (key_read_requests.getValue().equals("0") || key_reads.equals("0")) {
      return BigDecimal.ZERO;
    }
    return BigDecimal.ONE.subtract(
        new BigDecimal(key_reads.getValue())
            .divide(new BigDecimal(key_read_requests.getValue()), 8, RoundingMode.DOWN))
        .multiply(new BigDecimal("100"));
  }

  /**
   * key buffer 命中率: (1-key_writes /key_write_requests) * 100%
   *
   * @param config
   * @return
   */
  @Override
  public BigDecimal keyBufferWrite(DBConnectionConfig config) throws SQLException {
    JdbcTemplate jdbcTemplate = jdbcFactory.create(config);
    ShowStatusEntity key_write_requests = jdbcTemplate
        .queryForObject(MysqlConstantSql.key_write_requests, new ShowStatusEntityRowMapper());

    ShowStatusEntity key_writes = jdbcTemplate
        .queryForObject(MysqlConstantSql.key_writes, new ShowStatusEntityRowMapper());
    if (key_write_requests.getValue().equals("0") || key_writes.equals("0")) {
      return BigDecimal.ZERO;
    }
    return BigDecimal.ONE.subtract(
        new BigDecimal(key_writes.getValue())
            .divide(new BigDecimal(key_write_requests.getValue()), 8, RoundingMode.DOWN))
        .multiply(new BigDecimal("100"));
  }

  /**
   * InnoDB Buffer命中率: (1 -innodb_buffer_pool_reads / innodb_buffer_pool_read_requests) * 100%
   *
   * @param config
   * @return
   */
  @Override
  public BigDecimal innoDBBuffer(DBConnectionConfig config) throws SQLException {
    JdbcTemplate jdbcTemplate = jdbcFactory.create(config);
    ShowStatusEntity innodb_buffer_pool_reads = jdbcTemplate
        .queryForObject(MysqlConstantSql.innodb_buffer_pool_reads, new ShowStatusEntityRowMapper());

    ShowStatusEntity innodb_buffer_pool_read_requests = jdbcTemplate
        .queryForObject(MysqlConstantSql.innodb_buffer_pool_read_requests,
            new ShowStatusEntityRowMapper());
    if (innodb_buffer_pool_reads.getValue().equals("0") || innodb_buffer_pool_read_requests
        .equals("0")) {
      return BigDecimal.ZERO;
    }
    return BigDecimal.ONE.subtract(
        new BigDecimal(innodb_buffer_pool_reads.getValue())
            .divide(new BigDecimal(innodb_buffer_pool_read_requests.getValue()), 8,
                RoundingMode.DOWN))
        .multiply(new BigDecimal("100"));

  }

  /**
   * Query Cache命中率:  (Qcahce_hits /(Qcache_hits + Qcache_inserts )) * 100%;
   *
   * @param config
   * @return
   */
  @Override
  public BigDecimal queryCache(DBConnectionConfig config) throws SQLException {
    JdbcTemplate jdbcTemplate = jdbcFactory.create(config);
    ShowStatusEntity qcacheHits = jdbcTemplate
        .queryForObject(MysqlConstantSql.Qcache_hits, new ShowStatusEntityRowMapper());

    ShowStatusEntity qcacheInserts = jdbcTemplate
        .queryForObject(MysqlConstantSql.Qcache_inserts,
            new ShowStatusEntityRowMapper());

    try {
      return new BigDecimal(qcacheHits.getValue()).divide(
          new BigDecimal(qcacheHits.getValue()).add(new BigDecimal(qcacheInserts.getValue())), 8,
          RoundingMode.DOWN
      );
    } catch (Exception e) {
      return BigDecimal.ZERO;
    }
  }

  /**
   * show global  status like 'open%';
   *
   * @param config
   * @return
   */
  @Override
  public BigDecimal tableCache(DBConnectionConfig config) {
    return BigDecimal.ZERO;
  }

  /**
   * Thread Cache 命中率: (1 - Threads_created /connections ) * 100%
   *
   * @param config
   * @return
   */
  @Override
  public BigDecimal threadCache(DBConnectionConfig config) throws SQLException {
    JdbcTemplate jdbcTemplate = jdbcFactory.create(config);
    ShowStatusEntity Threads_created = jdbcTemplate
        .queryForObject(MysqlConstantSql.Threads_created, new ShowStatusEntityRowMapper());

    ShowStatusEntity connections = jdbcTemplate
        .queryForObject(MysqlConstantSql.connections,
            new ShowStatusEntityRowMapper());
    return BigDecimal.ONE.subtract(new BigDecimal(Threads_created.getValue())
        .divide(new BigDecimal(connections.getValue()), 8, RoundingMode.DOWN))
        .multiply(new BigDecimal("100"));

  }

  /**
   * 复制延时量 show slave status
   *
   * @param config
   * @return
   */
  @Override
  public MysqlSlaveStatus slaveStatus(DBConnectionConfig config) throws SQLException {
    JdbcTemplate jdbcTemplate = jdbcFactory.create(config);

    SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(MysqlConstantSql.slave_status);
    while (sqlRowSet.next()) {

      String slave_io_state = sqlRowSet.getString("Slave_IO_State");
      System.out.println();
    }
    return null;
  }

  /**
   * 临时表状况 Created_tmp_disk_tables/Created_tmp_tables
   *
   * @param config
   * @return
   */
  @Override
  public BigDecimal tmpTable(DBConnectionConfig config) throws SQLException {

    JdbcTemplate jdbcTemplate = jdbcFactory.create(config);
    ShowStatusEntity Created_tmp_disk_tables = jdbcTemplate
        .queryForObject(MysqlConstantSql.Created_tmp_disk_tables, new ShowStatusEntityRowMapper());

    ShowStatusEntity Created_tmp_tables = jdbcTemplate
        .queryForObject(MysqlConstantSql.Created_tmp_tables,
            new ShowStatusEntityRowMapper());
    if (Created_tmp_disk_tables.getValue().equals("0") || Created_tmp_tables
        .equals("0")) {
      return BigDecimal.ZERO;
    }

    return new BigDecimal(Created_tmp_disk_tables.getValue())
        .divide(new BigDecimal(Created_tmp_tables.getValue()), 8, RoundingMode.DOWN);
  }

  /**
   * Binlog Cache 使用状况
   *
   * @param config
   * @return
   */
  @Override
  public BigDecimal binlogCache(DBConnectionConfig config) throws SQLException {
    JdbcTemplate jdbcTemplate = jdbcFactory.create(config);
    ShowStatusEntity Binlog_cache = jdbcTemplate
        .queryForObject(MysqlConstantSql.Binlog_cache, new ShowStatusEntityRowMapper());

    return new BigDecimal(Binlog_cache.getValue());
  }

  /**
   * Innodb_log_waits 量
   *
   * @param config
   * @return
   */
  @Override
  public BigDecimal innodbLogWaits(DBConnectionConfig config) throws SQLException {
    JdbcTemplate jdbcTemplate = jdbcFactory.create(config);
    ShowStatusEntity Innodb_log_waits = jdbcTemplate
        .queryForObject(MysqlConstantSql.Innodb_log_waits, new ShowStatusEntityRowMapper());
    return new BigDecimal(Innodb_log_waits.getValue());
  }
}
