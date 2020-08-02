package org.tview.visualization.app.web.redis.dt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.inter.redis.RedisListOperation;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.model.res.ResultVO;
import org.tview.visualization.redis.impl.RedisListOperationImpl;

@RestController
@RequestMapping("/redis/list")
public class RedisListController {

  RedisListOperation redisListOperation = new RedisListOperationImpl();

  @PostMapping("/add")
  public ResultVO add(RedisConnectionConfig conf, String k, String v) {
    try {
      redisListOperation.add(conf, k, v);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return ResultVO.error(e.getMessage());
    }
  }

  @PostMapping("/get")
  public ResultVO get(RedisConnectionConfig conf, String k) {
    try {
      return new ResultVO("ok", redisListOperation.get(conf, k), 200);
    } catch (Exception e) {
      return ResultVO.error(e.getMessage());
    }
  }

  @PostMapping("/update")
  public ResultVO update(RedisConnectionConfig conf, String k, String ov, String nv) {
    try {
      redisListOperation.update(conf, k, ov, nv);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return ResultVO.error(e.getMessage());
    }
  }

  @PostMapping("/removeByRow")
  public ResultVO removeByRow(RedisConnectionConfig config, String k, int row) {
    try {
      redisListOperation.removeByRow(config, k, row);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return ResultVO.error(e.getMessage());
    }
  }

  @PostMapping("/del")
  public ResultVO del(RedisConnectionConfig conf, String k) {
    try {
      redisListOperation.del(conf, k);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return ResultVO.error(e.getMessage());
    }
  }
}
