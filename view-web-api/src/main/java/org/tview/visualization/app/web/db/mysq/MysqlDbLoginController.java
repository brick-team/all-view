package org.tview.visualization.app.web.db.mysq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.app.lisenter.ConfigInterface;
import org.tview.visualization.app.lisenter.MySqlPerformancePerformanceListener;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.mysql.ServerTimezone;
import org.tview.visualization.model.label.AbsConfig;
import org.tview.visualization.model.res.ResultVO;

/**
 * mysql 数据库登录
 */
@RestController
@RequestMapping("/mysql/login")
public class MysqlDbLoginController {

  @Autowired
  MySqlPerformancePerformanceListener mySqlPerformanceListener = new MySqlPerformancePerformanceListener();

  @PostMapping("/start/{name}")
  public ResultVO start(
      @PathVariable(value = "name") String name) {
    mySqlPerformanceListener.createWork(name, new ConfigInterface() {
      @Override
      public AbsConfig get() {
        return config();
      }
    });
    return new ResultVO("ok", "ok", 200);
  }

  private DBConnectionConfig config() {
    DBConnectionConfig config = new DBConnectionConfig();
    config.setDbType("mysql");
    config.setHost("47.98.225.144");
    config.setPort(3306);
    config.setUsername("huifer");
    config.setPassword("a12345");
    config.setTimeZone(ServerTimezone.UTC.getValue());
    return config;
  }


  @PostMapping("/stop/{name}")
  public ResultVO stop(@PathVariable String name) {
    mySqlPerformanceListener.remove(name);
    return new ResultVO("ok", "ok", 200);
  }


  @PutMapping("/get/{name}")
  public ResultVO get(@PathVariable String name) {
    return new ResultVO("ok", this.mySqlPerformanceListener.get(name), 200);
  }
}
