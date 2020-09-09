package com.playernguyen.weaponist.asset.ammunition;

import com.playernguyen.weaponist.asset.ItemTagEnum;
import com.playernguyen.weaponist.asset.ItemType;
import com.playernguyen.weaponist.util.Tag;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.List;

public abstract class DefaultAmmunition implements Ammunition {

    private final AmmunitionConfiguration ammunitionConfiguration;
    private final AmmunitionEnum type;

    public DefaultAmmunition(AmmunitionEnum ammunitionEnum) throws IOException {
        this.ammunitionConfiguration = new AmmunitionConfiguration(ammunitionEnum);
        this.type = ammunitionEnum;
    }

    public AmmunitionEnum getType() {
        return type;
    }

    @Override
    public List<String> getDescription() {
        return ammunitionConfiguration.getLoadedItemMetaData().getDescription();
    }

    @Override
    public String getDisplayName() {
        return ammunitionConfiguration.getLoadedItemMetaData().getDisplayName();
    }

    @Override
    public Material getMaterial() {
        Material material = ammunitionConfiguration.getLoadedItemMetaData().getMaterial();
        if (!material.isItem()) {
            throw new IllegalStateException("Material must be an item, not another...");
        }
        return material;
    }

    @Override
    public int getMaxStackSize() {
        return ammunitionConfiguration.getLoadedItemMetaData().getMaxStackSize();
    }

    @Override
    public int getMaxPenetrate() {
        return ammunitionConfiguration.getMaxPenetrate();
    }

    @Override
    public ItemStack toItem(Player owner, int amount) {
        ItemStack stack = new ItemStack(getMaterial(), amount);
        ItemMeta itemMeta = stack.getItemMeta();

        if (itemMeta != null) {
            itemMeta.setDisplayName(getDisplayName());
            itemMeta.setLore(getDescription());
        }
        stack.setItemMeta(itemMeta);

        // Add nms data into the item
        stack = new Tag.Builder(stack)
                .initData(ItemTagEnum.IS_AMMO)
                .clearAttribute()
                .setData(ItemTagEnum.ITEM_TYPE, ItemType.AMMO.toString())
                .setData(ItemTagEnum.ITEM_ID, this.getId())
                .build();
        return stack;
    }

    @Override
    public String getGlobalId() {
        return "ammo_".concat(getId());
    }
}
