package org.tview.visualization.model.res;

public class MysqlDataTypeRes {
  private String name;
  private Boolean md;

  public MysqlDataTypeRes() {
  }

  public MysqlDataTypeRes(String name, Boolean md) {
    this.name = name;
    this.md = md;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getMd() {
    return md;
  }

  public void setMd(Boolean md) {
    this.md = md;
  }
}
