package org.tview.visualization.model.res;

import java.util.List;

/** sql 执行查询后得到的结果对象 */
public class ExecuteRes {
  private List<String> filedName;

  private List<List<Object>> showData;

  private boolean flag;

  public boolean isFlag() {
    return flag;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
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
