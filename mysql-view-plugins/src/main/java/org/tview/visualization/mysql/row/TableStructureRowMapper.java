package org.tview.visualization.mysql.row;

import org.springframework.jdbc.core.RowMapper;
import org.tview.visualization.model.db.TableStructure;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableStructureRowMapper implements RowMapper<TableStructure> {

    @Override
    public TableStructure mapRow(ResultSet resultSet, int i) throws SQLException {
        TableStructure tableStructure = new TableStructure();
        tableStructure.setColumnName(resultSet.getString("column_name"));
        tableStructure.setColumnComment(resultSet.getString("column_comment"));
        tableStructure.setColumnType(resultSet.getString("column_type"));
        tableStructure.setNullable(resultSet.getString("is_nullable").equals("YES"));
    return tableStructure;
  }
}
