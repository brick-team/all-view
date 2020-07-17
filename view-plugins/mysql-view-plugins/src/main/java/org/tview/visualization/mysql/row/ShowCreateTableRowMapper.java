package org.tview.visualization.mysql.row;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.tview.visualization.model.db.ShowCreateTable;

public class ShowCreateTableRowMapper implements RowMapper<ShowCreateTable> {

  @Override
  public ShowCreateTable mapRow(ResultSet resultSet, int i) throws SQLException {
    return new ShowCreateTable(resultSet.getString("Table"), resultSet.getString("Create Table"));
  }
}
