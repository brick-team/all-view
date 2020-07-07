package org.tview.visualization.cache.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.tview.visualization.cache.CacheInterface;

public class LruCache<T> implements CacheInterface<T> {

  private final int size;
  private final Map<String, T> map;

  public LruCache(int size) {
    this.size = size;
    map =
        new LinkedHashMap<>(size) {
          @Override
          protected boolean removeEldestEntry(Entry<String, T> entry) {
            return size() > size;
          }
        };
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void put(String key, T value) {
    map.put(key, value);
  }

  @Override
  public T get(String key) {
    return map.get(key);
  }
}
