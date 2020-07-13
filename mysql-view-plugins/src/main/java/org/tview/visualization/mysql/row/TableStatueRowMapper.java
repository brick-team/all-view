package org.tview.visualization.mysql.row;

import org.springframework.jdbc.core.RowMapper;
import org.tview.visualization.model.db.TableStatusEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableStatueRowMapper implements RowMapper<TableStatusEntity> {

  @Override
  public TableStatusEntity mapRow(ResultSet resultSet, int i) throws SQLException {
    TableStatusEntity tableStatusEntity = new TableStatusEntity();
    tableStatusEntity.setName(resultSet.getString("Name"));
    tableStatusEntity.setEngine(resultSet.getString("Engine"));
    tableStatusEntity.setVersion(resultSet.getString("Version"));
    tableStatusEntity.setRowFormat(resultSet.getString("Row_format"));
    tableStatusEntity.setRows(resultSet.getString("Rows"));
    tableStatusEntity.setAvgRowLength(resultSet.getString("Avg_row_length"));
    tableStatusEntity.setDataLength(resultSet.getString("Data_length"));
    tableStatusEntity.setMaxDataLength(resultSet.getString("Max_data_length"));
    tableStatusEntity.setIndexLength(resultSet.getString("Index_length"));
    tableStatusEntity.setDataFree(resultSet.getString("Data_free"));
    tableStatusEntity.setAutoIncrement(resultSet.getString("Auto_increment"));
    tableStatusEntity.setCreateTime(resultSet.getString("Create_time"));
    tableStatusEntity.setUpdateTime(resultSet.getString("Update_time"));
    tableStatusEntity.setCheckTime(resultSet.getString("Check_time"));
    tableStatusEntity.setCollation(resultSet.getString("Collation"));
    tableStatusEntity.setChecksum(resultSet.getString("Checksum"));
    tableStatusEntity.setCreateOptions(resultSet.getString("Create_options"));
    tableStatusEntity.setComment(resultSet.getString("Comment"));
    return tableStatusEntity;
  }
}
