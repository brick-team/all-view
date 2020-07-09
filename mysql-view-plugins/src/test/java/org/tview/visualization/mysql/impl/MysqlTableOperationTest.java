package org.tview.visualization.mysql.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tview.visualization.inter.db.TableOperation;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.TableDataEntity;
import org.tview.visualization.model.db.mysql.ServerTimezone;
import org.tview.visualization.model.req.PageVO;
import org.tview.visualization.mysql.factory.JdbcFactory;
import org.tview.visualization.mysql.factory.JdbcTemplateFactory;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class MysqlTableOperationTest {
    JdbcFactory jdbcFactory;
    DBConnectionConfig dbConnectionConfig;
    TableOperation tableOperation;

    @BeforeEach
    void init() {
        jdbcFactory = new JdbcTemplateFactory();

        DBConnectionConfig config = new DBConnectionConfig();
        config.setDbType("mysql");
        config.setHost("47.98.225.144");
        config.setPort(3306);
        config.setUsername("huifer");
        config.setPassword("a12345");
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
    void tableInfo() {
    }

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
    void createTable() {
    }

    @Test
    void deleteTable() {
    }
}
