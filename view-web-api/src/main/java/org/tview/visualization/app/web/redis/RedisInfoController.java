package org.tview.visualization.app.web.redis;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.inter.redis.IRedisServerInfo;
import org.tview.visualization.model.redis.EasyRedisInfo;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.model.res.ResultVO;
import org.tview.visualization.redis.impl.IRedisServiceInfoImpl;

@RestController
@RequestMapping("/redis")
public class RedisInfoController {
  IRedisServerInfo redisServerInfo = new IRedisServiceInfoImpl();

  @PostMapping("/easy_info")
  public ResultVO easyInfo(@RequestBody RedisConnectionConfig config) {
    EasyRedisInfo easyRedisInfo = new EasyRedisInfo();
    easyRedisInfo.setVersion(redisServerInfo.version(config));
    easyRedisInfo.setUseMemory(redisServerInfo.useMemory(config));
    easyRedisInfo.setClient(redisServerInfo.client(config));
    easyRedisInfo.setExecSize(redisServerInfo.execSize(config));
    easyRedisInfo.setRunTime(redisServerInfo.runTime(config));

    return new ResultVO("OK", easyRedisInfo, 200);
  }

  @PostMapping("/server")
  public ResultVO server(@RequestBody RedisConnectionConfig config) {
    return new ResultVO("OK", redisServerInfo.server(config), 200);
  }

  @PostMapping("/clients")
  public ResultVO clients(@RequestBody RedisConnectionConfig config) {
    return new ResultVO("OK", redisServerInfo.clients(config), 200);
  }

  @PostMapping("/memory")
  public ResultVO memory(@RequestBody RedisConnectionConfig config) {
    return new ResultVO("OK", redisServerInfo.memory(config), 200);
  }

  @PostMapping("/persistence")
  public ResultVO persistence(@RequestBody RedisConnectionConfig config) {
    return new ResultVO("OK", redisServerInfo.persistence(config), 200);
  }

  @PostMapping("/stats")
  public ResultVO stats(@RequestBody RedisConnectionConfig config) {
    return new ResultVO("OK", redisServerInfo.stats(config), 200);
  }

  @PostMapping("/replication")
  public ResultVO replication(@RequestBody RedisConnectionConfig config) {
    return new ResultVO("OK", redisServerInfo.replication(config), 200);
  }

  @PostMapping("/cpu")
  public ResultVO cpu(@RequestBody RedisConnectionConfig config) {
    return new ResultVO("OK", redisServerInfo.cpu(config), 200);
  }

  @PostMapping("/cluster")
  public ResultVO cluster(@RequestBody RedisConnectionConfig config) {
    return new ResultVO("OK", redisServerInfo.cluster(config), 200);
  }

  @PostMapping("/keyspace")
  public ResultVO keyspace(@RequestBody RedisConnectionConfig config) {
    return new ResultVO("OK", redisServerInfo.keyspace(config), 200);
  }
}
