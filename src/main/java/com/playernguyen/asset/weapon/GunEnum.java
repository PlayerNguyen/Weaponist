package com.playernguyen.asset.weapon;

import com.playernguyen.asset.ammunition.AmmunitionEnum;
import com.playernguyen.sound.SoundConfiguration;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum GunEnum {

    BERETTA("beretta",
            "Beretta",
            1.2d,
            12,
            AmmunitionEnum.PISTOL,
            Material.PRISMARINE_CRYSTALS,
            7,
            Arrays.asList(
                    "&cBarretta &7handgun is a light-weight,",
                    "&7mobility and simplex gun for who want ", "&7to have a little boy in hand"
            ),
            Arrays.asList(
                    new SoundConfiguration(Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, 1, 1.4f),
                    new SoundConfiguration(Sound.BLOCK_CHEST_OPEN, 1, 1)
            ),
            Arrays.asList(
                    new SoundConfiguration(Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, 1, 1.4f),
                    new SoundConfiguration(Sound.ENTITY_GENERIC_EXPLODE, 1, 1.4f)
            )
    );

    private final String id;
    private final String name;
    private final AmmunitionEnum ammunitionType;
    private final Material material;
    private final int maxMagazine;
    private final List<String> description;
    private final double damage;

    private final double reloadTime;
    private final List<String> reloadSound;
    private final List<String> shootSound;

    GunEnum(String id,
            String name,
            double reloadTime,
            double damage,
            AmmunitionEnum ammunitionType,
            Material material,
            int maxMagazine,
            List<String> description,
            List<SoundConfiguration> reloadSound,
            List<SoundConfiguration> shootSound) {
        this.id = id;
        this.name = name;
        this.ammunitionType = ammunitionType;
        this.material = material;
        this.maxMagazine = maxMagazine;
        this.description = description;
        this.reloadTime = reloadTime;
        this.damage = damage;

        this.reloadSound = new ArrayList<>();
        for (SoundConfiguration soundConfiguration : reloadSound) {
            this.reloadSound.add(soundConfiguration.toString());
        }

        this.shootSound = new ArrayList<>();
        for (SoundConfiguration soundConfiguration : shootSound) {
            this.shootSound.add(soundConfiguration.toString());
        }
    }

    public List<String> getReloadSound() {
        return reloadSound;
    }

    public List<String> getShootSound() {
        return shootSound;
    }

    public double getDamage() {
        return damage;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AmmunitionEnum getAmmunitionType() {
        return ammunitionType;
    }

    public Material getMaterial() {
        return material;
    }

    public int getMaxMagazine() {
        return maxMagazine;
    }

    public List<String> getDescription() {
        return description;
    }

    public String asFileName() {
        return id.concat(".yml");
    }

    public double getReloadTime() {
        return reloadTime;
    }
}
