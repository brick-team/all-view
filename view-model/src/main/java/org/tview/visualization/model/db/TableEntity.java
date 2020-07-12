package org.tview.visualization.model.db;

/** show tables 的查询结果 */
public class TableEntity {
  private String name;

  public TableEntity() {}

  public TableEntity(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
