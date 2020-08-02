package org.tview.visualization.inter.redis;

import org.tview.visualization.model.redis.RedisConnectionConfig;

import java.util.List;

/** redis 的 list 数据类型操作 */
public interface RedisListOperation extends IRedisOperationLabel {

  void add(RedisConnectionConfig conf, String k, String v);

  List get(RedisConnectionConfig conf, String k);

  /**
   * 更新数据
   *
   * @param conf redis 连接配置
   * @param k 键
   * @param ov 老的数据值
   * @param nv 新的数据值
   */
  void update(RedisConnectionConfig conf, String k, String ov, String nv);

  /**
   * 删除这个key的第row行数据
   *
   * @param config redis 连接配置
   * @param k 键
   * @param row 行号
   */
  void removeByRow(RedisConnectionConfig config, String k, int row);

  /**
   * 删除整个key
   *
   * @param conf redis 连接配置
   * @param k 键
   */
  void del(RedisConnectionConfig conf, String k);
}
