package org.tview.visualization.inter.redis;

import org.tview.visualization.model.redis.RedisConnectionConfig;

/**
 * redis 的 zset 数据类型操作
 */
public interface RedisZSetOperation extends IRedisOperationLabel {

  void add(RedisConnectionConfig config, String k, double score, String member);

  void del(RedisConnectionConfig config, String key, String member);

  Object get(RedisConnectionConfig config, String key);

  void update(RedisConnectionConfig config, String k, double score, String member);
}
