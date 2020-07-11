package org.tview.visualization.mysql.factory;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.mysql.ServerTimezone;
import org.tview.visualization.model.db.mysql.ShowStatusEntity;
import org.tview.visualization.mysql.factory.jdbc.JdbcFactory;
import org.tview.visualization.mysql.factory.jdbc.JdbcTemplateFactory;
import org.tview.visualization.mysql.row.ShowStatusEntityRowMapper;

class JdbcTemplateFactoryTest {
  JdbcFactory jdbcFactory;

  @BeforeEach
  void init() {
    jdbcFactory = new JdbcTemplateFactory();
  }

  @Test
  void create() throws Exception {
    DBConnectionConfig dbConnectionConfig = new DBConnectionConfig();
    dbConnectionConfig.setDbType("mysql");
    dbConnectionConfig.setHost("47.98.225.144");
    dbConnectionConfig.setPort(3306);
    dbConnectionConfig.setUsername("huifer");
    dbConnectionConfig.setPassword("a12345");
    dbConnectionConfig.setTimeZone(ServerTimezone.UTC.getValue());
    dbConnectionConfig.setDbName("scrum");
    JdbcTemplate jdbcTemplate = jdbcFactory.create(dbConnectionConfig);

    String version = jdbcTemplate.queryForObject("select version() as version ", String.class);
    List<ShowStatusEntity> query =
        jdbcTemplate.query("show variables like '%datadir%'", new ShowStatusEntityRowMapper());
    String da =
        Optional.of(query)
            .orElseThrow(() -> new IllegalArgumentException("没有数据库地址"))
            .get(0)
            .getValue();

    List<ShowStatusEntity> query1 =
        jdbcTemplate.query("show variables ;", new ShowStatusEntityRowMapper());
    System.out.println();
  }
}
