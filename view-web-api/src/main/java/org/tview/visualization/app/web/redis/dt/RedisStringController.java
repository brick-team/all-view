package org.tview.visualization.app.web.redis.dt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.inter.redis.RedisStringOperation;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.model.res.ResultVO;
import org.tview.visualization.redis.impl.RedisStringOperationImpl;

@RestController
@RequestMapping("/redis/string")
public class RedisStringController {

  RedisStringOperation stringOperation = new RedisStringOperationImpl();

  @PostMapping("/add")
  public ResultVO add(RedisConnectionConfig config, String k, String v) {
    try {
      stringOperation.add(config, k, v);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return ResultVO.error(e.getMessage());
    }
  }

  @PostMapping("/get")
  public ResultVO get(RedisConnectionConfig config, String k) {
    try {
      Object o = stringOperation.get(config, k);
      return new ResultVO("ok", o, 200);
    } catch (Exception e) {
      return ResultVO.error(e.getMessage());
    }
  }

  @PostMapping("/delete")
  public ResultVO delete(RedisConnectionConfig config, String k) {
    try {
      stringOperation.delete(config, k);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return ResultVO.error(e.getMessage());
    }
  }

  @PostMapping("/update")
  public ResultVO update(RedisConnectionConfig config, String k, String v) {
    try {
      stringOperation.update(config, k, v);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return ResultVO.error(e.getMessage());
    }
  }
}
