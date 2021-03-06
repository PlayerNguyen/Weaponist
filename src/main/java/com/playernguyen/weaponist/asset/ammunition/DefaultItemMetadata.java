package com.playernguyen.weaponist.asset.ammunition;

import com.playernguyen.weaponist.asset.ItemMetadata;
import com.playernguyen.weaponist.asset.ItemType;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class DefaultItemMetadata implements ItemMetadata {

    private final String id;
    private final String displayName;
    private final List<String> description;
    private final Material material;
    private final int maxStackSize;
    private final ItemType type;

    public DefaultItemMetadata(String id,
                               String displayName,
                               List<String> description,
                               Material material,
                               int maxStackSize,
                               ItemType type) {
        this.id = id;
        this.displayName = ChatColor.translateAlternateColorCodes('&', displayName);
        this.material = material;
        this.maxStackSize = maxStackSize;
        this.type = type;

        List<String> des = new ArrayList<>();
        for (String e : description) {
            des.add(ChatColor.translateAlternateColorCodes('&', e));
        }
        this.description = des;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getGlobalId() {
        return type.getPrefix() + "_" + getId();
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public List<String> getDescription() {
        return this.description;
    }

    @Override
    public Material getMaterial() {
        return this.material;
    }

    @Override
    public int getMaxStackSize() {
        return this.maxStackSize;
    }


}
