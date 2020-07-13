package org.tview.visualization.mysql.factory.jdbc;

import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.tview.visualization.model.db.DBConnectionConfig;

/** JdbcTemplate 创建接口 */
public interface JdbcTemplateCreate {
  /**
   * 创建 JdbcTemplate 对象
   *
   * @param connectionConfig 链接配置
   * @return JdbcTemplate
   */
  JdbcTemplate create(DBConnectionConfig connectionConfig) throws SQLException;

  /**
   * 生成 jdbc url
   *
   * @param connectionConfig 链接配置
   * @return jdbc url
   */
  String genUrl(DBConnectionConfig connectionConfig);
}
