package org.tview.visualization.mysql.impl;

import org.tview.visualization.inter.db.mysql.MysqlCharSetOperation;
import org.tview.visualization.model.db.mysql.MysqlCharSet;
import org.tview.visualization.model.db.mysql.MysqlCharSetCollation;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/** mysql 字符集操作实现 */
public class MysqlCharSetOperationImpl implements MysqlCharSetOperation {
  /**
   * 获取字符集
   *
   * @return 字符集
   */
  @Override
  public List<String> charSetList() {
    return Arrays.stream(MysqlCharSet.values())
        .map(MysqlCharSet::getName)
        .collect(Collectors.toList());
  }

  /**
   * 获取某个字符集的默认排序规则
   *
   * @param charSet 字符集
   * @return 排序规则
   */
  @Override
  public String defaultCollection(String charSet) {
    return Arrays.stream(MysqlCharSetCollation.values())
        .filter(
            s -> Objects.equals(s.getCharSet(), charSet.toLowerCase()) && s.isDefaultCollection())
        .map(MysqlCharSetCollation::getCollection)
        .findFirst()
        .orElseThrow(
            () -> {
              throw new IllegalArgumentException(charSet + "没有默认字符集排序规则");
            });
  }

  /**
   * 获取某个字符集的排序规则
   *
   * @param charSet 字符集
   * @return 排序规则
   */
  @Override
  public List<String> collections(String charSet) {
    return Arrays.stream(MysqlCharSetCollation.values())
        .filter(
            s -> Objects.equals(s.getCharSet(), charSet.toLowerCase()) && s.isDefaultCollection())
        .map(MysqlCharSetCollation::getCollection)
        .collect(Collectors.toList());
  }

  /**
   * 获取所有排序规则
   *
   * @return 排序规则
   */
  @Override
  public List<String> collections() {
    return Arrays.stream(MysqlCharSetCollation.values())
        .map(MysqlCharSetCollation::getCollection)
        .collect(Collectors.toList());
  }
}
