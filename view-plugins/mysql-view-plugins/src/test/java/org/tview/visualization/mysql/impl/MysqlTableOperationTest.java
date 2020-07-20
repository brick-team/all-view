package org.tview.visualization.mysql.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tview.visualization.inter.db.TableOperation;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.TableDataEntity;
import org.tview.visualization.model.db.mysql.ServerTimezone;
import org.tview.visualization.model.req.PageVO;
import org.tview.visualization.mysql.factory.AbsMysqlConfig;
import org.tview.visualization.mysql.factory.jdbc.JdbcFactory;
import org.tview.visualization.mysql.factory.jdbc.JdbcTemplateFactory;

class MysqlTableOperationTest extends AbsMysqlConfig {
  JdbcFactory jdbcFactory;
  DBConnectionConfig dbConnectionConfig;
  TableOperation tableOperation;

  @BeforeEach
  void initc() {
    jdbcFactory = new JdbcTemplateFactory();
    DBConnectionConfig config = getConf();

    config.setDbName("scrum");
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

  @Test
  void testChangeFiled() throws SQLException {
    //    List<CreateRowParams> changeRow = new ArrayList<>();
    //    CreateRowParams title = new CreateRowParams();
    //    title.setOldName("new_title");
    //    title.setName("title");
    //    title.setType("varchar");
    //    title.setLength(250);
    //    title.setScale(0);
    //    title.setNullable(false);
    //    title.setKey(false);
    //    title.setContent("标题");
    //    title.setAutoAdd(false);
    //    title.setDefaultValue("title_value");
    //    title.setUnsigned(false);
    //    CreateRowParams price = new CreateRowParams();
    //    price.setOldName("price");
    //    price.setName("total_price");
    //    price.setType("decimal");
    //    price.setLength(10);
    //    price.setScale(2);
    //    price.setNullable(true);
    //    price.setKey(false);
    //    price.setContent("价格");
    //    price.setAutoAdd(false);
    //    price.setDefaultValue("1.0");
    //    price.setUnsigned(true);
    //    changeRow.add(price);
    //    changeRow.add(title);
    //    List<CreateRowParams> addRow = new ArrayList<>();
    //    CreateRowParams t1 = new CreateRowParams();
    //    t1.setName("t1");
    //    t1.setType("varchar");
    //    t1.setLength(3);
    //    t1.setScale(0);
    //    t1.setNullable(false);
    //    t1.setKey(false);
    //    t1.setContent("");
    //    t1.setAutoAdd(false);
    //    t1.setDefaultValue("");
    //    t1.setUnsigned(false);
    //
    //    CreateRowParams t2 = new CreateRowParams();
    //    t2.setName("wi");
    //    t2.setType("decimal");
    //    t2.setLength(3);
    //    t2.setScale(2);
    //    t2.setNullable(false);
    //    t2.setKey(false);
    //    t2.setContent("");
    //    t2.setAutoAdd(false);
    //    t2.setDefaultValue("1.0");
    //    t2.setUnsigned(false);
    //
    //    addRow.add(t1);
    //    addRow.add(t2);
    //
    //    tableOperation.changeFiled(this.dbConnectionConfig,
    //        "issue", changeRow, addRow);
    jdbcFactory
        .create(this.dbConnectionConfig)
        .execute(
            "ALTER TABLE `issue` CHANGE `price` `total_price` decimal ( 10,2 ) UNSIGNED   DEFAULT '1.0' COMMENT '价格';");
  }

  @Test
  void testShowTableSql() throws SQLException {
    String label = tableOperation.getCreateTableSql(this.getConf(), "label");
  }
}
