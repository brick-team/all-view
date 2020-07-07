package org.tview.visualization.inter.db;

import java.util.List;

/** mysql 数据库操作接口 */
public interface DatabaseOperation {

  /**
   * 数据库列表
   *
   * @return 数据库名称
   */
  List<String> databases();

  /**
   * 某个数据库的表名清单
   *
   * @return 表名列表
   */
  List<String> tableNames();

  /**
   * 数据库状态
   *
   * @return 数据库状态
   */
  Object dataSourceState();

  /** 创建一个数据库 */
  void createDatabase();
}
