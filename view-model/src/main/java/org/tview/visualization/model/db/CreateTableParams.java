package org.tview.visualization.model.db;

import java.util.ArrayList;
import java.util.List;

/** 创建表的参数 */
public class CreateTableParams {
  List<CreateRowParams> rowParams = new ArrayList<>();
  /**
   * 排序方式
   *
   * @see org.tview.visualization.model.db.mysql.MysqlCharSetCollation
   */
  private String sortType;
  /**
   * 编码类型
   *
   * @see org.tview.visualization.model.db.mysql.MysqlCharSet
   */
  private String charSet;
  /** 注释 */
  private String comment;
  /** 表名 */
  private String tableName;

  private String engine;

  public String getEngine() {
    return engine;
  }

  public void setEngine(String engine) {
    this.engine = engine;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getSortType() {
    return sortType;
  }

  public void setSortType(String sortType) {
    this.sortType = sortType;
  }

  public String getCharSet() {
    return charSet;
  }

  public void setCharSet(String charSet) {
    this.charSet = charSet;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public List<CreateRowParams> getRowParams() {
    return rowParams;
  }

  public void setRowParams(List<CreateRowParams> rowParams) {
    this.rowParams = rowParams;
  }

  /**
   * 行数据
   */
  public static class CreateRowParams {

    /** 字段名称 */
    private String name;
    /** 字段类型 */
    private String type;
    /** 长度 */
    private Integer length;
    /** 小数位 */
    private Integer scale;
    /** 是否可以为null true: 可以, false 不能 */
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

    private boolean pk;

    public CreateRowParams() {}

    public CreateRowParams(
        String name,
        String type,
        Integer length,
        Integer scale,
        boolean nullable,
        boolean key,
        String content,
        boolean autoAdd,
        String defaultValue,
        boolean unsigned,
        boolean pk) {
      this.name = name;
      this.type = type;
      this.length = length;
      this.scale = scale;
      this.nullable = nullable;
      this.key = key;
      this.content = content;
      this.autoAdd = autoAdd;
      this.defaultValue = defaultValue;
      this.unsigned = unsigned;
      this.pk = pk;
    }

    public boolean isPk() {
      return pk;
    }

    public void setPk(boolean pk) {
      this.pk = pk;
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
}
