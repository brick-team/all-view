package org.tview.visualization.mysql.factory.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.tview.visualization.model.db.DBConnectionConfig;

import java.sql.SQLException;

public interface JdbcFactory {
  /**
   * 创建 JdbcTemplate
   *
   * @param connectionConfig 链接配置
   * @return JdbcTemplate
   * @throws SQLException sql异常
   */
  JdbcTemplate create(DBConnectionConfig connectionConfig) throws SQLException;
}
