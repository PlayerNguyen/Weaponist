package com.playernguyen.weaponist.asset.gun;

import com.playernguyen.weaponist.asset.AssetConfig;
import com.playernguyen.weaponist.asset.ItemMetadata;
import com.playernguyen.weaponist.asset.ItemType;
import com.playernguyen.weaponist.asset.ammunition.Ammunition;
import com.playernguyen.weaponist.asset.ammunition.AmmunitionEnum;
import com.playernguyen.weaponist.asset.ammunition.DefaultItemMetadata;
import com.playernguyen.weaponist.sound.SoundConfiguration;

import java.util.ArrayList;
import java.util.List;

public class GunConfiguration extends AssetConfig<GunEnum, GunFlags> {


    private final AmmunitionEnum ammoType;
    private final ItemMetadata itemMetadata;
    private final int maxMagazine;
    private final double reloadTime;
    private final double damage;
    private final double delayPerShoot;
    private final double fireAccuracy;
    private final int maxDistance;
    private final int maxPenetrate;
    private final List<SoundConfiguration> reloadSound;
    private final List<SoundConfiguration> shootSound;

    public GunConfiguration(GunEnum gunEnum, GunFlags[] defaultLoader, String parent) {
        super(gunEnum, defaultLoader, parent);
        // Load default
        if (!getFile().exists()) {
            addDefault(GunFlags.METADATA_DISPLAY_NAME, gunEnum.getDisplay());
            addDefault(GunFlags.METADATA_DESCRIPTION, gunEnum.getDescription());
            addDefault(GunFlags.METADATA_MATERIAL, gunEnum.getMaterial().toString());
            addDefault(GunFlags.METADATA_AMMO_TYPE, gunEnum.getAmmunitionType().getId());

            addDefault(GunFlags.ATTRIBUTE_MAX_MAGAZINE, gunEnum.getMaxMagazine());
            addDefault(GunFlags.METADATA_TIME_RELOAD, gunEnum.getReloadTime());
            addDefault(GunFlags.ATTRIBUTE_GENERIC_DAMAGE, gunEnum.getDamage());
            addDefault(GunFlags.METADATA_TIME_DELAY, gunEnum.getDelayPerShoot());
            addDefault(GunFlags.ATTRIBUTE_GENERIC_FIRE_RATE, gunEnum.getFireAccuracy());

            addDefault(GunFlags.SOUND_RELOAD, gunEnum.getReloadSound());
            addDefault(GunFlags.SOUND_NON_COMPRESSOR_SOUND_PROJECTILE, gunEnum.getShootSound());
            addDefault(GunFlags.ATTRIBUTE_GENERIC_DISTANCE, gunEnum.getMaxDistance());
            addDefault(GunFlags.ATTRIBUTE_GENERIC_PENETRATE, gunEnum.getMaxPenetrate());
            save();
        }

        // Getter
        this.itemMetadata = new DefaultItemMetadata(
                gunEnum.getId(),
                getString(GunFlags.METADATA_DISPLAY_NAME),
                getStringList(GunFlags.METADATA_DESCRIPTION),
                getMaterial(GunFlags.METADATA_MATERIAL),
                1,
                ItemType.GUN
        );

        // Generic attributes
        this.maxMagazine = getInt(GunFlags.ATTRIBUTE_MAX_MAGAZINE);
        this.ammoType = getAmmoType(GunFlags.METADATA_AMMO_TYPE);
        this.reloadTime = getDouble(GunFlags.METADATA_TIME_RELOAD);
        this.damage = getDouble(GunFlags.ATTRIBUTE_GENERIC_DAMAGE);
        this.delayPerShoot = getDouble(GunFlags.METADATA_TIME_DELAY);
        this.fireAccuracy = getDouble(GunFlags.ATTRIBUTE_GENERIC_FIRE_RATE);
        this.maxDistance = getInt(GunFlags.ATTRIBUTE_GENERIC_DISTANCE);
        this.maxPenetrate = getInt(GunFlags.ATTRIBUTE_GENERIC_PENETRATE);
        // Sound attributes initial
        this.reloadSound = new ArrayList<>();
        addSoundFromListString(getStringList(GunFlags.SOUND_RELOAD), this.reloadSound);
        this.shootSound = new ArrayList<>();
        addSoundFromListString(getStringList(GunFlags.SOUND_NON_COMPRESSOR_SOUND_PROJECTILE), this.shootSound);

        // Check material
        if (itemMetadata.getMaterial().isBlock()) {
            throw new IllegalArgumentException("Material cannot be a block!");
        }

        // Not found ammo type ~
        if (getAmmunitionManager().getRegisteredAmmunition(ammoType) == null) {
            throw new NullPointerException("The ammo type not found...");
        }
        // Save change
        if (!getFile().exists()) save();
    }


//    public GunConfiguration(GunEnum gunEnum) throws IOException {
//        // Load the file ~
//        this.file = new File(
//                getWeaponist().getWeaponFolder().getFolder(),
//                gunEnum.asFileName()
//        );
//        // Load the configuration
//        getFileConfiguration() = YamlConfiguration.loadConfiguration(file);
//
//        // Initial
//        setDefault(DISPLAY_NAME, gunEnum.getName());
//        setDefault(DISPLAY_DESCRIPTION, gunEnum.getDescription());
//        setDefault(MATERIAL_TYPE, gunEnum.getMaterial().toString());
//        setDefault(AMMO_TYPE, gunEnum.getAmmunitionType().getId());
//
//        setDefault(MAX_MAGAZINE, gunEnum.getMaxMagazine());
//        setDefault(RELOAD_TIME, gunEnum.getReloadTime());
//        setDefault(GENERIC_DAMAGE, gunEnum.getDamage());
//        setDefault(DELAY_PER_SHOOT, gunEnum.getDelayPerShoot());
//        setDefault(FIRE_ACCURACY, gunEnum.getFireAccuracy());
//        setDefault(MATERIAL_TYPE, gunEnum.getMaterial().toString());
//
//        setDefault(RELOAD_SOUND, gunEnum.getReloadSound());
//        setDefault(SHOOT_NON_ACCESSORY_SOUND, gunEnum.getShootSound());
//        setDefault(MAX_DISTANCE, gunEnum.getMaxDistance());
//        setDefault(MAX_PENETRATE, gunEnum.getMaxPenetrate());
//
//        // Getter
//        this.itemMetadata = new DefaultItemMetadata(
//                gunEnum.getId(),
//                getFileConfiguration().getString(DISPLAY_NAME),
//                getFileConfiguration().getStringList(DISPLAY_DESCRIPTION),
//                Material.getMaterial(getFileConfiguration().getString(MATERIAL_TYPE)),
//                getFileConfiguration().getInt(MAX_MAGAZINE),
//                ItemType.GUN
//        );
//
//        // Generic attributes
//        this.maxMagazine = getFileConfiguration().getInt(MAX_MAGAZINE);
//        this.ammoType = getFileConfiguration().getString(AMMO_TYPE);
//        this.reloadTime = getFileConfiguration().getDouble(RELOAD_TIME);
//        this.damage = getFileConfiguration().getDouble(GENERIC_DAMAGE);
//        this.delayPerShoot = getFileConfiguration().getDouble(DELAY_PER_SHOOT);
//        this.fireAccuracy = getFileConfiguration().getDouble(FIRE_ACCURACY);
//        this.maxDistance = getFileConfiguration().getInt(MAX_DISTANCE);
//        this.maxPenetrate = getFileConfiguration().getInt(MAX_PENETRATE);
//        // Sound attributes initial
//        this.reloadSound = new ArrayList<>();
//        addSoundFromListString(getFileConfiguration().getStringList(RELOAD_SOUND), this.reloadSound);
//        this.shootSound = new ArrayList<>();
//        addSoundFromListString(getFileConfiguration().getStringList(SHOOT_NON_ACCESSORY_SOUND), this.shootSound);
//
//        // Check material
//        if (itemMetadata.getMaterial().isBlock()) {
//            throw new IllegalArgumentException("Material cannot be a block!");
//        }
//
//        // Not found ammo type ~
//        if (getAmmunitionManager().getRegisteredAmmunition(ammoType) == null) {
//            throw new NullPointerException("The ammo type not found...");
//        }
//
//        save();
//    }

    public ItemMetadata getLoadedItemMetadata() {
        return this.itemMetadata;
    }

    public int getMaxMagazine() {
        return maxMagazine;
    }

    public AmmunitionEnum getAmmoType() {
        return ammoType;
    }


    public double getReloadTime() {
        return reloadTime;
    }

    public double getDamage() {
        return damage;
    }

    private void addSoundFromListString(List<String> stringList, List<SoundConfiguration> toAdd) {
        for (String _item : stringList) {
            SoundConfiguration.Reader reader = new SoundConfiguration.Reader(_item);
            toAdd.add(reader.toSoundConfiguration());
        }
    }

    public List<SoundConfiguration> getShootSound() {
        return shootSound;
    }

    public List<SoundConfiguration> getReloadSound() {
        return reloadSound;
    }

    public double getDelayPerShoot() {
        return delayPerShoot;
    }

    public double getFireAccuracy() {
        return fireAccuracy;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public int getMaxPenetrate() {
        return maxPenetrate;
    }


}
