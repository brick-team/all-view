package org.tview.visualization.mysql.row;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.tview.visualization.model.db.DatabasesEntity;

/** databases 的row对象 */
public class DatabasesRowMapper implements RowMapper<DatabasesEntity> {
  @Override
  public DatabasesEntity mapRow(ResultSet resultSet, int i) throws SQLException {
    return new DatabasesEntity(resultSet.getString("Database"));
  }
}
