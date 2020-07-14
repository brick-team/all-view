package org.tview.visualization.redis.task;

import org.junit.jupiter.api.Test;
import org.tview.visualization.redis.factory.AbsRedisTemplate;

class MemoryTaskTest extends AbsRedisTemplate {

  MemoryTask memoryTask = new MemoryTask();

  @Test
  void task() throws InterruptedException {
    memoryTask.task(this.config);

    Thread.sleep(12 * 1000);
    memoryTask.shutdown();
    System.out.println(memoryTask.get());

  }
}