package org.tview.visualization.mysql.row;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.tview.visualization.model.db.TableIndex;

public class TableIndexRowMapper implements RowMapper<TableIndex> {

  @Override
  public TableIndex mapRow(ResultSet resultSet, int i) throws SQLException {
    TableIndex tableIndex = new TableIndex();
    tableIndex.setTableName(resultSet.getString("TABLE_NAME"));
    tableIndex.setColumnName(resultSet.getString("COLUMN_NAME"));
    tableIndex.setConstraintName(resultSet.getString("CONSTRAINT_NAME"));
    tableIndex.setReferencedTableName(resultSet.getString("REFERENCED_TABLE_NAME"));
    tableIndex.setReferencedColumnName(resultSet.getString("REFERENCED_COLUMN_NAME"));
    return tableIndex;
  }
}
