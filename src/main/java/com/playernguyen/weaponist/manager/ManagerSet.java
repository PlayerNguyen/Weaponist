package com.playernguyen.weaponist.manager;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class ManagerSet<T> implements Iterable<T> {

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

    public void clear() {
        getContainer().clear();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return getContainer().iterator();
    }
}
