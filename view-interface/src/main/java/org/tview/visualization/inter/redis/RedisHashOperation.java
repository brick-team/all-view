package org.tview.visualization.inter.redis;

import org.tview.visualization.model.redis.RedisConnectionConfig;

/**
 * redis 的 hash 数据类型操作
 */
public interface RedisHashOperation extends IRedisOperationLabel {

  void add(RedisConnectionConfig config, String k, String field, String v);

  Object get(RedisConnectionConfig config, String k);

  void del(RedisConnectionConfig config, String k, String field);

  void update(RedisConnectionConfig config, String k, String field, String v);
}
