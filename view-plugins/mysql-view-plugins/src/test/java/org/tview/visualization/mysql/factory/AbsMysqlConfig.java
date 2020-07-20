package org.tview.visualization.mysql.factory;

import org.junit.jupiter.api.BeforeEach;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.mysql.ServerTimezone;

public class AbsMysqlConfig {
  DBConnectionConfig conf = new DBConnectionConfig();

  @BeforeEach
  public void init() {
    DBConnectionConfig config = new DBConnectionConfig();
    config.setDbType("mysql");
    config.setHost("47.98.225.144");
    config.setPort(3306);
    config.setUsername("huifer");
    config.setPassword("a12345");
    config.setTimeZone(ServerTimezone.UTC.getValue());
    conf = config;
  }

  public DBConnectionConfig getConf() {
    return conf;
  }
}
