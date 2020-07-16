package org.tview.visualization.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.model.res.ResultVO;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class})
@RequestMapping("/")
@RestController
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @GetMapping("/health")
  public ResultVO dod() {
    return new ResultVO("OK", "ok", 200);
  }
}
