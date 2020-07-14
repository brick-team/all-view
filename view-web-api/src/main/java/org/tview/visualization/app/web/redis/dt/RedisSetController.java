package org.tview.visualization.app.web.redis.dt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.inter.redis.RedisSetOperation;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.model.res.ResultVO;
import org.tview.visualization.redis.impl.RedisSetOperationImpl;

@RestController
@RequestMapping("/redis/set")
public class RedisSetController {

  RedisSetOperation setOperation = new RedisSetOperationImpl();

  @PostMapping("/add")
  public ResultVO add(RedisConnectionConfig config, String k, String v) {
    try {
      setOperation.add(config, k, v);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return new ResultVO("error", e.getMessage(), 400);
    }
  }

  @PostMapping("/get")
  public ResultVO get(RedisConnectionConfig config, String k) {
    try {
      return new ResultVO("ok", setOperation.get(config, k), 200);
    } catch (Exception e) {
      return new ResultVO("error", e.getMessage(), 400);
    }
  }

  @PostMapping("/update")
  public ResultVO update(RedisConnectionConfig config, String k, String ov, String nv) {
    try {
      setOperation.update(config, k, ov, nv);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return new ResultVO("error", e.getMessage(), 400);
    }
  }

  @PostMapping("/del")
  public ResultVO del(RedisConnectionConfig config, String k, String v) {
    try {
      setOperation.del(config, k, v);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return new ResultVO("error", e.getMessage(), 400);
    }
  }
}
