package org.tview.visualization.common.cache;

import org.tview.visualization.cache.impl.FifoCache;
import org.tview.visualization.model.db.DBConnectionConfig;

public class DblLoginCache extends FifoCache<String, DBConnectionConfig> {

  public DblLoginCache(int size) {
    super(size);
  }
}
