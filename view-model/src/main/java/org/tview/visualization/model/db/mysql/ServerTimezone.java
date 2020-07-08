package org.tview.visualization.model.db.mysql;

/** mysql 可用时区(部分) */
public enum ServerTimezone {
  /*utc*/
  UTC("UTC"),
  /*shanghai*/
  Shanghai("Asia/Shanghai"),
  ;
  /* serverTimezone 的value值*/
  private String value;

  ServerTimezone() {}

  ServerTimezone(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
