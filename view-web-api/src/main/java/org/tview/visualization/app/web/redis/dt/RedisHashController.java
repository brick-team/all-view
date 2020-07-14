package org.tview.visualization.app.web.redis.dt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.inter.redis.RedisHashOperation;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.model.res.ResultVO;
import org.tview.visualization.redis.impl.RedisHashOperationImpl;

@RestController
@RequestMapping("/redis/hash")
public class RedisHashController {

  RedisHashOperation hashOperation = new RedisHashOperationImpl();

  @PostMapping("/add")
  public ResultVO add(RedisConnectionConfig config, String k, String field, String v) {
    try {
      hashOperation.add(config, k, field, v);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return new ResultVO("error", e.getMessage(), 400);
    }
  }

  @PostMapping("/get")
  public ResultVO get(RedisConnectionConfig config, String k) {
    try {
      hashOperation.get(config, k);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return new ResultVO("error", e.getMessage(), 400);
    }
  }

  @PostMapping("/del")
  public ResultVO del(RedisConnectionConfig config, String k, String field) {
    try {
      hashOperation.del(config, k, field);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return new ResultVO("error", e.getMessage(), 400);
    }
  }

  @PostMapping("/update")
  public ResultVO update(RedisConnectionConfig config, String k, String field, String v) {
    try {
      hashOperation.update(config, k, field, v);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return new ResultVO("error", e.getMessage(), 400);
    }
  }
}
