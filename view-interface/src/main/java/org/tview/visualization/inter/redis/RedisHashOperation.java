package org.tview.visualization.inter.redis;

import java.util.Map;
import org.tview.visualization.model.redis.RedisConnectionConfig;

/**
 * redis 的 hash 数据类型操作
 */
public interface RedisHashOperation extends IRedisOperationLabel {

  /**
   * @param config redis 连接配置
   * @param k      键
   * @param field  小键
   * @param v      值
   */
  void add(RedisConnectionConfig config, String k, String field, String v);

  /**
   * 获取 hash 数据
   *
   * @param config redis 连接配置
   * @param k      键
   * @return 值
   */
  Map get(RedisConnectionConfig config, String k);

  /**
   * 删除hash 的field
   *
   * @param config redis 连接配置
   * @param k      键
   * @param field  小键
   */
  void del(RedisConnectionConfig config, String k, String field);

  /**
   * @param config redis 连接配置
   * @param k      键
   * @param field  小键
   * @param v      值
   */
  void update(RedisConnectionConfig config, String k, String field, String v);
}
