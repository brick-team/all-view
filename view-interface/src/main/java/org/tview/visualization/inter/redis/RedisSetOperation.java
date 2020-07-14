package org.tview.visualization.inter.redis;

import org.tview.visualization.model.redis.RedisConnectionConfig;

/**
 * redis 的 set 数据类型操作
 */
public interface RedisSetOperation extends IRedisOperationLabel {

  void add(RedisConnectionConfig config, String k, String v);

  Object get(RedisConnectionConfig config, String k);

  void update(RedisConnectionConfig config, String k, String v);

  void del(RedisConnectionConfig config, String k, String v);
}
