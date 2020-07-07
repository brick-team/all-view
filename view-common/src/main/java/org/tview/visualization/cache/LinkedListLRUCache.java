package org.tview.visualization.cache;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListLRUCache {

    LinkedList<CacheNode> list;
    int size;

    public LinkedListLRUCache() {
    }

    public LinkedListLRUCache(int size) {
        list = new LinkedList<>();
        this.size = size;
    }

    public CacheNode get(String key) {
        Iterator<CacheNode> cacheNodeIterator = list.descendingIterator();
        CacheNode value = new CacheNode();

        while (cacheNodeIterator.hasNext()) {
            CacheNode next = cacheNodeIterator.next();
            if (next.getKey().equals(key)) {
                value = next;
                cacheNodeIterator.remove();
                put(key, value.getValue());
                break;
            }
        }
        return value;
    }

    public void put(String key, String value) {
        Iterator<CacheNode> iterator = list.iterator();
        while (iterator.hasNext()) {
            CacheNode next = iterator.next();
            if (next.getKey().equals(key)) {
                iterator.remove();
                break;
            }
        }

        if (size == list.size()) {
            list.removeFirst();
        }
        list.add(new CacheNode(key, value));
    }


}
