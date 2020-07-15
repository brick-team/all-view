package org.tview.visualization.redis.impl.login;

import java.util.Properties;
import org.springframework.data.redis.core.RedisTemplate;
import org.tview.visualization.inter.ConfigLoginService;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.redis.cache.RedisNameConfigCache;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactory;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactoryImpl;

public class RedisLoginServiceImpl implements ConfigLoginService<RedisConnectionConfig> {

  RedisNameConfigCache cache = new RedisNameConfigCache(10);
  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  /**
   * 获取配置对象
   *
   * @param name 自定义名称
   * @return 配置
   */
  @Override
  public RedisConnectionConfig get(String name) {
    return cache.get(name);
  }

  /**
   * 是否链接成功
   *
   * @param config 配置对象
   * @return true:连接成功,false:连接失败
   */
  @Override
  public boolean connection(RedisConnectionConfig config) {
    RedisTemplate redisTemplate = this.factory.factory(config);
    Properties info = redisTemplate.getConnectionFactory().getConnection().info();
    return !info.isEmpty();
  }

  /**
   * 登记配置
   *
   * @param name   自定义名称
   * @param config 配置
   */
  @Override
  public void login(String name, RedisConnectionConfig config) {
    if (connection(config)) {
      cache.put(name, config);
    }
  }
}
