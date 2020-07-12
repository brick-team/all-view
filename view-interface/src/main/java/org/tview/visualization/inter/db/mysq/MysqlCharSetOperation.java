package org.tview.visualization.inter.db.mysq;

import java.util.List;

/** * mysql 的字符集操作 */
public interface MysqlCharSetOperation {

  /**
   * 获取字符集
   *
   * @return 字符集
   */
  List<String> charSetList();

  /**
   * 获取某个字符集的默认排序规则
   *
   * @param charSet 字符集
   * @return 排序规则
   */
  String defaultCollection(String charSet);

  /**
   * 获取某个字符集的排序规则
   *
   * @param charSet 字符集
   * @return 排序规则
   */
  List<String> collections(String charSet);

  /**
   * 获取所有排序规则
   *
   * @return 排序规则
   */
  List<String> collections();
}
