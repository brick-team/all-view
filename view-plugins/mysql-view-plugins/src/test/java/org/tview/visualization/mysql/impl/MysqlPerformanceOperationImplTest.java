package org.tview.visualization.mysql.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.tview.visualization.inter.db.IDBPerformanceOperation;
import org.tview.visualization.mysql.factory.AbsMysqlConfig;
import org.tview.visualization.page.PageUtils;

class MysqlPerformanceOperationImplTest extends AbsMysqlConfig {

  IDBPerformanceOperation performanceOperation = new MysqlPerformanceOperationImpl();


  @Test
  void qps() throws SQLException {
    BigDecimal qps = performanceOperation.qps(this.getConf());
    System.out.println(qps);
  }

  @Test
  void tps() throws SQLException {
    BigDecimal tps = performanceOperation.tps(this.getConf());
    System.out.println(tps);
  }

  @Test
  void keyBufferRead() throws SQLException {
    BigDecimal bigDecimal = performanceOperation.keyBufferRead(this.getConf());
    System.out.println(bigDecimal);
  }

  @Test
  void keyBufferWrite() throws SQLException {
    System.out.println(performanceOperation.keyBufferWrite(this.getConf()));
  }

  @Test
  void innoDBBuffer() throws SQLException {
    System.out.println(performanceOperation.innoDBBuffer(this.getConf()));
  }

  @Test
  void queryCache() {
  }

  @Test
  void tableCache() {
  }

  @Test
  void threadCache() {
  }

  @Test
  void slaveStatus() throws SQLException {
    this.performanceOperation.slaveStatus(this.getConf());
  }

  @Test
  void tmpTable() {
  }

  @Test
  void binlogCache() {
  }

  @Test
  void innodbLogWaits() {
  }
}