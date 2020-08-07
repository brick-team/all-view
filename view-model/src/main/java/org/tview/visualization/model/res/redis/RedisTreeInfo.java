package org.tview.visualization.model.res.redis;

import java.util.List;

public class RedisTreeInfo {

  /**
   * path : showName : localhost type : host+port child :
   * [{"path":"/","showName":"db0","type":"database","child":[{"path":"key_name","type":"key_type","showName":"key_name"}]}]
   */
  private String path;

  private String showName;
  private String type;
  private List<ChildBeanX> child;

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getShowName() {
    return showName;
  }

  public void setShowName(String showName) {
    this.showName = showName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<ChildBeanX> getChild() {
    return child;
  }

  public void setChild(List<ChildBeanX> child) {
    this.child = child;
  }

  public static class ChildBeanX {

    /**
     * path : / showName : db0 type : database child :
     * [{"path":"key_name","type":"key_type","showName":"key_name"}]
     */
    private String path;

    private String showName;
    private String type;

    public String getPath() {
      return path;
    }

    public void setPath(String path) {
      this.path = path;
    }

    public String getShowName() {
      return showName;
    }

    public void setShowName(String showName) {
      this.showName = showName;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }
  }
}
