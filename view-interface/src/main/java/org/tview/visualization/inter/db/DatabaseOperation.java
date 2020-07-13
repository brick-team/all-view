package org.tview.visualization.inter.db;

import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.DBInfoEntity;
import org.tview.visualization.model.db.TableStatusEntity;
import org.tview.visualization.model.db.mysql.ShowStatusEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * mysql 数据库操作接口
 */
public interface DatabaseOperation {

    /**
     * 数据库列表
     *
     * @return 数据库名称
     */
    List<String> databases(DBConnectionConfig connectionConfig) throws SQLException;

  /**
   * 某个数据库的表名清单
   *
   * @return 表名列表
   */
  List<String> tableNames(DBConnectionConfig connectionConfig, String dbName) throws SQLException;

  /**
   * 数据库状态
   *
   * @return 数据库状态
   */
  DBInfoEntity dataSourceState(DBConnectionConfig connectionConfig) throws SQLException;

  /**
   * 数据库状态
   *
   * @param connectionConfig
   * @return
   */
  List<ShowStatusEntity> state(DBConnectionConfig connectionConfig) throws SQLException;

  /** 创建一个数据库 */
  boolean createDatabase(DBConnectionConfig connectionConfig, String createDbName);

  /**
   * show table status from db_name 查看某个数据库的表状态
   *
   * @param connectionConfig
   * @param dbName
   * @return
   */
  List<TableStatusEntity> tableInfos(DBConnectionConfig connectionConfig, String dbName)
      throws SQLException;
}
