package org.tview.visualization.redis.impl;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.apache.kafka.common.protocol.types.Field.Str;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tview.visualization.inter.redis.RedisHashOperation;
import org.tview.visualization.model.res.KeyValueObject;
import org.tview.visualization.redis.factory.AbsRedisTemplate;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactory;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactoryImpl;

import java.util.Map;

class RedisHashOperationImplTest extends AbsRedisTemplate {

  public static final String HASH_KEY = "hash";
  RedisHashOperation hashOperation = new RedisHashOperationImpl();
  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  @BeforeEach
  public void delete() {
    factory.factory(config).delete(HASH_KEY);
  }

  @Test
  void add() {
    hashOperation.add(this.config, HASH_KEY, "A", "A");
    hashOperation.add(this.config, HASH_KEY, "B", "b");

    Map map = hashOperation.get(this.config, HASH_KEY);
    Assert.assertTrue(map.size() == 2);
  }

  @Test
  void del() {
    hashOperation.add(this.config, HASH_KEY, "A", "A");
    hashOperation.add(this.config, HASH_KEY, "B", "b");
    hashOperation.del(this.config, HASH_KEY, "A");
    Map map = hashOperation.get(this.config, HASH_KEY);
    Assert.assertTrue(map.size() == 1);
  }

  @Test
  void update() {
    hashOperation.add(this.config, HASH_KEY, "A", "A");
    hashOperation.add(this.config, HASH_KEY, "b", "A");
    hashOperation.add(this.config, HASH_KEY, "c", "A");
    hashOperation.update(this.config, HASH_KEY, "A", "NEW_VALUE");
    Map<Object, Object> map = hashOperation.get(this.config, HASH_KEY);




    List<KeyValueObject> res = new ArrayList<>();

    for (Entry<Object, Object> entry : map.entrySet()) {

      Object kk = entry.getKey();
      Object v = entry.getValue();
      KeyValueObject keyValueObject = new KeyValueObject(gson.toJson(kk), gson.toJson(v));
      res.add(keyValueObject);
    }

    System.out.println(res  );
    Object a = map.get("A");
    Assert.assertTrue(a.equals("NEW_VALUE"));
  }

  Gson gson = new Gson();
}
