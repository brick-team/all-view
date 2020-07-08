package org.tview.visualization.mysql.cache;

import org.springframework.jdbc.core.JdbcTemplate;
import org.tview.visualization.cache.impl.LruCache;
import org.tview.visualization.model.db.DBConnectionConfig;

/**
 * JDBC template 的缓存实现
 */
public class JdbcTemplateCache extends LruCache<DBConnectionConfig, JdbcTemplate> {
  public JdbcTemplateCache(int size) {
    super(size);
  }

  @Override
  public int size() {
    return super.size();
  }

  @Override
  public void put(DBConnectionConfig key, JdbcTemplate value) {
    super.put(key, value);
  }

  @Override
  public JdbcTemplate get(DBConnectionConfig key) {
    return super.get(key);
  }
}
