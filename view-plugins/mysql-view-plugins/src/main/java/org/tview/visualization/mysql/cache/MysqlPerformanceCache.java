package org.tview.visualization.mysql.cache;

import java.util.Map;
import org.tview.visualization.cache.impl.FifoCache;
import org.tview.visualization.model.res.DbPerformance;

public class MysqlPerformanceCache extends FifoCache<String, DbPerformance> {

  public MysqlPerformanceCache(int size) {
    super(size);
  }

  @Override
  public int size() {
    return super.size();
  }

  @Override
  public void put(String key, DbPerformance value) {
    super.put(key, value);
  }

  @Override
  public DbPerformance get(String key) {
    return super.get(key);
  }

  @Override
  public Map<String, DbPerformance> getMap() {
    return super.getMap();
  }
}
