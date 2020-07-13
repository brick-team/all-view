package org.tview.visualization.model.redis.info;

public class RedisCliInfoReplication {

  private String role;
  private String connectedSlaves;
  private String masterReplOffset;
  private String replBacklogActive;
  private String replBacklogSize;
  private String replBacklogFirstByteOffset;
  private String replBacklogHistlen;

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getConnectedSlaves() {
    return connectedSlaves;
  }

  public void setConnectedSlaves(String connectedSlaves) {
    this.connectedSlaves = connectedSlaves;
  }

  public String getMasterReplOffset() {
    return masterReplOffset;
  }

  public void setMasterReplOffset(String masterReplOffset) {
    this.masterReplOffset = masterReplOffset;
  }

  public String getReplBacklogActive() {
    return replBacklogActive;
  }

  public void setReplBacklogActive(String replBacklogActive) {
    this.replBacklogActive = replBacklogActive;
  }

  public String getReplBacklogSize() {
    return replBacklogSize;
  }

  public void setReplBacklogSize(String replBacklogSize) {
    this.replBacklogSize = replBacklogSize;
  }

  public String getReplBacklogFirstByteOffset() {
    return replBacklogFirstByteOffset;
  }

  public void setReplBacklogFirstByteOffset(String replBacklogFirstByteOffset) {
    this.replBacklogFirstByteOffset = replBacklogFirstByteOffset;
  }

  public String getReplBacklogHistlen() {
    return replBacklogHistlen;
  }

  public void setReplBacklogHistlen(String replBacklogHistlen) {
    this.replBacklogHistlen = replBacklogHistlen;
  }
}
