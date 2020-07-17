package org.tview.visualization.model.db;

/** 数据库实体 */
public class DatabasesEntity {
  private String name;

  public DatabasesEntity() {}

  public DatabasesEntity(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
