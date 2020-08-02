package org.tview.visualization.app.web.redis.dt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.inter.redis.RedisZSetOperation;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.model.res.ResultVO;
import org.tview.visualization.redis.impl.RedisZSetOperationImpl;

@RestController
@RequestMapping("/redis/zset")
public class RedisZsetController {

  RedisZSetOperation zSetOperation = new RedisZSetOperationImpl();

  @PostMapping("/add")
  public ResultVO add(RedisConnectionConfig config, String k, double score, String member) {
    try {
      zSetOperation.add(config, k, score, member);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return ResultVO.error(e.getMessage());
    }
  }

  @PostMapping("/del")
  public ResultVO del(RedisConnectionConfig config, String key, String member) {
    try {
      zSetOperation.del(config, key, member);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return ResultVO.error(e.getMessage());
    }
  }

  @PostMapping("/get")
  public ResultVO get(RedisConnectionConfig config, String key) {
    try {
      return new ResultVO("ok", zSetOperation.get(config, key), 200);
    } catch (Exception e) {
      return ResultVO.error(e.getMessage());
    }
  }

  @PostMapping("/update")
  public ResultVO update(RedisConnectionConfig config, String k, double score, String member) {
    try {
      zSetOperation.update(config, k, score, member);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return ResultVO.error(e.getMessage());
    }
  }
}
