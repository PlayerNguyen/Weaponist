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
            5,
            20,
            Material.SLIME_BALL
    ),
    SMOKE(
            "smoke",
            "&6Smoke Grenade",
            "|",
            3,
            20,
            Material.CLAY_BALL,
             20
    ),
    MOLOTOV(
            "molotov",
            "&cMolotov",
            "|",
            0.1D,
            20,
            Material.DIAMOND,
            5
    )
    ;

    private final String id;
    private final String display;
    private final String description;
    private final double explodeTime;
    private final double power;
    private final Material material;
    private final double explodingTime;

    ThrowEnum(String id, String display, String description, double explodeTime, double power, Material material, double explodingTime) {
        this.id = id;
        this.display = display;
        this.description = description;
        this.explodeTime = explodeTime;
        this.power = power;
        this.material = material;
        this.explodingTime = explodingTime;
    }

    ThrowEnum(String id, String display, String description, double explodeTime, double power, Material material) {
        this.id = id;
        this.display = display;
        this.description = description;
        this.explodeTime = explodeTime;
        this.power = power;
        this.material = material;
        this.explodingTime = 1;
    }

    public double getExplodingTime() {
        return explodingTime;
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
