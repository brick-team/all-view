package org.tview.visualization.mysql.factory;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.mysql.ServerTimezone;

import java.sql.SQLException;

class MysqlJdbcTemplateCreateTest {
    JdbcTemplateCreate mysql = new MysqlJdbcTemplateCreate();
    DBConnectionConfig config;
    private static final Logger LOG = LoggerFactory.getLogger(MysqlJdbcTemplateCreateTest.class);

    @BeforeEach
    public void conf() {
        DBConnectionConfig dbConnectionConfig = new DBConnectionConfig();
        dbConnectionConfig.setDbType("mysql");
        dbConnectionConfig.setHost("47.98.225.144");
        dbConnectionConfig.setPort(3306);
        dbConnectionConfig.setUsername("huifer");
        dbConnectionConfig.setPassword("a12345");
        dbConnectionConfig.setTimeZone(ServerTimezone.UTC.getValue());
        dbConnectionConfig.setDbName("scrum");
        config = dbConnectionConfig;
    }

    @Test
    public void genUrlTest() throws SQLException {
        JdbcTemplate jdbcTemplate = mysql.create(config);
    }

}