package org.tview.visualization.mysql.impl;

import org.junit.jupiter.api.Test;
import org.tview.visualization.inter.db.DatabaseOperation;
import org.tview.visualization.model.db.DBInfoEntity;
import org.tview.visualization.mysql.factory.AbsConfig;
import org.tview.visualization.mysql.factory.jdbc.JdbcFactory;

import java.sql.SQLException;

class MysqlDatabaseOperationImplTest extends AbsConfig {
    JdbcFactory jdbcFactory;
    DatabaseOperation databaseOperation;

  @BeforeEach
  void init() {
    jdbcFactory = new JdbcTemplateFactory();

    DBConnectionConfig config = new DBConnectionConfig();
    config.setDbType("mysql");
    config.setHost("47.98.225.144");
    config.setPort(3306);
    config.setUsername("huifer");
    config.setPassword("a12345");
    config.setTimeZone(ServerTimezone.UTC.getValue());
    dbConnectionConfig = config;

    databaseOperation = new MysqlDatabaseOperationImpl();
  }

  @Test
  void databases() throws SQLException {
  }

    @Test
    void tableNames() {
    }

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
