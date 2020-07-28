package org.tview.visualization.model.kafka.topic;

import java.util.LinkedHashMap;
import java.util.Map;

public class TopicPartitionVO {

  private Integer leaderId = -1;
  private Integer id = -1;
  private Map<Integer, PartitionReplica> replicaMap = new LinkedHashMap<>();
  private long size = -1;
  private long firstOffset = -1;
  private Integer preferredLeaderId = -1;

  public Integer getPreferredLeaderId() {
    return preferredLeaderId;
  }

  public void setPreferredLeaderId(Integer preferredLeaderId) {
    this.preferredLeaderId = preferredLeaderId;
  }

  public Integer getLeaderId() {
    return leaderId;
  }

  public void setLeaderId(Integer leaderId) {
    this.leaderId = leaderId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Map<Integer, PartitionReplica> getReplicaMap() {
    return replicaMap;
  }

  public void setReplicaMap(Map<Integer, PartitionReplica> replicaMap) {
    this.replicaMap = replicaMap;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public long getFirstOffset() {
    return firstOffset;
  }

  public void setFirstOffset(long firstOffset) {
    this.firstOffset = firstOffset;
  }

  public void addReplica(PartitionReplica partitionReplica) {

    replicaMap.put(partitionReplica.getId(), partitionReplica);
    if (partitionReplica.isLeader()) {
      leaderId = partitionReplica.getId();
    }

    if (preferredLeaderId == null) {
      preferredLeaderId = partitionReplica.getId();
    }
  }

  public static final class PartitionReplica {

    private Integer id;
    private boolean inSync;
    private boolean leader;
    private boolean offline;

    public PartitionReplica() {}

    public PartitionReplica(Integer id, boolean inSync, boolean leader, boolean offline) {
      this.id = id;
      this.inSync = inSync;
      this.leader = leader;
      this.offline = offline;
    }

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public boolean isInSync() {
      return inSync;
    }

    public void setInSync(boolean inSync) {
      this.inSync = inSync;
    }

    public boolean isLeader() {
      return leader;
    }

    public void setLeader(boolean leader) {
      this.leader = leader;
    }

    public boolean isOffline() {
      return offline;
    }

    public void setOffline(boolean offline) {
      this.offline = offline;
    }
  }
}
