package org.tview.visualization.mysql.factory.jdbc;

import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.tview.visualization.model.db.DBConnectionConfig;

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
