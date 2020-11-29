package com.playernguyen.weaponist.command;

import com.playernguyen.weaponist.WeaponistInstance;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PermissionConstructor extends WeaponistInstance implements Iterable<String> {

    private final List<String> container = new ArrayList<>();

    public List<String> getContainer() {
        return container;
    }

    public void add(String s) {
        getDebugger().warn(String.format("Register permissions: %s", s));
        container.add(s);
    }

    public void add(CommandPermissionEnum e) {
        getDebugger().warn(String.format("Register permissions: %s", e.getNode()));
        container.add(e.getNode());
    }


    @NotNull
    @Override
    public Iterator<String> iterator() {
        return container.iterator();
    }
}
