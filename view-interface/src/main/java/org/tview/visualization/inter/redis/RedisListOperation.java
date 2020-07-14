package org.tview.visualization.inter.redis;

import org.tview.visualization.model.redis.RedisConnectionConfig;

/**
 * redis 的 list 数据类型操作
 */
public interface RedisListOperation extends IRedisOperationLabel {

  void add(RedisConnectionConfig conf, String k, String v);

  Object get(RedisConnectionConfig conf, String k);

  void update(RedisConnectionConfig conf, String k, String v);

  void del(RedisConnectionConfig conf, String k);
}
