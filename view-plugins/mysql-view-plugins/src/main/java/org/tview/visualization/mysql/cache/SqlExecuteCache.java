package org.tview.visualization.mysql.cache;

import org.tview.visualization.cache.impl.FifoCache;
import org.tview.visualization.mysql.singlet.MysqlCacheSinglet;

/**
 * sql执行缓存
 *
 * @see MysqlCacheSinglet#getSqlExecuteCache()
 */
public class SqlExecuteCache extends FifoCache<String, String> {
  public SqlExecuteCache(int size) {
    super(size);
  }

  @Override
  public int size() {
    return super.size();
  }

  @Override
  public void put(String key, String value) {
    super.put(key, value);
  }

  @Override
  public String get(String key) {
    return super.get(key);
  }
}
