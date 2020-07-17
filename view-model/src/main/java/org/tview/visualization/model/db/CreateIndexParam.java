package org.tview.visualization.model.db;

import java.util.List;
import org.tview.visualization.model.db.mysql.MysqlIndexMethod;
import org.tview.visualization.model.db.mysql.MysqlIndexType;

/** 创建索引的参数 */
public class CreateIndexParam {
  /** 表格名称 */
  private String tableName;
  /** 索引名称 */
  private String indexName;
  /** 字段名称 */
  private List<String> filedNames;
  /**
   * 索引类型
   *
   * @see MysqlIndexType
   */
  private String indexType;
  /**
   * 索引方法
   *
   * @see MysqlIndexMethod
   */
  private String indexMethod;
  /** 注释 */
  private String comment;

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getIndexName() {
    return indexName;
  }

  public void setIndexName(String indexName) {
    this.indexName = indexName;
  }

  public List<String> getFiledNames() {
    return filedNames;
  }

  public void setFiledNames(List<String> filedNames) {
    this.filedNames = filedNames;
  }

  public String getIndexType() {
    return indexType;
  }

  public void setIndexType(String indexType) {
    this.indexType = indexType;
  }

  public String getIndexMethod() {
    return indexMethod;
  }

  public void setIndexMethod(String indexMethod) {
    this.indexMethod = indexMethod;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
