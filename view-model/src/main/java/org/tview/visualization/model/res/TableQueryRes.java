package org.tview.visualization.model.res;

import java.util.List;

/** 表查询结果对象 */
public class TableQueryRes {

  /** 分页大小 */
  private int size;
  /** 分页号 */
  private int num;
  /** 总量 */
  private int total;
  /** 数据 */
  private List<List<Object>> rows;
  /** 表头 */
  private List<String> head;

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<List<Object>> getRows() {
    return rows;
  }

  public void setRows(List<List<Object>> rows) {
    this.rows = rows;
  }

  public List<String> getHead() {
    return head;
  }

  public void setHead(List<String> head) {
    this.head = head;
  }
}
