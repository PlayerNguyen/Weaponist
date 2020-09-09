package com.playernguyen.weaponist.asset.throwItem;

import com.playernguyen.weaponist.asset.AssetEnum;
import com.playernguyen.weaponist.asset.ItemType;
import com.playernguyen.weaponist.util.ListSerialize;
import org.bukkit.Material;

import java.util.List;

public enum ThrowEnum implements AssetEnum {

    FRAG(
            "frag",
            "&cFlag Grenade",
            " | ",
            7,
            20,
            Material.SLIME_BALL
    ),
    ;

    private final String id;
    private final String display;
    private final String description;
    private final double explodeTime;
    private final double power;
    private final Material material;

    ThrowEnum(String id, String display, String description, double explodeTime, double power, Material material) {
        this.id = id;
        this.display = display;
        this.description = description;
        this.explodeTime = explodeTime;
        this.power = power;
        this.material = material;
    }

    public String getId() {
        return id;
    }

    public double getPower() {
        return power;
    }

    public String getDisplay() {
        return display;
    }

    public List<String> getDescription() {
        return ListSerialize.deserializeStringList(description);
    }

    public double getExplodeTime() {
        return explodeTime;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.THROWABLE;
    }

    public Material getMaterial() {
        return material;
    }
}
