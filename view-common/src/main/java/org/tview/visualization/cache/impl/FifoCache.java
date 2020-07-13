package org.tview.visualization.cache.impl;

import org.tview.visualization.cache.CacheInterface;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FifoCache<K, V> implements CacheInterface<K, V> {

  private final int size;
  private final Map<K, V> map;

  public FifoCache(int size) {
    this.size = size;
    map =
        new LinkedHashMap<>(size, 1, false) {
          @Override
          protected boolean removeEldestEntry(Entry<K, V> entry) {
            return size() > size;
          }
        };
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void put(K key, V value) {
    map.put(key, value);
  }

  @Override
  public V get(K key) {
    return map.get(key);
  }
}
