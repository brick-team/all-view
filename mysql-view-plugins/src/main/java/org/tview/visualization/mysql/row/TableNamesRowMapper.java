package org.tview.visualization.mysql.row;

import org.springframework.jdbc.core.RowMapper;
import org.tview.visualization.model.db.TableEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableNamesRowMapper implements RowMapper<TableEntity> {
  private final String filedName;

  public TableNamesRowMapper(String filedName) {
    this.filedName = filedName;
  }

  @Override
  public TableEntity mapRow(ResultSet resultSet, int i) throws SQLException {
    TableEntity tablesEntity = new TableEntity();
    tablesEntity.setName(resultSet.getString(filedName));
    return tablesEntity;
  }
}
