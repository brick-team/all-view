package org.tview.visualization.model.db;

public enum DBType {
  mysql("mysql");
  private final String value;

  DBType(String value) {
    this.value = value;
  }

  public static DBType str2enum(String value) {
    if (value == null || value.equals("") || value.length() == 0) {
      throw new IllegalArgumentException("转换参数异常");
    }
    String smallValue = value.toLowerCase();
    switch (smallValue) {
      case "mysql":
        return mysql;
    }
    return null;
  }

  public String getValue() {
    return value;
  }
}
