package org.tview.visualization.app.web.redis;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.inter.redis.RedisKeysOperation;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.model.res.ResultVO;
import org.tview.visualization.redis.impl.RedisKeysService;

@RestController
@RequestMapping("/redis/key")
public class RedisKeysController {

  RedisKeysOperation keysOperation = new RedisKeysService();

  @PostMapping("/info")
  public ResultVO info(@RequestBody RedisConnectionConfig config, @RequestBody String keyRegion) {
    return new ResultVO("ok", keysOperation.keys(config, keyRegion), 200);
  }
}
