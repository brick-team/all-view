package org.tview.visualization.mysql.cache;

import org.tview.visualization.cache.impl.LruCache;
import org.tview.visualization.model.db.TableInfoEntity;
import org.tview.visualization.mysql.singlet.MysqlCacheSinglet;

/**
 * mysql 的表结构
 *
 * @see MysqlCacheSinglet#getTableInfoCache()
 */
public class TableInfoCache extends LruCache<String, TableInfoEntity> {

  public TableInfoCache(int size) {
    super(size);
  }

  @Override
  public int size() {
    return super.size();
  }

  @Override
  public void put(String key, TableInfoEntity value) {
    super.put(key, value);
  }

  @Override
  public TableInfoEntity get(String key) {
    return super.get(key);
  }
}
