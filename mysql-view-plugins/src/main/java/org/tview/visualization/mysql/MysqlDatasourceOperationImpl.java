package org.tview.visualization.mysql;

import org.tview.visualization.inter.db.DatabaseOperation;

/** mysql 的数据库操作接口实现 */
public class MysqlDatasourceOperationImpl implements DatabaseOperation {
  @Override
  public void print() {
    System.out.println("hello");
  }
}
