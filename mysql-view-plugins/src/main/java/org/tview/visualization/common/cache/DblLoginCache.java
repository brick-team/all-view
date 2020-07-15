package org.tview.visualization.common.cache;

import java.util.Map;
import org.tview.visualization.cache.impl.LruCache;
import org.tview.visualization.model.db.DBConnectionConfig;

public class DblLoginCache extends LruCache<String, DBConnectionConfig> {

  public DblLoginCache(int size) {
    super(size);
  }

  @Override
  public int size() {
    return super.size();
  }

  @Override
  public void put(String key, DBConnectionConfig value) {
    super.put(key, value);
  }

  @Override
  public DBConnectionConfig get(String key) {
    return super.get(key);
  }

  @Override
  public Map<String, DBConnectionConfig> getMap() {
    return super.getMap();
  }
}
