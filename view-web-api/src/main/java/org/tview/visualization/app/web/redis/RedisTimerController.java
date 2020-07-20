package org.tview.visualization.app.web.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.app.listener.ConfigInterface;
import org.tview.visualization.app.listener.RedisMemoryPerformanceListener;
import org.tview.visualization.model.label.AbsConfig;
import org.tview.visualization.model.redis.RedisConnectionConfig;

@RestController
@RequestMapping("/redis/timer")
public class RedisTimerController {

  @Autowired
  private RedisMemoryPerformanceListener redisMemoryListener;

  @GetMapping("/start/{name}")
  public void start(@PathVariable(value = "name") String name) {

    RedisConnectionConfig config = new RedisConnectionConfig();
    config.setHost("127.0.0.1");
    config.setPort(32768);
    config.setPwd("");
    config.setDbIndex(1);
    redisMemoryListener.createWork(name, new ConfigInterface() {
      @Override
      public AbsConfig get() {
        return config;
      }
    },null);
  }

  @GetMapping("/stop/{name}")
  public void stop(@PathVariable(value = "name") String name) {
    redisMemoryListener.remove(name);
  }

  @GetMapping("/get/{name}")
  public Object get(@PathVariable(value = "name") String name) {
    return redisMemoryListener.get(name);
  }
}
