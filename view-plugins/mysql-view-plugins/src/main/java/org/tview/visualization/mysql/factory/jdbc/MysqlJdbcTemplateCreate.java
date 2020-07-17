package org.tview.visualization.mysql.factory.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.util.StringUtils;
import org.tview.visualization.model.db.DBConnectionConfig;

public class MysqlJdbcTemplateCreate implements JdbcTemplateCreate {
  public static final String NOT_DB =
      "jdbc:mysql://%s:%s/?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=%s&user=%s&password=%s";
  public static final String HAS_DB =
      "jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=%s&user=%s&password=%s";

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
    } else {
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
    Connection target = DriverManager.getConnection(genUrl(connectionConfig));
    return new JdbcTemplate(new SingleConnectionDataSource(target, true));
  }
}
