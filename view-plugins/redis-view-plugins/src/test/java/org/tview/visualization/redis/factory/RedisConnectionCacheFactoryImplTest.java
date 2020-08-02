package org.tview.visualization.redis.factory;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.StandardCharsets;
import java.util.*;

class RedisConnectionCacheFactoryImplTest extends AbsRedisTemplate {

  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  public static void convertByteToString(
      RedisConnection connection, Set<byte[]> keysSet, List<Object> tempList) {
    StringRedisSerializer stringSerializer = new StringRedisSerializer(StandardCharsets.UTF_8);
    for (byte[] byteArray : keysSet) {
      String converted = stringSerializer.deserialize(byteArray);
      DataType dateType = connection.type(byteArray);
      Map<String, Object> map = new HashMap<>();
      map.put(converted, dateType);
      tempList.add(map);
    }
  }

  @org.junit.jupiter.api.Test
  void factory() {
    RedisTemplate redisTemplate = this.factory.factory(this.config);
    RedisConnection connection =
        RedisConnectionUtils.getConnection(redisTemplate.getConnectionFactory());
    Set<byte[]> keys = connection.keys("*".getBytes());
    ArrayList<Object> objects = new ArrayList<>();
    convertByteToString(connection, keys, objects);

    connection.close();

    redisTemplate.opsForValue().set("aaa", "badasda");
    System.out.println();
  }
}
