package com.playernguyen.weaponist.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class ManagerMap<K, V> {
    private final Map<K, V> map;

    public ManagerMap(Map<K, V> map) {
        this.map = map;
    }

    public ManagerMap() {
        this.map = new HashMap<>();
    }

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

    public Set<K> keySet() {
        return map.keySet();
    }


}
