package org.tview.visualization.inter.redis;

import org.tview.visualization.model.redis.RedisConnectionConfig;

import java.util.Collection;

/** redis 的 set 数据类型操作 */
public interface RedisSetOperation extends IRedisOperationLabel {

  /**
   * 添加一个 set
   *
   * @param config redis 连接配置
   * @param k 键
   * @param v 值
   */
  void add(RedisConnectionConfig config, String k, String v);

  /**
   * 获取set数据值
   *
   * @param config redis 连接配置
   * @param k 键
   * @return 值
   */
  Collection get(RedisConnectionConfig config, String k);

  /**
   * 修改一个键的老数据
   *
   * @param config redis 连接配置
   * @param k 键
   * @param ov 老数据
   * @param nv 新数据
   */
  void update(RedisConnectionConfig config, String k, String ov, String nv);

  /**
   * 删除一个键下面的值
   *
   * @param config redis 连接配置
   * @param k 键
   * @param v 值
   */
  void del(RedisConnectionConfig config, String k, String v);
}
