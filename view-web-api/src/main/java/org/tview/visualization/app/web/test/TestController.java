package org.tview.visualization.app.web.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.app.listener.ConfigInterface;
import org.tview.visualization.app.listener.NewMysqlPerformance;
import org.tview.visualization.app.listener.NewRedisPerformance;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.mysql.ServerTimezone;
import org.tview.visualization.model.enums.PerformanceEnums;
import org.tview.visualization.model.label.AbsConfig;
import org.tview.visualization.model.redis.RedisConnectionConfig;

@RestController
@RequestMapping("/G")
public class TestController {

  @Autowired private NewMysqlPerformance newMysqlPerformance;
  @Autowired private NewRedisPerformance newRedisPerformance;

  @GetMapping("/tmysql")
  public void tmysql() {
    DBConnectionConfig config = new DBConnectionConfig();
    config.setDbType("mysql");
    config.setHost("47.98.225.144");
    config.setPort(3306);
    config.setUsername("huifer");
    config.setPassword("a12345");
    config.setTimeZone(ServerTimezone.UTC.getValue());
    this.newMysqlPerformance.createWork(
        "test",
        new ConfigInterface() {
          @Override
          public AbsConfig get() {
            return config;
          }
        },
        this.newMysqlPerformance,
        PerformanceEnums.MYSQL);
  }

  @GetMapping("/getm")
  public Object get() {
    return this.newMysqlPerformance.get("test", PerformanceEnums.MYSQL);
  }

  @GetMapping("/tredis")
  public void tredis() {
    RedisConnectionConfig config = new RedisConnectionConfig();
    config.setHost("127.0.0.1");
    config.setPort(32768);
    config.setPwd("");
    config.setDbIndex(1);
    newRedisPerformance.createWork(
        "tr",
        new ConfigInterface() {
          @Override
          public AbsConfig get() {
            return config;
          }
        },
        this.newRedisPerformance,
        PerformanceEnums.REDIS);
  }

  @GetMapping("/getr")
  public Object getr() {
    return this.newRedisPerformance.get("tr", PerformanceEnums.REDIS);
  }
}
