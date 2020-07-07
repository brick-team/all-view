package org.tview.visualization.cache;

public interface CacheInterface {

    void set(String key, Object value);

    Object get(String key);
}
