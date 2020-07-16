package org.tview.visualization.redis.factory;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.junit.Test;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

class RedisConnectionCacheFactoryImplTest extends AbsRedisTemplate {

  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();





  public static void convertByteToString(
      RedisConnection connection, Set<byte[]> keysSet, List<Object> tempList) {
    StringRedisSerializer stringSerializer = new StringRedisSerializer(Charset.forName("UTF8"));
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
