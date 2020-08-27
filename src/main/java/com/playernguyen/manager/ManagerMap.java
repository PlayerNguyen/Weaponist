package com.playernguyen.manager;

import java.util.HashMap;
import java.util.Map;

public abstract class ManagerMap<K, V> {
    private final Map<K, V> map = new HashMap<>();

    public Map<K, V> getMap() {
        return map;
    }

    public V put(K k, V v) {
        return map.put(k, v);
    }

    public void clearAll() {
        this.map.clear();
    }

    public void remove(K k) {
        this.map.remove(k);
    }

    public V get(K k) {
        return map.get(k);
    }

}