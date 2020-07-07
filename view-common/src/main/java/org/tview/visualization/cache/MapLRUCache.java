package org.tview.visualization.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapLRUCache {

    Map<String, String> map;
    int size;

    public MapLRUCache(int size) {
        this.size = size;
        map = new LinkedHashMap<>() {
            @Override
            protected boolean removeEldestEntry(Entry<String, String> entry) {
                return size() > size;
            }
        };
    }

    public String get(String key) {
        return map.get(key);
    }

    public void put(String k, String v) {
        map.put(k, v);
    }
}
