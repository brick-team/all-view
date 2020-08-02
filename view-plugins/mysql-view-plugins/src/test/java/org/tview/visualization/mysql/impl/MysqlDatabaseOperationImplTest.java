package org.tview.visualization.mysql.impl;

import org.junit.jupiter.api.Test;
import org.tview.visualization.inter.db.DatabaseOperation;
import org.tview.visualization.model.db.DBInfoEntity;
import org.tview.visualization.mysql.factory.AbsMysqlConfig;
import org.tview.visualization.mysql.factory.jdbc.JdbcFactory;

import java.sql.SQLException;

class MysqlDatabaseOperationImplTest extends AbsMysqlConfig {
  JdbcFactory jdbcFactory;
  DatabaseOperation databaseOperation;

  @Test
  void databases() throws SQLException {}

  @Test
  void tableNames() {}

  @Test
  void dataSourceState() throws SQLException {
    DBInfoEntity dbInfoEntity = databaseOperation.dataSourceState(getConf());
    System.out.println();
  }

  @Test
  void createDatabase() {}

  @Test
  void state() {}
}
