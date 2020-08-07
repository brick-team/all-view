package org.tview.visualization.model.res;

public class KeyValueObject {
  private String key;
  private String value;

  public KeyValueObject() {
  }

  public KeyValueObject(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "{\"KeyValueObject\":{"
        + "\"key\":\""
        + key + '\"'
        + ",\"value\":\""
        + value + '\"'
        + "}}";

  }
}
