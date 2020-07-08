package org.tview.visualization.cache;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class FifoCache {

  LinkedHashMap<String, String> map;
  int size;

  public FifoCache(int size) {
    this.size = size;
    map =
        new LinkedHashMap<>(size, 1, false) {
          @Override
          protected boolean removeEldestEntry(Entry<String, String> entry) {
            return size() > size;
          }
        };
  }

  public void put(String k, String v) {
    map.put(k, v);
  }

  public String get(String k) {
    return map.get(k);
  }
}
