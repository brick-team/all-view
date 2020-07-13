package org.tview.visualization.model.res;

import java.util.List;

public class VueTableEntity {

  private List<String> head;
  private List<List<String>> body;

  public VueTableEntity(List<String> head, List<List<String>> body) {
    this.head = head;
    this.body = body;
  }

  public VueTableEntity() {
  }

  public List<String> getHead() {
    return head;
  }

  public void setHead(List<String> head) {
    this.head = head;
  }

  public List<List<String>> getBody() {
    return body;
  }

  public void setBody(List<List<String>> body) {
    this.body = body;
  }
}
