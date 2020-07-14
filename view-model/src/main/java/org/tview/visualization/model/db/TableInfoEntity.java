package org.tview.visualization.model.db;

import java.util.ArrayList;
import java.util.List;

public class TableInfoEntity {

  List<TableStructure> tableStruct = new ArrayList<>();
  List<TableIndex> tableIndex = new ArrayList<>();
  private String tableName;
  private List<String> enFiled;
  private List<String> cnFiled;

  public TableInfoEntity() {
  }

  public List<TableStructure> getTableStruct() {
    return tableStruct;
  }

  public void setTableStruct(List<TableStructure> tableStruct) {
    this.tableStruct = tableStruct;
  }

  public List<TableIndex> getTableIndex() {
    return tableIndex;
  }

  public void setTableIndex(List<TableIndex> tableIndex) {
    this.tableIndex = tableIndex;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public List<String> getEnFiled() {
    return enFiled;
  }

  public void setEnFiled(List<String> enFiled) {
    this.enFiled = enFiled;
  }

  public List<String> getCnFiled() {
    return cnFiled;
  }

  public void setCnFiled(List<String> cnFiled) {
    this.cnFiled = cnFiled;
  }
}
