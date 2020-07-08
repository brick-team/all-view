package org.tview.visualization.mysql.factory;

import org.junit.jupiter.api.Test;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.mysql.ServerTimezone;
import org.tview.visualization.mysql.cache.JdbcTemplateCache;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JdbcTemplateFactoryTest {

  @Test
  void create() throws SQLException {
    DBConnectionConfig dbConnectionConfig = new DBConnectionConfig();
    dbConnectionConfig.setDbType("mysql");
    dbConnectionConfig.setHost("47.98.225.144");
    dbConnectionConfig.setPort(3306);
    dbConnectionConfig.setUsername("huifer");
    dbConnectionConfig.setPassword("a12345");
    dbConnectionConfig.setTimeZone(ServerTimezone.UTC.getValue());
    dbConnectionConfig.setDbName("scrum");
    JdbcTemplateFactory.create(dbConnectionConfig);
    JdbcTemplateFactory.create(dbConnectionConfig);
    JdbcTemplateFactory.create(dbConnectionConfig);
    JdbcTemplateCache cache = JdbcTemplateFactory.getCache();
    System.out.println();
  }
}
