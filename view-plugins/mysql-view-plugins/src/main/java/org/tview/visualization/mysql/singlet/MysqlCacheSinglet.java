package org.tview.visualization.mysql.singlet;

import org.tview.visualization.mysql.cache.JdbcTemplateCache;
import org.tview.visualization.mysql.cache.SqlExecuteCache;
import org.tview.visualization.mysql.cache.TableInfoCache;

public class MysqlCacheSinglet {

  private static JdbcTemplateCache jdbcTemplateCache = null;
  private static SqlExecuteCache sqlExecuteCache = null;
  private static TableInfoCache tableInfoCache = null;

  public static JdbcTemplateCache getJdbcTemplateCache() {
    if (jdbcTemplateCache == null) {
      synchronized (MysqlCacheSinglet.class) {
        if (jdbcTemplateCache == null) {
          jdbcTemplateCache = new JdbcTemplateCache(10);
        }
      }
    }
    return jdbcTemplateCache;
  }

  public static SqlExecuteCache getSqlExecuteCache() {
    if (sqlExecuteCache == null) {
      synchronized (MysqlCacheSinglet.class) {
        if (sqlExecuteCache == null) {
          sqlExecuteCache = new SqlExecuteCache(1024);
        }
      }
    }
    return sqlExecuteCache;
  }

  public static TableInfoCache getTableInfoCache() {
    if (tableInfoCache == null) {
      synchronized (MysqlCacheSinglet.class) {
        if (tableInfoCache == null) {
          tableInfoCache = new TableInfoCache(100);
        }
      }
    }
    return tableInfoCache;
  }
}
