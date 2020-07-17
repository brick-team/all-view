package org.tview.visualization.model.db;

import java.util.List;
import java.util.Map;

/** 数据表的数据显示实体 */
public class TableDataEntity {
  /** jdbc template 查询结果 */
  private List<Map<String, Object>> data;
  /** 字段 */
  private List<String> filedName;
  /** 每行数据 */
  private List<List<Object>> showData;

  public List<Map<String, Object>> getData() {
    return data;
  }

  public void setData(List<Map<String, Object>> data) {
    this.data = data;
  }

  public List<String> getFiledName() {
    return filedName;
  }

  public void setFiledName(List<String> filedName) {
    this.filedName = filedName;
  }

  public List<List<Object>> getShowData() {
    return showData;
  }

  public void setShowData(List<List<Object>> showData) {
    this.showData = showData;
  }
}
