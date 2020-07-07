package org.tview.visualization.inter.db;

import java.util.List;

/** 表的操作 */
public interface TableOperation {

  /**
   * 查询表的所有
   *
   * @return
   */
  List<Object> findAll();

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
  Object tableInfo();

  /** 删除一条数据 */
  void deleteOnceData();

  /** 创建一条数据 */
  void createOnceData();

  /** 创建数据表 */
  void createTable();

  /** 删除表 */
  void deleteTable();
}
