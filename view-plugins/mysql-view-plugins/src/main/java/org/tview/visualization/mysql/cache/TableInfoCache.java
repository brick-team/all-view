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
}
