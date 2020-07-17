package org.tview.visualization.cache.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.tview.visualization.cache.CacheInterface;

public class LruCache<K, V> implements CacheInterface<K, V> {

  private final int size;
  private final Map<K, V> map;

  public LruCache(int size) {
    this.size = size;
    map =
        new LinkedHashMap<>(size) {
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

  public Map<K, V> getMap() {
    return map;
  }
}
