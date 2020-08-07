package org.tview.visualization.model.res;

public class ResultVO {

  private String msg;
  private Object data;
  private Integer code;

  public ResultVO() {
  }

  public ResultVO(String msg, Object data, Integer code) {
    this.msg = msg;
    this.data = data;
    this.code = code;
  }

  public static ResultVO error(Object data) {
    return new ResultVO("error", data, 400);
  }

  public static ResultVO error() {
    return new ResultVO("error", null, 400);
  }

  public static ResultVO success(Object data) {
    return new ResultVO("error", data, 200);
  }

  public static ResultVO success() {
    return new ResultVO("error", null, 200);
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }
}
