package org.tview.visualization.mysql.impl;

import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tview.visualization.inter.db.DatabaseOperation;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.DBInfoEntity;
import org.tview.visualization.model.db.mysql.ServerTimezone;
import org.tview.visualization.mysql.factory.jdbc.JdbcFactory;
import org.tview.visualization.mysql.factory.jdbc.JdbcTemplateFactory;

class MysqlDatabaseOperationImplTest {
  JdbcFactory jdbcFactory;
  DBConnectionConfig dbConnectionConfig;
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
    databaseOperation.createDatabase(dbConnectionConfig, "zzz");
  }

  @Test
  void tableNames() {}

  @Test
  void dataSourceState() throws SQLException {
    DBInfoEntity dbInfoEntity = databaseOperation.dataSourceState(dbConnectionConfig);
    System.out.println();
  }

  @Test
  void createDatabase() {}

  @Test
  void state() {}
}
