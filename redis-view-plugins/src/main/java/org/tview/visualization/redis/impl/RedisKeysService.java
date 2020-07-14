package org.tview.visualization.redis.impl;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.tview.visualization.inter.redis.RedisKeysOperation;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.model.redis.RedisDataType;
import org.tview.visualization.model.redis.RedisKeyInfo;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactory;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactoryImpl;

public class RedisKeysService implements RedisKeysOperation {

  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  public List<RedisKeyInfo> keys(RedisConnectionConfig config, String keyRegion) {
    RedisTemplate redisTemplate = this.factory.factory(config);
    RedisConnection connection =
        RedisConnectionUtils.getConnection(redisTemplate.getConnectionFactory());
    Set<byte[]> keys = connection.keys(keyRegion.getBytes());

    return convert(connection, keys);
  }

  private List<RedisKeyInfo> convert(RedisConnection connection, Set<byte[]> keys) {
    StringRedisSerializer stringSerializer = new StringRedisSerializer(StandardCharsets.UTF_8);
    return keys.stream()
        .map(
            s -> {
              String keyName = stringSerializer.deserialize(s);
              DataType type = connection.type(s);
              return new RedisKeyInfo(keyName, RedisDataType.valueOf(type.name()));
            })
        .collect(Collectors.toList());
  }
}

