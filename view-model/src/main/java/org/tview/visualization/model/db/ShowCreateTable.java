package org.tview.visualization.model.db;

public class ShowCreateTable {

  private String table;
  private String createTable;

  public ShowCreateTable() {}

  public ShowCreateTable(String table, String createTable) {
    this.table = table;
    this.createTable = createTable;
  }

  public String getTable() {
    return table;
  }

  public void setTable(String table) {
    this.table = table;
  }

  public String getCreateTable() {
    return createTable;
  }

  public void setCreateTable(String createTable) {
    this.createTable = createTable;
  }
}
