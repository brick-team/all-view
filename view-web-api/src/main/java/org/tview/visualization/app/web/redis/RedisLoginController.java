package org.tview.visualization.app.web.redis;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.inter.ConfigLoginService;
import org.tview.visualization.inter.redis.IRedisServerInfo;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.model.res.ResultVO;
import org.tview.visualization.redis.impl.IRedisServiceInfoImpl;
import org.tview.visualization.redis.impl.login.RedisLoginServiceImpl;

@RestController
@RequestMapping("/redis")
public class RedisLoginController {

  ConfigLoginService service = new RedisLoginServiceImpl();

  IRedisServerInfo redisServerInfo = new IRedisServiceInfoImpl();

  @PostMapping("/login")
  public ResultVO login(
      @RequestBody String name, @RequestBody RedisConnectionConfig config
  ) {
    service.login(name, config);
    return ResultVO.success(redisServerInfo.dataBaseList(config, name));
  }



}
