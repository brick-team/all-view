package org.tview.visualization.inter.redis;

import java.util.List;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.model.redis.RedisKeyInfo;

/** redis key 的操作接口 */
public interface RedisKeysOperation {

  /**
   * redis 节点信息
   *
   * @param config redis 连接配置
   * @param keyRegion key的正则表达式
   * @return redis key 信息
   */
  List<RedisKeyInfo> keys(RedisConnectionConfig config, String keyRegion);
}
