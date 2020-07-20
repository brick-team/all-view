package org.tview.visualization.model.res;

import java.math.BigDecimal;

public class DbPerformance {

  private BigDecimal qps;
  private BigDecimal tps;
  private BigDecimal keyBufferRead;
  private BigDecimal keyBufferWrite;
  private BigDecimal innoDBBuffer;
  private BigDecimal queryCache;
  private BigDecimal tableCache;
  private BigDecimal threadCache;
  private BigDecimal tmpTable;
  private BigDecimal binlogCache;
  private BigDecimal innodbLogWaits;

  public BigDecimal getQps() {
    return qps;
  }

  public void setQps(BigDecimal qps) {
    this.qps = qps;
  }

  public BigDecimal getTps() {
    return tps;
  }

  public void setTps(BigDecimal tps) {
    this.tps = tps;
  }

  public BigDecimal getKeyBufferRead() {
    return keyBufferRead;
  }

  public void setKeyBufferRead(BigDecimal keyBufferRead) {
    this.keyBufferRead = keyBufferRead;
  }

  public BigDecimal getKeyBufferWrite() {
    return keyBufferWrite;
  }

  public void setKeyBufferWrite(BigDecimal keyBufferWrite) {
    this.keyBufferWrite = keyBufferWrite;
  }

  public BigDecimal getInnoDBBuffer() {
    return innoDBBuffer;
  }

  public void setInnoDBBuffer(BigDecimal innoDBBuffer) {
    this.innoDBBuffer = innoDBBuffer;
  }

  public BigDecimal getQueryCache() {
    return queryCache;
  }

  public void setQueryCache(BigDecimal queryCache) {
    this.queryCache = queryCache;
  }

  public BigDecimal getTableCache() {
    return tableCache;
  }

  public void setTableCache(BigDecimal tableCache) {
    this.tableCache = tableCache;
  }

  public BigDecimal getThreadCache() {
    return threadCache;
  }

  public void setThreadCache(BigDecimal threadCache) {
    this.threadCache = threadCache;
  }

  public BigDecimal getTmpTable() {
    return tmpTable;
  }

  public void setTmpTable(BigDecimal tmpTable) {
    this.tmpTable = tmpTable;
  }

  public BigDecimal getBinlogCache() {
    return binlogCache;
  }

  public void setBinlogCache(BigDecimal binlogCache) {
    this.binlogCache = binlogCache;
  }

  public BigDecimal getInnodbLogWaits() {
    return innodbLogWaits;
  }

  public void setInnodbLogWaits(BigDecimal innodbLogWaits) {
    this.innodbLogWaits = innodbLogWaits;
  }
}
