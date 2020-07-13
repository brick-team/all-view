package org.tview.visualization.mysql.row;

import org.springframework.jdbc.core.RowMapper;
import org.tview.visualization.model.db.mysql.ShowStatusEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowStatusEntityRowMapper implements RowMapper<ShowStatusEntity> {
    @Override
    public ShowStatusEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        ShowStatusEntity showStatusEntity = new ShowStatusEntity();
        String variableName = resultSet.getString("variable_name");
        String value = resultSet.getString("value");
        showStatusEntity.setValue(value);
        showStatusEntity.setVariableName(variableName);
        return showStatusEntity;
  }
}
