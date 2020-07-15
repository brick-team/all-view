package org.tview.visualization.inter.db;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.tview.visualization.model.db.CreateIndexParam;
import org.tview.visualization.model.db.CreateRowParams;
import org.tview.visualization.model.db.CreateTableParams;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.TableDataEntity;
import org.tview.visualization.model.db.TableInfoEntity;
import org.tview.visualization.model.req.PageVO;
import org.tview.visualization.model.res.MysqlDataTypeRes;

/** 表的操作 */
public interface TableOperation {

  /**
   * 查询表的所有
   *
   * @param config 链接配置
   * @param table 表名字
   * @param pageVO 分页参数
   * @return
   */
  TableDataEntity findAll(DBConnectionConfig config, String table, PageVO pageVO)
      throws SQLException;

  /**
   * 表的信息
   *
   * <ol>
   *   <li>表结构
   *   <li>表索引
   * </ol>
   *
   * @return
   */
  TableInfoEntity tableInfo(DBConnectionConfig config, String table) throws SQLException;

  /** 删除一条数据 */
  void deleteOnceData(DBConnectionConfig config, String table, int id) throws SQLException;

  /** 创建一条数据 */
  void createOnceData(DBConnectionConfig config, String table, Map<String, Object> data)
      throws SQLException;

  /** 创建数据表 */
  void createTable(DBConnectionConfig config, CreateTableParams params) throws SQLException;

  /** 删除表 */
  void deleteTable(DBConnectionConfig config, String table) throws SQLException;

  /**
   * 修改表名
   *
   * @param config
   * @return
   */
  boolean changeTableName(DBConnectionConfig config, String oldName, String newName);

  /**
   * 删除字段
   *
   * @param config 配置
   * @param fields 需要删除的字段列表
   * @return
   */
  boolean removeField(DBConnectionConfig config, String tableName, String... fields);

  /**
   * 修改字段结构
   *
   * @param config 连接配置
   * @param addRow 添加的字段列表
   * @param changeRow 修改的字段列表
   * @return true:修改成功,false:修改失败
   */
  boolean changeFiled(
      DBConnectionConfig config,
      String tableName,
      List<CreateRowParams> changeRow,
      List<CreateRowParams> addRow);

  /** 创建索引 */
  void createIndex(DBConnectionConfig config, CreateIndexParam createIndexParam)
      throws SQLException;

  void addIndex(DBConnectionConfig config, CreateIndexParam createIndexParam) throws SQLException;

  /** 删除索引 */
  void removeIndex(DBConnectionConfig config, String indexName, String tableName)
      throws SQLException;

  /**
   * 修改索引
   *
   * @param config 链接参数
   * @return
   */
  boolean changeIndex(
      DBConnectionConfig config, CreateIndexParam oldIndex, CreateIndexParam newIndex);

  /**
   * mysql 支持的数据类型
   *
   * @return 数据类型
   */
  List<MysqlDataTypeRes> mysqlDataType();
}
