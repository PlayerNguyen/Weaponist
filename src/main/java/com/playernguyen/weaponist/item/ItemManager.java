package com.playernguyen.weaponist.item;

import com.playernguyen.weaponist.asset.Item;
import com.playernguyen.weaponist.manager.ManagerMap;
import org.jetbrains.annotations.Nullable;

public class ItemManager extends ManagerMap<String, Item> {

    @Nullable
    public Item getRegisteredGlobalItem(String id) {
        return getMap().get(id);
    }

}
