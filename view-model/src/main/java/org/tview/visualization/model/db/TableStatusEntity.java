package org.tview.visualization.model.db;

/** 表状态实体 */
public class TableStatusEntity {

  /** 表名称 */
  private String name;
  /** 表的存储引擎 */
  private String engine;
  /** 版本 */
  private String version;
  /** 行格式。对于MyISAM引擎，这可能是Dynamic，Fixed或Compressed。动态行的行长度可变，例如Varchar或Blob类型字段。固定行是指行长度不变，例如Char和Integer类型字段。 */
  private String rowFormat;
  /** 表中的行数。对于非事务性表，这个值是精确的，对于事务性引擎，这个值通常是估算的。 */
  private String rows;
  /** 平均每行包括的字节数 */
  private String avgRowLength;
  /** 整个表的数据量(单位：字节) */
  private String dataLength;
  /** 表可以容纳的最大数据量 */
  private String maxDataLength;
  /** 索引占用磁盘的空间大小 */
  private String indexLength;
  /** 对于MyISAM引擎，标识已分配，但现在未使用的空间，并且包含了已被删除行的空间。 */
  private String dataFree;
  /** 下一个Auto_increment的值 */
  private String autoIncrement;
  /** 表的创建时间 */
  private String createTime;
  /** 表的最近更新时间 */
  private String updateTime;
  /** 使用 check table 或 myisamchk工具检查表的最近时间 */
  private String checkTime;
  /** 表的默认字符集和字符排序规则 */
  private String collation;
  /** 如果启用，则对整个表的内容计算时的校验和 */
  private String checksum;
  /** 指表创建时的其他所有选项 */
  private String createOptions;
  /** 包含了其他额外信息，对于MyISAM引擎，包含了注释徐标新，如果表使用的是innodb引擎 ，将现实表的剩余空间。如果是一个视图，注释里面包含了VIEW字样。 */
  private String comment;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEngine() {
    return engine;
  }

  public void setEngine(String engine) {
    this.engine = engine;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getRowFormat() {
    return rowFormat;
  }

  public void setRowFormat(String rowFormat) {
    this.rowFormat = rowFormat;
  }

  public String getRows() {
    return rows;
  }

  public void setRows(String rows) {
    this.rows = rows;
  }

  public String getAvgRowLength() {
    return avgRowLength;
  }

  public void setAvgRowLength(String avgRowLength) {
    this.avgRowLength = avgRowLength;
  }

  public String getDataLength() {
    return dataLength;
  }

  public void setDataLength(String dataLength) {
    this.dataLength = dataLength;
  }

  public String getMaxDataLength() {
    return maxDataLength;
  }

  public void setMaxDataLength(String maxDataLength) {
    this.maxDataLength = maxDataLength;
  }

  public String getIndexLength() {
    return indexLength;
  }

  public void setIndexLength(String indexLength) {
    this.indexLength = indexLength;
  }

  public String getDataFree() {
    return dataFree;
  }

  public void setDataFree(String dataFree) {
    this.dataFree = dataFree;
  }

  public String getAutoIncrement() {
    return autoIncrement;
  }

  public void setAutoIncrement(String autoIncrement) {
    this.autoIncrement = autoIncrement;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  public String getCheckTime() {
    return checkTime;
  }

  public void setCheckTime(String checkTime) {
    this.checkTime = checkTime;
  }

  public String getCollation() {
    return collation;
  }

  public void setCollation(String collation) {
    this.collation = collation;
  }

  public String getChecksum() {
    return checksum;
  }

  public void setChecksum(String checksum) {
    this.checksum = checksum;
  }

  public String getCreateOptions() {
    return createOptions;
  }

  public void setCreateOptions(String createOptions) {
    this.createOptions = createOptions;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
