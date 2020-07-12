package org.tview.visualization.model.db.mysql;

/** mysql 的数据类型 */
public enum MysqlVarType {
  TINYINT("TINYINT"),
  SMALLINT("SMALLINT"),
  MEDIUMINT("MEDIUMINT"),
  INT("INT"),
  BIGINT("BIGINT"),
  FLOAT("FLOAT",true),
  DOUBLE("DOUBLE",true),
  DECIMAL("DECIMAL",true),
  DATE("DATE"),
  TIME("TIME"),
  YEAR("YEAR"),
  DATETIME("DATETIME"),
  TIMESTAMP("TIMESTAMP"),
  CHAR("CHAR"),
  VARCHAR("VARCHAR"),
  TINYBLOB("TINYBLOB"),
  TINYTEXT("TINYTEXT"),
  BLOB("BLOB"),
  TEXT("TEXT"),
  MEDIUMBLOB("MEDIUMBLOB"),
  MEDIUMTEXT("MEDIUMTEXT"),
  LONGBLOB("LONGBLOB"),
  LONGTEXT("LONGTEXT"),
  ;
  private final String name;

  /**
   * 是否支持 M,D
   */
  private boolean md;

  public boolean isMd() {
    return md;
  }

  MysqlVarType(String name) {
    this.name = name;
  }

  MysqlVarType(String name, boolean md) {
    this.name = name;
    this.md = md;
  }

  public String getName() {
    return name;
  }
}
