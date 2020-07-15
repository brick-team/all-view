package org.tview.visualization.redis.factory;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.redis.cache.RedisConnectionCache;
import org.tview.visualization.redis.singlet.RedisSinglet;

public class RedisConnectionCacheFactoryImpl implements RedisConnectionCacheFactory {

  RedisConnectionCache CACHE = RedisSinglet.getRedisConnectionCache();

  @Override
  public RedisTemplate factory(RedisConnectionConfig config) {
    RedisTemplate redisTemplate = CACHE.get(config);
    if (redisTemplate != null) {
      return redisTemplate;
    } else {
      JedisConnectionFactory conn = new JedisConnectionFactory();
      conn.setDatabase(config.getDbIndex());
      conn.setHostName(config.getHost());
      conn.setPort(config.getPort());
      conn.setPassword(config.getPwd());
      conn.afterPropertiesSet();
      RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
      template.setConnectionFactory(conn);
      template.setKeySerializer(RedisSerializer.string());
      template.setValueSerializer(RedisSerializer.string());
      template.setHashKeySerializer(RedisSerializer.string());
      template.setHashValueSerializer(RedisSerializer.string());
      template.setDefaultSerializer(RedisSerializer.string());
      template.afterPropertiesSet();
      CACHE.put(config, template);
      return template;
    }
  }
}
