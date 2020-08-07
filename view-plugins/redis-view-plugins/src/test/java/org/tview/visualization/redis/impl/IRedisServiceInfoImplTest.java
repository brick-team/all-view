package org.tview.visualization.redis.impl;

import org.junit.jupiter.api.Test;
import org.tview.visualization.inter.redis.IRedisServerInfo;
import org.tview.visualization.model.res.redis.RedisTreeInfo;
import org.tview.visualization.redis.factory.AbsRedisTemplate;

class IRedisServiceInfoImplTest extends AbsRedisTemplate {
  IRedisServerInfo redisServerInfo = new IRedisServiceInfoImpl();

  @Test
  void keyspace() {
    redisServerInfo.keyspace(this.config);
  }

  @Test
  void version() {
    System.out.println(redisServerInfo.version(this.config));
  }

  @Test
  void useMemory() {
    System.out.println(redisServerInfo.useMemory(this.config));
  }

  @Test
  void client() {
    System.out.println(redisServerInfo.client(this.config));
  }

  @Test
  void execSize() {
    System.out.println(redisServerInfo.execSize(this.config));
  }

  @Test
  void runTime() {
    System.out.println(redisServerInfo.runTime(this.config));
  }

  @Test
  void conf() {
    System.out.println(redisServerInfo.conf(this.config));
  }

  @Test
  void dataTree(){
    RedisTreeInfo asd = redisServerInfo.dataBaseList(this.config, "asd");
    System.out.println();
  }
}
