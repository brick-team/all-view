package org.tview.visualization.model.kafka;

public class CreateTopicParam {
  /**
   * topic 名称
   */
  private String name;
  /**
   * partitions 编号, 分区数量
   * <b>default value = 0 </b>
   */
  private Integer partitionsNum = 0;
  /**
   * 副本数量
   * <b>default value = 0 </b>
   */
  private Short replicationFactor = 0;

  public CreateTopicParam() {
  }

  public CreateTopicParam(String name, Integer partitionsNum, Short replicationFactor) {
    this.name = name;
    this.partitionsNum = partitionsNum;
    this.replicationFactor = replicationFactor;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPartitionsNum() {
    return partitionsNum;
  }

  public void setPartitionsNum(Integer partitionsNum) {
    this.partitionsNum = partitionsNum;
  }

  public Short getReplicationFactor() {
    return replicationFactor;
  }

  public void setReplicationFactor(Short replicationFactor) {
    this.replicationFactor = replicationFactor;
  }
}
