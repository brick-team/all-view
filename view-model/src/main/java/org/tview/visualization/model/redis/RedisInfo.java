package org.tview.visualization.model.redis;

import org.tview.visualization.model.redis.info.*;

import java.util.List;

public class RedisInfo {

  private RedisCliInfoClients clients;

  private RedisCliInfoCluster cluster;

  private RedisCliInfoCpu cpu;

  private List<RedisCliInfoKeyspace> keyspace;

  private RedisCliInfoMemory memory;
  private RedisCliInfoPersistence persistence;
  private RedisCliInfoReplication replication;
  private RedisCliInfoServer server;
  private RedisCliInfoStats stats;

  public List<RedisCliInfoKeyspace> getKeyspace() {
    return keyspace;
  }

  public void setKeyspace(List<RedisCliInfoKeyspace> keyspace) {
    this.keyspace = keyspace;
  }

  public RedisCliInfoClients getClients() {
    return clients;
  }

  public void setClients(RedisCliInfoClients clients) {
    this.clients = clients;
  }

  public RedisCliInfoCluster getCluster() {
    return cluster;
  }

  public void setCluster(RedisCliInfoCluster cluster) {
    this.cluster = cluster;
  }

  public RedisCliInfoCpu getCpu() {
    return cpu;
  }

  public void setCpu(RedisCliInfoCpu cpu) {
    this.cpu = cpu;
  }

  public RedisCliInfoMemory getMemory() {
    return memory;
  }

  public void setMemory(RedisCliInfoMemory memory) {
    this.memory = memory;
  }

  public RedisCliInfoPersistence getPersistence() {
    return persistence;
  }

  public void setPersistence(RedisCliInfoPersistence persistence) {
    this.persistence = persistence;
  }

  public RedisCliInfoReplication getReplication() {
    return replication;
  }

  public void setReplication(RedisCliInfoReplication replication) {
    this.replication = replication;
  }

  public RedisCliInfoServer getServer() {
    return server;
  }

  public void setServer(RedisCliInfoServer server) {
    this.server = server;
  }

  public RedisCliInfoStats getStats() {
    return stats;
  }

  public void setStats(RedisCliInfoStats stats) {
    this.stats = stats;
  }
}
