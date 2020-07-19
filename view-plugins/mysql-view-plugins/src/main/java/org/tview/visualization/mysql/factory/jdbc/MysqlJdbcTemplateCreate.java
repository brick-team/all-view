package org.tview.visualization.mysql.factory.jdbc;

import static org.tview.visualization.mysql.singlet.MysqlCacheSinglet.getJdbcTemplateCache;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.util.StringUtils;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.mysql.cache.JdbcTemplateCache;

public class MysqlJdbcTemplateCreate implements JdbcTemplateCreate {

  public static final String NOT_DB =
      "jdbc:mysql://%s:%s/?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=%s&user=%s&password=%s";
  public static final String HAS_DB =
      "jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=%s&user=%s&password=%s";
  JdbcTemplateCache jdbcTemplateCache = getJdbcTemplateCache();

  /**
   * 生成 jdbc url
   *
   * @param connectionConfig 链接配置
   * @return jdbc url
   */
  public String genUrl(DBConnectionConfig connectionConfig) {

    if (StringUtils.isEmpty(connectionConfig.getDbName())) {

      return String.format(
          NOT_DB,
          connectionConfig.getHost(),
          connectionConfig.getPort(),
          connectionConfig.getTimeZone(),
          connectionConfig.getUsername(),
          connectionConfig.getPassword());
    }
    else {
      return String.format(
          HAS_DB,
          connectionConfig.getHost(),
          connectionConfig.getPort(),
          connectionConfig.getDbName(),
          connectionConfig.getTimeZone(),
          connectionConfig.getUsername(),
          connectionConfig.getPassword());
    }
  }

  /**
   * 创建 JdbcTemplate 对象
   *
   * @param connectionConfig 链接配置
   * @return JdbcTemplate
   */
  @Override
  public JdbcTemplate create(DBConnectionConfig connectionConfig) throws SQLException {

    JdbcTemplate jdbcTemplate = jdbcTemplateCache.get(connectionConfig);
    if (jdbcTemplate == null) {
      Connection target = DriverManager.getConnection(genUrl(connectionConfig));
      System.out.println("非缓存");
      return new JdbcTemplate(new SingleConnectionDataSource(target, true));
    }
    else {
      System.out.println("缓存");
      return jdbcTemplate;
    }
  }
}
