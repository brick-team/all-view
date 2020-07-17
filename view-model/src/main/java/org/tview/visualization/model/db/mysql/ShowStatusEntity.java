package org.tview.visualization.model.db.mysql;

/** 执行 show status 的结果 */
public class ShowStatusEntity {
  private String variableName;
  private String value;

  public ShowStatusEntity() {}

  public ShowStatusEntity(String variableName, String value) {
    this.variableName = variableName;
    this.value = value;
  }

  public String getVariableName() {
    return variableName;
  }

  public void setVariableName(String variableName) {
    this.variableName = variableName;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
