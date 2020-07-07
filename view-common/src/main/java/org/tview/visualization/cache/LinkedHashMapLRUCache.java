package org.tview.visualization.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/** LRU 最少使用的会被删除 */
public class LinkedHashMapLRUCache {

  private final int size;
  LinkedHashMap<String, String> cache;

  public LinkedHashMapLRUCache(int size) {
    this.cache = new LinkedHashMap<>(size);
    this.size = size;
  }

  public Object get(String key) {

    if (!cache.containsKey(key)) {
      return null;
    }
    String value = cache.get(key);
    cache.remove(key);
    cache.put(key, value);
    return value;
  }

  public void put(String key, String value) {
    cache.remove(key);

    if (size == cache.size()) {
      // remove first element
      Set<String> keySet = cache.keySet();
      Iterator<String> iterator = keySet.iterator();
      cache.remove(iterator.next());
    }
    cache.put(key, value);
  }
}
