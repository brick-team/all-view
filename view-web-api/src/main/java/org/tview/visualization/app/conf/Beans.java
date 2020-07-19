package org.tview.visualization.app.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Component
public class Beans {
  @Bean
  public ThreadPoolTaskScheduler threadPoolTaskScheduler() {

    ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    threadPoolTaskScheduler.setPoolSize(10);
    threadPoolTaskScheduler.initialize();
    return threadPoolTaskScheduler;
  }
}
