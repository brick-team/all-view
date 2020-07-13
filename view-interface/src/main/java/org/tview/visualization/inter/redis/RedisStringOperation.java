package org.tview.visualization.inter.redis;

import org.tview.visualization.model.redis.RedisConnectionConfig;

/**
 * redis 的 string 数据类型操作
 */
public interface RedisStringOperation extends IRedisOperationLabel {
    void add(RedisConnectionConfig config, String k, String v);


    Object get(RedisConnectionConfig config, String k, String v);

    void delete(RedisConnectionConfig config, String k);

    void update(RedisConnectionConfig config, String k, String v);
}
