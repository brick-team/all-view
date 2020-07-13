package org.tview.visualization.mysql.factory.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.DBType;
import org.tview.visualization.mysql.cache.JdbcTemplateCache;

import java.sql.SQLException;

public class JdbcTemplateFactory implements JdbcFactory {

    /**
     * JdbcTemplate 缓存对象
     */
    private final JdbcTemplateCache cache = new JdbcTemplateCache(10);

    /**
     * 创建 JdbcTemplate
     *
     * @param connectionConfig 链接配置
   * @return
   * @throws SQLException
   */
  public JdbcTemplate create(DBConnectionConfig connectionConfig) throws SQLException {
    String dbType = connectionConfig.getDbType();
    DBType dbEnum = DBType.str2enum(dbType);
    JdbcTemplateCreate jdbcTemplateCreate;
    if (dbEnum == DBType.MYSQL) {
      jdbcTemplateCreate = new MysqlJdbcTemplateCreate();
      return set(connectionConfig, jdbcTemplateCreate.create(connectionConfig));
    }

    return null;
  }

  /**
   * 获取整个缓存
   *
   * @return
   */
  public JdbcTemplateCache getCache() {
    return cache;
  }

  /**
   * 设置缓存
   *
   * @return JdbcTemplate
   */
  private JdbcTemplate set(DBConnectionConfig k, JdbcTemplate v) {
    JdbcTemplate jdbcTemplate = cache.get(k);
    if (jdbcTemplate != null) {
      return jdbcTemplate;
    }
    cache.put(k, v);
    return cache.get(k);
  }
}
