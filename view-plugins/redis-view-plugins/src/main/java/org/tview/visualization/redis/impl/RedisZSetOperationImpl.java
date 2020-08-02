package org.tview.visualization.redis.impl;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.tview.visualization.inter.redis.RedisZSetOperation;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactory;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactoryImpl;

import java.util.HashSet;
import java.util.Set;

public class RedisZSetOperationImpl implements RedisZSetOperation {

  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  @Override
  public void add(RedisConnectionConfig config, String k, double score, String member) {
    Set<TypedTuple<Object>> zset = new HashSet<>();
    zset.add(
        new TypedTuple<Object>() {
          private final String data;

          private final double sc;

          {
            sc = score;
            data = member;
          }

          @Override
          public Object getValue() {
            return data;
          }

          @Override
          public Double getScore() {
            return sc;
          }

          @Override
          public int compareTo(TypedTuple<Object> o) {
            if (o == null) {
              return 1;
            }
            return this.getScore() - o.getScore() >= 0 ? 1 : -1;
          }
        });

    factory.factory(config).opsForZSet().add(k, zset);
  }

  @Override
  public void del(RedisConnectionConfig config, String key, String member) {
    factory.factory(config).opsForZSet().remove(key, member);
  }

  @Override
  public Set<TypedTuple> get(RedisConnectionConfig config, String key) {
    return factory.factory(config).opsForZSet().rangeWithScores(key, 0, -1);
  }

  @Override
  public void update(RedisConnectionConfig config, String k, double score, String member) {
    factory.factory(config).opsForZSet().add(k, member, score);
  }
}
