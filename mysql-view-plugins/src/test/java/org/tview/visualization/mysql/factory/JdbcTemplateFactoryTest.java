package org.tview.visualization.mysql.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.mysql.ServerTimezone;
import org.tview.visualization.mysql.factory.jdbc.JdbcFactory;
import org.tview.visualization.mysql.factory.jdbc.JdbcTemplateFactory;

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
      //    dbConnectionConfig.setDbName("mysql");
      JdbcTemplate jdbcTemplate = jdbcFactory.create(dbConnectionConfig);

      File file =
              new File(
                      "E:\\github\\all-view\\mysql-view-plugins\\src\\main\\resources\\mysql_5_8_collection.txt");

      InputStreamReader inputReader =
              new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
      BufferedReader bf = new BufferedReader(inputReader);
      // 按行读取字符串
      String str;

      while ((str = bf.readLine()) != null) {
          String[] split = str.split(",");

          String yes =
                  String.format(
                          "%s(\"%s\",\"%s\",%s),", split[0], split[0], split[1], split[3].equals("Yes"));
      System.out.println(yes);
    }
    bf.close();
    inputReader.close();
  }

  private boolean s(String b) {
    return b.equals("Yes");
  }
}
