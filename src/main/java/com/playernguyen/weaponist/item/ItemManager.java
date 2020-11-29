package com.playernguyen.weaponist.item;

import com.playernguyen.weaponist.asset.Item;
import com.playernguyen.weaponist.manager.ManagerMap;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.TreeMap;

public class ItemManager extends ManagerMap<String, Item> {

    public ItemManager() {
        super(new TreeMap<>());
    }

    @Nullable
    public Item getRegisteredGlobalItem(String id) {
        return getMap().get(id);
    }

}
