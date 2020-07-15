package org.tview.visualization.model.db.mysql;

/** mysql 的索引方法 */
public enum MysqlIndexMethod {
  BTREE("btree"),
  HASH("hash"),
  ;
  private final String name;

  MysqlIndexMethod(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
