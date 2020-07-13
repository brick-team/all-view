package org.tview.visualization.mysql.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tview.visualization.inter.db.TableOperation;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.TableDataEntity;
import org.tview.visualization.model.db.mysql.ServerTimezone;
import org.tview.visualization.model.req.PageVO;
import org.tview.visualization.mysql.factory.AbsConfig;
import org.tview.visualization.mysql.factory.jdbc.JdbcFactory;
import org.tview.visualization.mysql.factory.jdbc.JdbcTemplateFactory;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class MysqlTableOperationTest extends AbsConfig {
  JdbcFactory jdbcFactory;
  DBConnectionConfig dbConnectionConfig;
  TableOperation tableOperation;

  @BeforeEach
  void initc() {
    jdbcFactory = new JdbcTemplateFactory();
    DBConnectionConfig config = getConf();

    config.setDbName("at15");
    config.setTimeZone(ServerTimezone.UTC.getValue());
    dbConnectionConfig = config;

    tableOperation = new MysqlTableOperation();
  }

  @Test
  void findAll() throws SQLException {
    TableDataEntity t_project =
        tableOperation.findAll(dbConnectionConfig, "t_project", new PageVO(0, 2));
    System.out.println();
  }

  @Test
  void tableInfo() {}

  @Test
  void deleteOnceData() throws SQLException {
    tableOperation.deleteOnceData(dbConnectionConfig, "t_project", 3);
  }

  @Test
  void createOnceData() throws SQLException {
    Map<String, Object> map = new HashMap<>();
    map.put("project_name", "zzzz");
    map.put("size", 1);
    map.put("data", new Date());
    tableOperation.createOnceData(dbConnectionConfig, "t_project", map);
  }

  @Test
  void createTable() {}

  @Test
  void deleteTable() {}
}
