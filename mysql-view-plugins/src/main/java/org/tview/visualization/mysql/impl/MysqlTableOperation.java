package org.tview.visualization.mysql.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;
import org.tview.visualization.cache.CacheInterface;
import org.tview.visualization.inter.db.TableOperation;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.TableDataEntity;
import org.tview.visualization.model.db.TableIndex;
import org.tview.visualization.model.db.TableInfoEntity;
import org.tview.visualization.model.db.TableStructure;
import org.tview.visualization.model.req.PageVO;
import org.tview.visualization.mysql.factory.jdbc.JdbcFactory;
import org.tview.visualization.mysql.factory.jdbc.JdbcTemplateFactory;
import org.tview.visualization.mysql.factory.table.TableStructureCacheFactory;
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
  JdbcFactory factory = new JdbcTemplateFactory();
  CacheInterface<String, TableInfoEntity> tableStructureCacheFactory =
      new TableStructureCacheFactory();

  /**
   * 查询表的所有
   *
   * @param config
   * @param table
   * @param pageVO
   * @return
   */
  @Override
  public TableDataEntity findAll(DBConnectionConfig config, String table, PageVO pageVO)
      throws SQLException {
    JdbcTemplate jdbcTemplate = factory.create(config);
    PageVO calc = PageUtils.calc(pageVO);
    TableDataEntity tableDataEntity = new TableDataEntity();

    List<Map<String, Object>> maps =
        jdbcTemplate.queryForList(
            "select * from " + table + " limit " + calc.getNum() + " , " + calc.getSize());

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
      tableInfoEntity.setEnFiled(
          tableStruct.stream().map(TableStructure::getColumnName).collect(Collectors.toList()));
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
  private List<TableStructure> getTableStruct(
      String db, String tableName, JdbcTemplate jdbcTemplate) {
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
  public void createOnceData(DBConnectionConfig config, String table, Map<String, Object> data)
      throws SQLException {

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
  public void createTable(DBConnectionConfig config, String table) {
    // todo: 2020/7/11 建表语句
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
}
