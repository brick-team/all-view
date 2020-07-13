package org.tview.visualization.mysql.factory;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.mysql.factory.jdbc.JdbcTemplateCreate;
import org.tview.visualization.mysql.factory.jdbc.MysqlJdbcTemplateCreate;

import java.sql.SQLException;

class MysqlJdbcTemplateCreateTest extends AbsConfig {
    private static final Logger LOG = LoggerFactory.getLogger(MysqlJdbcTemplateCreateTest.class);
    JdbcTemplateCreate mysql = new MysqlJdbcTemplateCreate();

    @Test
    public void genUrlTest() throws SQLException {
        DBConnectionConfig conf = getConf();
        conf.setDbName("scrum");
        JdbcTemplate jdbcTemplate = mysql.create(conf);
    }
}
