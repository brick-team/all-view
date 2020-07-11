package org.tview.visualization.mysql.factory.table;

import org.tview.visualization.cache.CacheInterface;
import org.tview.visualization.model.db.TableInfoEntity;
import org.tview.visualization.mysql.cache.TableInfoCache;

public class TableStructureCacheFactory implements CacheInterface<String, TableInfoEntity> {

  TableInfoCache tableInfoCache = new TableInfoCache(100);

  /**
   * 缓存容量
   *
   * @return 缓存容量
   */
  @Override
  public int size() {
    return tableInfoCache.size();
  }

  /**
   * 设置缓存
   *
   * @param key key
   * @param value value
   */
  @Override
  public void put(String key, TableInfoEntity value) {
    tableInfoCache.put(key, value);
  }

  /**
   * 获取缓存
   *
   * @param key key
   * @return value
   */
  @Override
  public TableInfoEntity get(String key) {
    TableInfoEntity tableInfoEntity = tableInfoCache.get(key);

    return tableInfoCache.get(key) == null ? new TableInfoEntity() : tableInfoEntity;
  }
}
