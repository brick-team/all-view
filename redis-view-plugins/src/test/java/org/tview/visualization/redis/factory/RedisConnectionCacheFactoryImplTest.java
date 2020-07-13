package org.tview.visualization.redis.factory;

import java.util.Properties;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

class RedisConnectionCacheFactoryImplTest extends AbsRedisTemplate {

  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  @org.junit.jupiter.api.Test
  void factory() {
    RedisTemplate<String, Object> factory = this.factory.factory(this.config);
    RedisConnectionFactory requiredConnectionFactory = factory.getRequiredConnectionFactory();
    Properties info = requiredConnectionFactory.getConnection().info();
    System.out.println();
  }
}
