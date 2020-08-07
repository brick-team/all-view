package org.tview.visualization.redis.factory;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;

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

  @org.junit.jupiter.api.Test
  public void databases() {
    Jedis jedis = new Jedis("127.0.0.1", 6379);

    List<String> databases = jedis.configGet("databases");

    RedisTemplate factory = this.factory.factory(this.config);
    RedisConnection connection = factory.getConnectionFactory().getConnection();
    Jedis nativeConnection = (Jedis) connection.getNativeConnection();
    List<String> databases1 = nativeConnection.configGet("databases");
    System.out.println();
  }


}
