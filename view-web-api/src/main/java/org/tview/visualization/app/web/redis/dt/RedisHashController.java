package org.tview.visualization.app.web.redis.dt;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.inter.redis.RedisHashOperation;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.model.res.KeyValueObject;
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
      return ResultVO.error(e.getMessage());
    }
  }

  @PostMapping("/get")
  public ResultVO get(RedisConnectionConfig config, String k) {
    try {
      Map<Object, Object> map = hashOperation.get(config, k);


      List<KeyValueObject> res = new ArrayList<>();

      for (Entry<Object, Object> entry : map.entrySet()) {

        Object kk = entry.getKey();
        Object v = entry.getValue();
        KeyValueObject keyValueObject = new KeyValueObject(gson.toJson(kk), gson.toJson(v));
        res.add(keyValueObject);
      }
      return new ResultVO("ok", res, 200);
    } catch (Exception e) {
      return ResultVO.error(e.getMessage());
    }
  }

  Gson gson = new Gson();
  @PostMapping("/del")
  public ResultVO del(RedisConnectionConfig config, String k, String field) {
    try {
      hashOperation.del(config, k, field);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return ResultVO.error(e.getMessage());
    }
  }

  @PostMapping("/update")
  public ResultVO update(RedisConnectionConfig config, String k, String field, String v) {
    try {
      hashOperation.update(config, k, field, v);
      return new ResultVO("ok", true, 200);
    } catch (Exception e) {
      return ResultVO.error(e.getMessage());
    }
  }
}
