package com.playernguyen.manager;

import java.util.HashSet;
import java.util.Set;

public class ManagerSet<T> {

    private final Set<T> container = new HashSet<>();

    public Set<T> getContainer() {
        return container;
    }

    public boolean add(T e) {
        return container.add(e);
    }

    public void remove(T e) {
        container.remove(e);
    }

}
