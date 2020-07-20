package org.tview.visualization.mysql.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.tview.visualization.cache.CacheInterface;
import org.tview.visualization.inter.db.TableOperation;
import org.tview.visualization.model.db.CreateIndexParam;
import org.tview.visualization.model.db.CreateTableParams;
import org.tview.visualization.model.db.CreateTableParams.CreateRowParams;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.ShowCreateTable;
import org.tview.visualization.model.db.TableDataEntity;
import org.tview.visualization.model.db.TableIndex;
import org.tview.visualization.model.db.TableInfoEntity;
import org.tview.visualization.model.db.TableStructure;
import org.tview.visualization.model.db.mysql.MysqlIndexType;
import org.tview.visualization.model.db.mysql.MysqlVarType;
import org.tview.visualization.model.req.PageVO;
import org.tview.visualization.model.res.MysqlDataTypeRes;
import org.tview.visualization.mysql.factory.jdbc.JdbcFactory;
import org.tview.visualization.mysql.factory.jdbc.JdbcTemplateFactory;
import org.tview.visualization.mysql.factory.table.TableStructureCacheFactory;
import org.tview.visualization.mysql.row.ShowCreateTableRowMapper;
import org.tview.visualization.mysql.row.TableIndexRowMapper;
import org.tview.visualization.mysql.row.TableStructureRowMapper;
import org.tview.visualization.page.PageUtils;

public class MysqlTableOperation implements TableOperation {

  public static final String TABLE_INFO =
      "select column_name,\n"
          + "column_comment,column_type,is_nullable from information_schema.columns where table_schema ='%s' and\n"
          + "table_name = '%s' ;";
  public static final String KEY_INDEX =
      "select\n"
          + "    TABLE_NAME,COLUMN_NAME,CONSTRAINT_NAME, REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME\n"
          + "from INFORMATION_SCHEMA.KEY_COLUMN_USAGE\n"
          + "where CONSTRAINT_SCHEMA ='%s' AND\n"
          + "    TABLE_NAME = '%s';";
  private static final Logger LOG = LoggerFactory.getLogger(MysqlTableOperation.class);
  JdbcFactory factory = new JdbcTemplateFactory();
  CacheInterface<String, TableInfoEntity> tableStructureCacheFactory = new TableStructureCacheFactory();

  public static void main(String[] args) {
    CreateTableParams c = new CreateTableParams();
    c.setCharSet("utf8mb4");
    c.setComment("用户表");
    c.setEngine("InnoDB");
    c.setTableName("tttt_uuu");
    List<CreateRowParams> rowParams = new ArrayList<>();
    CreateRowParams id = new CreateRowParams("id", "int", 11, 0, false, true, "主键", true, null, false, true);
    rowParams.add(id);

    CreateRowParams source =
        new CreateRowParams("source", "float", 11, 2, false, true, "分数", false, "0.00", true, false);
    rowParams.add(source);
    c.setRowParams(rowParams);

    String tableSql = createTableSql(c);
    System.out.println(tableSql);
  }

  private static String createTableSql(CreateTableParams createTableParams) {
    String tableName = createTableParams.getTableName();
    String dtl = genDtl(createTableParams.getRowParams());
    String keySql = genKey(createTableParams.getRowParams());
    String engine = "InnoDB";
    String charSet = "utf8";
    String format = "CREATE TABLE `%s` (%s  %s) ENGINE=%s  CHARSET=`%s` COMMENT=\"%s\" ;";

    String substring = keySql.substring(0, keySql.length() - 1);
    return String.format(format, tableName, dtl, substring, engine, charSet, createTableParams.getComment());
  }

  private static String genKey(List<CreateRowParams> rowParams) {
    if (rowParams.stream().filter(CreateRowParams::isPk).collect(Collectors.toList()).size() > 1) {
      throw new IllegalArgumentException("不允许两个主键");
    }
    StringBuffer sb = new StringBuffer(64);
    for (CreateRowParams rowParam : rowParams) {
      String sql = "PRIMARY KEY ( `%s` ),";
      if (!rowParam.isPk() && rowParam.isKey()) {
        sb.append(String.format("KEY (`%s`),", rowParam.getName()));
      }
    }
    return sb.toString();
  }

  private static String genDtl(List<CreateRowParams> rowParams) {
    StringBuffer sb = new StringBuffer(64);
    for (CreateRowParams rowParam : rowParams) {
      String dtl =
          dtl(
              rowParam.getName(),
              rowParam.getType(),
              rowParam.getLength(),
              rowParam.getScale(),
              rowParam.isNullable(),
              rowParam.isKey(),
              rowParam.getContent(),
              rowParam.isAutoAdd(),
              rowParam.getDefaultValue(),
              rowParam.isUnsigned(),
              rowParam.isPk());
      sb.append(dtl);
    }
    return sb.toString();
  }

  private static String dtl(
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

    String res = "";
    // 转换类型
    MysqlVarType mysqlVarType = MysqlVarType.valueOf(type.toUpperCase());
    if (!mysqlVarType.isMd()) {
      // 没有小数
      String format = "`%s`\t %s(%s) ";
      String sql = String.format(format, name, type, length);
      sql = sqlGen(nullable, content, autoAdd, defaultValue, unsigned, pk, sql);

      res = sql + ",";
    } else {
      String format = "`%s`\t %s(%s,%s) ";
      String sql = String.format(format, name, type, length, scale);
      sql = sqlGen(nullable, content, autoAdd, defaultValue, unsigned, pk, sql);
      res = sql + ",";
    }

    return res;
  }

  private static String sqlGen(
      boolean nullable,
      String content,
      boolean autoAdd,
      String defaultValue,
      boolean unsigned,
      boolean pk,
      String sql) {
    if (pk) {
      sql += "primary key ";
    }
    if (unsigned && !pk) {
      sql += "unsigned ";
    }
    if (!nullable) {
      // 不为空
      sql += " NOT NULL ";
    }
    if (autoAdd) {
      // 自增
      sql += " AUTO_INCREMENT ";
    }
    if (!StringUtils.isEmpty(content)) {
      // 备注
      sql += "COMMENT  '" + content + "' ";
    }
    if (!StringUtils.isEmpty(defaultValue) && !pk) {
      sql += "default '" + defaultValue + "' ";
    }
    return sql;
  }

  /**
   * 查询表的所有
   *
   * @param config
   * @param table
   * @param pageVO
   * @return
   */
  @Override
  public TableDataEntity findAll(DBConnectionConfig config, String table, PageVO pageVO) throws SQLException {
    JdbcTemplate jdbcTemplate = factory.create(config);
    PageVO calc = PageUtils.calc(pageVO);
    TableDataEntity tableDataEntity = new TableDataEntity();

    List<Map<String, Object>> maps =
        jdbcTemplate.queryForList("select * from " + table + " limit " + calc.getNum() + " , " + calc.getSize());

    List<String> fields = new ArrayList<>();

    // 添加字段
    if (!CollectionUtils.isEmpty(maps)) {
      Map<String, Object> firstData = maps.get(0);
      firstData.forEach(
          (k, v) -> {
            fields.add(k);
          });
    }
    List<List<Object>> rows = new ArrayList<>();

    if (!CollectionUtils.isEmpty(maps)) {
      List<Object> row = new ArrayList<>();
      for (Map<String, Object> map : maps) {
        map.forEach(
            (k, v) -> {
              row.add(v);
            });
      }
      rows.add(row);
    }
    tableDataEntity.setData(maps);
    tableDataEntity.setFiledName(fields);
    tableDataEntity.setShowData(rows);

    return tableDataEntity;
  }

  /**
   * 表的信息
   *
   * <ol>
   *   <li>表结构
   *   <li>表索引
   * </ol>
   *
   * @param config
   * @param table
   * @return
   */
  @Override
  public TableInfoEntity tableInfo(DBConnectionConfig config, String table) throws SQLException {
    JdbcTemplate jdbcTemplate = factory.create(config);
    String key = tableStateCacheKey(config.getDbName(), table);
    TableInfoEntity tableInfoEntity = tableStructureCacheFactory.get(key);
    if (tableInfoEntity != null) {

      List<TableStructure> tableStruct = getTableStruct(config.getDbName(), table, jdbcTemplate);
      List<TableIndex> tableIndex = getTableIndex(config.getDbName(), table, jdbcTemplate);
      tableInfoEntity.setTableStruct(tableStruct);
      tableInfoEntity.setTableIndex(tableIndex);
      tableInfoEntity.setTableName(table);
      tableInfoEntity.setEnFiled(tableStruct.stream().map(TableStructure::getColumnName).collect(Collectors.toList()));
      tableInfoEntity.setCnFiled(
          tableStruct.stream().map(TableStructure::getColumnComment).collect(Collectors.toList()));

      tableStructureCacheFactory.put(key, tableInfoEntity);
    }

    return tableInfoEntity;
  }

  private String tableStateCacheKey(String dbName, String tableName) {
    return dbName + ":" + tableName;
  }

  /**
   * 获取表结构
   *
   * @param db
   * @param tableName
   * @param jdbcTemplate
   */
  private List<TableStructure> getTableStruct(String db, String tableName, JdbcTemplate jdbcTemplate) {
    List<TableStructure> query =
        jdbcTemplate.query(String.format(TABLE_INFO, db, tableName), new TableStructureRowMapper());
    return query;
  }

  /**
   * 表的索引结构
   *
   * @param db
   * @param tableName
   * @param jdbcTemplate
   * @return
   */
  private List<TableIndex> getTableIndex(String db, String tableName, JdbcTemplate jdbcTemplate) {
    return jdbcTemplate.query(String.format(KEY_INDEX, db, tableName), new TableIndexRowMapper());
  }

  /**
   * 删除一条数据
   *
   * @param config
   * @param table
   * @param id
   */
  @Override
  public void deleteOnceData(DBConnectionConfig config, String table, int id) throws SQLException {
    JdbcTemplate jdbcTemplate = factory.create(config);
    jdbcTemplate.execute("delete from " + table + " where id = " + id);
  }

  /**
   * 创建一条数据
   *
   * @param config
   * @param data
   */
  @Override
  public void createOnceData(DBConnectionConfig config, String table, Map<String, Object> data) throws SQLException {

    JdbcTemplate jdbcTemplate = factory.create(config);

    String sql = "insert into %s (%s) values(%s)";
    StringBuffer filed = new StringBuffer(64);
    StringBuffer value = new StringBuffer(64);
    List<Object> arg = new ArrayList<>();

    data.forEach(
        (k, v) -> {
          filed.append("`" + k + "`" + ",");
          value.append("?" + ",");
          arg.add(v);
        });
    filed.deleteCharAt(filed.length() - 1);
    value.deleteCharAt(value.length() - 1);
    String format = String.format(sql, table, filed, value);
    jdbcTemplate.update(format, arg.stream().toArray());
  }

  /**
   * 创建数据表
   *
   * @param config
   * @param table
   */
  @Override
  public void createTable(DBConnectionConfig config, CreateTableParams params) throws SQLException {
    // todo: 2020/7/11 建表语句
    JdbcTemplate jdbcTemplate = factory.create(config);
    String tableSql = createTableSql(params);
    jdbcTemplate.execute(tableSql);
  }

  /**
   * 删除表
   *
   * @param config
   * @param table
   */
  @Override
  public void deleteTable(DBConnectionConfig config, String table) throws SQLException {
    JdbcTemplate jdbcTemplate = factory.create(config);
    String sql = String.format("DROP TABLE %s ", table);
    jdbcTemplate.execute(sql);
  }

  /**
   * mysql 支持的数据类型
   *
   * @return 数据类型
   */
  @Override
  public List<MysqlDataTypeRes> mysqlDataType() {
    return Arrays.stream(MysqlVarType.values())
        .map(s -> new MysqlDataTypeRes(s.getName(), s.isMd()))
        .collect(Collectors.toList());
  }

  /**
   * 修改表名
   *
   * @param config
   * @return
   */
  @Override
  public boolean changeTableName(DBConnectionConfig config, String oldName, String newName) {
    String s = String.format("alter table %s RENAME to %s", oldName, newName);
    JdbcTemplate jdbcTemplate = null;
    try {
      jdbcTemplate = factory.create(config);
      jdbcTemplate.execute(s);
      return true;
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return false;
  }

  /**
   * 删除字段
   *
   * @param config 配置
   * @param fields 需要删除的字段列表
   * @return
   */
  @Override
  public boolean removeField(DBConnectionConfig config, String tableName, String... fields) {
    String s = "ALTER table %s drop `%s`";
    StringBuilder sb = new StringBuilder(64);
    for (String field : fields) {
      sb.append(field + ",");
    }
    sb.substring(0, sb.length() - 1);
    String format = String.format(s, tableName, sb.toString());

    try {
      JdbcTemplate jdbcTemplate = factory.create(config);
      jdbcTemplate.execute(format);

      return true;
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return false;
  }

  @Override
  public void addIndex(DBConnectionConfig config, CreateIndexParam createIndexParam) throws SQLException {
    JdbcTemplate jdbcTemplate = factory.create(config);
    String indexType = createIndexParam.getIndexType();
    MysqlIndexType mysqlIndexType = MysqlIndexType.valueOf(indexType.toUpperCase());
    String sql;
    switch (mysqlIndexType) {
      case UNIQUE:
        sql = genUniqueIndexSql(createIndexParam);
        jdbcTemplate.execute(sql);
        break;
      case FULLTEXT:
        sql = genFullTextIndexSql(createIndexParam);
        jdbcTemplate.execute(sql);
        break;
      case NORMAL:
        sql = genNormal(createIndexParam);
        jdbcTemplate.execute(sql);
        break;
      case SPATIAL:
        sql = genSpatial(createIndexParam);
        jdbcTemplate.execute(sql);
        break;
    }
  }

  /**
   * 创建空间索引的语句
   *
   * @param createIndexParam
   * @return
   */
  private String genSpatial(CreateIndexParam createIndexParam) {
    String format = "ALTER TABLE `%s` add SPATIAL  index %s(`%s`)";

    return String.format(
        format,
        createIndexParam.getTableName(),
        createIndexParam.getIndexName(),
        createIndexParam.getFiledNames().get(0));
  }

  /**
   * 创建普通索引的语句
   *
   * @param createIndexParam
   * @return
   */
  private String genNormal(CreateIndexParam createIndexParam) {
    String format = "ALTER  TABLE  `%s`  ADD  index  %s(%s ) ";
    return indexFormat(createIndexParam, format);
  }

  /**
   * 创建全文索引的语句
   *
   * @param createIndexParam
   * @return
   */
  private String genFullTextIndexSql(CreateIndexParam createIndexParam) {
    String format = "ALTER  TABLE  `%s`  ADD  FULLTEXT index  %s(%s ) ";
    return indexFormat(createIndexParam, format);
  }

  /**
   * 添加唯一索引的sql语句
   *
   * @param createIndexParam 创建的参数
   * @return sql 语句
   */
  private String genUniqueIndexSql(CreateIndexParam createIndexParam) {
    String format = "ALTER  TABLE  `%s`  ADD  UNIQUE %s( %s) ";
    return indexFormat(createIndexParam, format);
  }

  private String indexFormat(CreateIndexParam createIndexParam, String format) {
    StringBuilder fileds = new StringBuilder(64);
    List<String> filedNames = createIndexParam.getFiledNames();
    for (String filedName : filedNames) {
      fileds.append(String.format("`%s`,", filedName));
    }
    fileds.substring(0, fileds.length() - 1);
    return String.format(format, createIndexParam.getTableName(), createIndexParam.getIndexName(), fileds);
  }

  /**
   * 修改字段结构
   *
   * @param config 连接配置
   * @param changeRow 修改的字段列表
   * @param addRow 添加的字段列表
   * @return true:修改成功,false:修改失败
   */
  @Override
  public boolean changeFiled(
      DBConnectionConfig config,
      String tableName,
      List<org.tview.visualization.model.db.CreateRowParams> changeRow,
      List<org.tview.visualization.model.db.CreateRowParams> addRow) {
    StringBuilder res = new StringBuilder();
    StringBuilder changeTableColumn = calcChangeRowSql(tableName, changeRow);

    StringBuilder addTableColumn = calcAddRowSql(tableName, addRow);

    res.append(changeTableColumn);
    res.append(addTableColumn);

    try {
      JdbcTemplate jdbcTemplate = factory.create(config);
      LOG.info("当前执行的sql = {}", res);
      jdbcTemplate.execute(res.toString());
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  private StringBuilder calcChangeRowSql(
      String tableName, List<org.tview.visualization.model.db.CreateRowParams> changeRow) {
    // 旧字段的修改
    StringBuilder changeTableColumn = new StringBuilder(32);

    for (org.tview.visualization.model.db.CreateRowParams createRowParams : changeRow) {
      MysqlVarType mysqlVarType = MysqlVarType.valueOf(createRowParams.getType().toUpperCase());
      if (!mysqlVarType.isMd()) {
        String s = "ALTER TABLE `%s` CHANGE `%s` `%s` %s ( %s ) UNSIGNED %s DEFAULT '%s' COMMENT '%s';";
        s = canRemoveDefaultAndUnsigned(createRowParams, s);
        changeTableColumn.append(
            String.format(
                s,
                tableName,
                createRowParams.getOldName(),
                createRowParams.getName(),
                createRowParams.getType(),
                createRowParams.getLength(),
                createRowParams.isNullable() ? "" : "not null",
                createRowParams.getDefaultValue(),
                createRowParams.getContent()));
      } else {
        String s = "ALTER TABLE `%s` CHANGE `%s` `%s` %s ( %s,%s ) UNSIGNED  %s DEFAULT '%s' COMMENT '%s';";
        s = canRemoveDefaultAndUnsigned(createRowParams, s);
        changeTableColumn.append(
            String.format(
                s,
                tableName,
                createRowParams.getOldName(),
                createRowParams.getName(),
                createRowParams.getType(),
                createRowParams.getLength(),
                createRowParams.getScale(),
                createRowParams.isNullable() ? "" : "not null",
                createRowParams.getDefaultValue(),
                createRowParams.getContent()));
      }
    }
    return changeTableColumn;
  }

  /**
   * 计算添加字段的sql语句
   *
   * @param tableName
   * @param addRow
   * @return
   */
  private StringBuilder calcAddRowSql(String tableName, List<org.tview.visualization.model.db.CreateRowParams> addRow) {
    // 新增字段的处理
    StringBuilder addTableColumn = new StringBuilder(64);
    for (org.tview.visualization.model.db.CreateRowParams createRowParams : addRow) {

      MysqlVarType mysqlVarType = MysqlVarType.valueOf(createRowParams.getType().toUpperCase());
      if (!mysqlVarType.isMd()) {
        String s = "ALTER TABLE `%s` ADD COLUMN `%s` %s(%s) UNSIGNED %s DEFAULT %s COMMENT '%s';";
        s = canRemoveDefaultAndUnsigned(createRowParams, s);
        addTableColumn.append(
            String.format(
                s,
                tableName,
                createRowParams.getName(),
                createRowParams.getType(),
                createRowParams.getLength(),
                createRowParams.isNullable() ? "" : "not null",
                StringUtils.isEmpty(createRowParams.getDefaultValue()) && createRowParams.isNullable()
                    ? ""
                    : createRowParams.getDefaultValue(),
                createRowParams.getContent()));

      } else {
        String s = "ALTER TABLE `%s` ADD COLUMN `%s` %s(%s,%s) UNSIGNED %s DEFAULT %s COMMENT '%s';";
        s = canRemoveDefaultAndUnsigned(createRowParams, s);

        addTableColumn.append(
            String.format(
                s,
                tableName,
                createRowParams.getName(),
                createRowParams.getType(),
                createRowParams.getLength(),
                createRowParams.getScale(),
                createRowParams.isNullable() ? "" : "not null",
                StringUtils.isEmpty(createRowParams.getDefaultValue()) && createRowParams.isNullable()
                    ? "null"
                    : createRowParams.getDefaultValue(),
                createRowParams.getContent()));
      }
    }
    return addTableColumn;
  }

  private String canRemoveDefaultAndUnsigned(
      org.tview.visualization.model.db.CreateRowParams createRowParams, String s) {
    if (StringUtils.isEmpty(createRowParams.getDefaultValue())) {
      s = s.replace("DEFAULT", "");
    }
    if (!createRowParams.isUnsigned()) {
      s = s.replace("UNSIGNED", "");
    }
    return s;
  }

  /**
   * 创建索引
   *
   * @param config
   */
  @Override
  public void createIndex(DBConnectionConfig config, CreateIndexParam createIndexParam) throws SQLException {
    // 暂定认为是手动后续插入的使用 alter  语句
    this.addIndex(config, createIndexParam);
  }

  /**
   * 删除索引
   *
   * @param config
   */
  @Override
  public void removeIndex(DBConnectionConfig config, String indexName, String tableName) throws SQLException {
    String s = String.format("DROP INDEX %s on `%s`", indexName, tableName);
    JdbcTemplate jdbcTemplate = factory.create(config);
    jdbcTemplate.execute(s);
  }

  /**
   * 修改索引
   *
   * @param config 链接参数
   * @return
   */
  @Override
  public boolean changeIndex(DBConnectionConfig config, CreateIndexParam oldIndex, CreateIndexParam newIndex) {
    try {
      removeIndex(config, oldIndex.getIndexName(), oldIndex.getTableName());
      addIndex(config, newIndex);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return false;
  }

  /**
   * 获取创建表的创建语句
   *
   * @param config 链接参数
   * @param tableName 表名
   * @return
   */
  @Override
  public String getCreateTableSql(DBConnectionConfig config, String tableName) throws SQLException {
    JdbcTemplate jdbcTemplate = factory.create(config);
    ShowCreateTable showCreateTable =
        jdbcTemplate.queryForObject(String.format("SHOW CREATE TABLE %s", tableName), new ShowCreateTableRowMapper());

    return showCreateTable.getCreateTable();
  }
}
