package org.tview.visualization.redis.impl;

import java.util.List;
import org.springframework.data.redis.core.ListOperations;
import org.tview.visualization.inter.redis.RedisListOperation;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactory;
import org.tview.visualization.redis.factory.RedisConnectionCacheFactoryImpl;

public class RedisListOperationImpl implements RedisListOperation {

  RedisConnectionCacheFactory factory = new RedisConnectionCacheFactoryImpl();

  @Override
  public void add(RedisConnectionConfig conf, String k, String v) {
    factory.factory(conf).opsForList().rightPush(k, v);
  }

  @Override
  public List get(RedisConnectionConfig conf, String k) {
    return factory.factory(conf).opsForList().range(k, 0, -1);
  }

  @Override
  public void update(RedisConnectionConfig conf, String k, String ov, String nv) {
    List list = get(conf, k);
    factory.factory(conf).opsForList().remove(k, list.size(), ov);
    add(conf, k, nv);
  }

  @Override
  public void del(RedisConnectionConfig conf, String k) {
    factory.factory(conf).opsForList().remove(k, 0, -1);
  }

  /**
   * 删除这个key的第row行数据
   *
   * @param config redis 连接配置
   * @param k 键
   * @param row 行号
   */
  @Override
  public void removeByRow(RedisConnectionConfig config, String k, int row) {
    ListOperations listOperations = factory.factory(config).opsForList();
    listOperations.set(k, row, "__delete__");
    listOperations.remove(k, 0, "__delete__");
  }
}
