package org.tview.visualization.inter.redis;

import org.tview.visualization.model.redis.RedisConnectionConfig;

import java.util.Set;

/** redis 的 zset 数据类型操作 */
public interface RedisZSetOperation extends IRedisOperationLabel {

  /**
   * zset 添加数据
   *
   * @param config redis 连接配置
   * @param k 键
   * @param score 分数
   * @param member 值
   */
  void add(RedisConnectionConfig config, String k, double score, String member);

  /**
   * 删除一个元素
   *
   * @param config
   * @param key 键
   * @param member 值
   */
  void del(RedisConnectionConfig config, String key, String member);

  /**
   * 获取zset
   *
   * @param config
   * @param key 键
   * @return 数据
   */
  Set get(RedisConnectionConfig config, String key);

  /**
   * 更新一个元素
   *
   * @param config
   * @param k 键
   * @param score 分数
   * @param member 值
   */
  void update(RedisConnectionConfig config, String k, double score, String member);
}
