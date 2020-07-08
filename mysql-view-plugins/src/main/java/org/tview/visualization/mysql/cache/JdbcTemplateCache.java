package org.tview.visualization.mysql.cache;

import org.springframework.jdbc.core.JdbcTemplate;
import org.tview.visualization.cache.impl.LruCache;

public class JdbcTemplateCache extends LruCache<String, JdbcTemplate> {
    public JdbcTemplateCache(int size) {
        super(size);
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public void put(String key, JdbcTemplate value) {
        super.put(key, value);
    }

    @Override
    public JdbcTemplate get(String key) {
        return super.get(key);
    }
}
