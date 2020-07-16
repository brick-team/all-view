package org.tview.visualization.app.web.redis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ScheduledFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task/scheduler")
public class TaskSchedulerController {

  protected Logger log = LoggerFactory.getLogger(TaskSchedulerController.class);
  Map<String, ScheduledFuture<?>> map = new HashMap<>();
  Map<String, List<String>> uidMap = new HashMap<>();
  @Autowired private ThreadPoolTaskScheduler threadPoolTaskScheduler;

  @GetMapping("/start/{name}")
  public void start(@PathVariable(value = "name") String name) {
    ScheduledFuture<?> schedule =
        threadPoolTaskScheduler.schedule(
            new Runnable() {
              @Override
              public void run() {
                log.info(name);
                if (uidMap.containsKey(name)) {
                  List<String> stringList = uidMap.get(name);
                  stringList.add(UUID.randomUUID().toString());
                } else {
                  List<String> u = new ArrayList<>();
                  u.add(UUID.randomUUID().toString());
                  uidMap.put(name, u);
                }
              }
            },
            new Trigger() {
              @Override
              public Date nextExecutionTime(TriggerContext triggerContext) {
                return new CronTrigger("0/5 * * * * ?").nextExecutionTime(triggerContext);
              }
            });
    map.put(name, schedule);
  }

  @GetMapping("/stop/{name}")
  public void stop(@PathVariable(value = "name") String name) {
    if (map.containsKey(name)) {
      map.get(name).cancel(true);
    }
  }
}
