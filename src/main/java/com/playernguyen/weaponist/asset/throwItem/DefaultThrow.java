package com.playernguyen.weaponist.asset.throwItem;

import com.playernguyen.weaponist.WeaponistInstance;
import com.playernguyen.weaponist.asset.ItemTagEnum;
import com.playernguyen.weaponist.util.Tag;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

public abstract class DefaultThrow extends WeaponistInstance implements Throw {

    private final ThrowEnum throwEnum;
    private final ThrowConfiguration throwConfiguration;

    protected DefaultThrow(ThrowEnum throwEnum) {
        this.throwEnum = throwEnum;
        this.throwConfiguration = new ThrowConfiguration(throwEnum, ThrowFlags.values());
    }

    protected ThrowConfiguration getThrowConfiguration() {
        return throwConfiguration;
    }

    protected ThrowEnum getThrowEnum() {
        return throwEnum;
    }

    @Override
    public double getExplodeTime() {
        return getThrowConfiguration().getDouble(ThrowFlags.ATTRIBUTE_EXPLODE_TIME);
    }

    @Override
    public double getPower() {
        return getThrowConfiguration().getDouble(ThrowFlags.ATTRIBUTE_GENERIC_POWER);
    }

    @Override
    public String getId() {
        return throwEnum.getId();
    }

    @Override
    public String getGlobalId() {
        return throwEnum.getGlobalId();
    }

    @Override
    public String getDisplayName() {
        return getThrowConfiguration().getFormattedColorString(ThrowFlags.METADATA_DISPLAY_NAME);
    }

    @Override
    public List<String> getDescription() {
        return getThrowConfiguration().getFormattedStringList(ThrowFlags.METADATA_DESCRIPTION);
    }

    @Override
    public Material getMaterial() {
        return getThrowConfiguration().getMaterial(ThrowFlags.METADATA_MATERIAL);
    }

    @Override
    public int getMaxStackSize() {
        return 1;
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

        Tag.Builder builder = new Tag.Builder(stack)
                .clearAttribute()
                .initData(ItemTagEnum.IS_THROWABLE)
                .setData(ItemTagEnum.ITEM_ID, getId())
                .setData("UID", UUID.randomUUID());

        return builder.build();
    }
}
