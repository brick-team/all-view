package org.tview.visualization.model.db;

/**
 * 行结构
 */
public class CreateRowParams {

  /** 修改表结构的时候使用: 旧的名字 */
  private String oldName;
  /** 字段名称 */
  private String name;
  /** 字段类型 */
  private String type;
  /** 长度 */
  private Integer length;
  /** 小数位 */
  private Integer scale;
  /** 是否可以为null */
  private boolean nullable;
  /** 是否是键 */
  private boolean key;
  /** 备注 */
  private String content;
  /** 是否自增 */
  private boolean autoAdd;
  /** 默认值 */
  private String defaultValue;
  /** 是否有符号 */
  private boolean unsigned;

  public String getOldName() {
    return oldName;
  }

  public void setOldName(String oldName) {
    this.oldName = oldName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public Integer getScale() {
    return scale;
  }

  public void setScale(Integer scale) {
    this.scale = scale;
  }

  public boolean isNullable() {
    return nullable;
  }

  public void setNullable(boolean nullable) {
    this.nullable = nullable;
  }

  public boolean isKey() {
    return key;
  }

  public void setKey(boolean key) {
    this.key = key;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean isAutoAdd() {
    return autoAdd;
  }

  public void setAutoAdd(boolean autoAdd) {
    this.autoAdd = autoAdd;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  public boolean isUnsigned() {
    return unsigned;
  }

  public void setUnsigned(boolean unsigned) {
    this.unsigned = unsigned;
  }
}
