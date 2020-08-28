package com.playernguyen.weaponist.asset.gun;

import com.playernguyen.weaponist.WeaponistInstance;
import com.playernguyen.weaponist.asset.ItemMetadata;
import com.playernguyen.weaponist.asset.ammunition.DefaultItemMetadata;
import com.playernguyen.weaponist.sound.SoundConfiguration;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GunConfiguration extends WeaponistInstance {

    public static final String DISPLAY_NAME = "name";
    public static final String AMMO_TYPE = "ammoType";
    public static final String MATERIAL_TYPE = "materialType";
    public static final String MAX_MAGAZINE = "maxMagazine";
    public static final String DISPLAY_DESCRIPTION = "displayDescription";
    public static final String RELOAD_TIME = "reloadTime";
    public static final String GENERIC_DAMAGE = "genericDamage";
    public static final String RELOAD_SOUND = "reloadSound";
    public static final String SHOOT_NON_ACCESSORY_SOUND = "shootSound.defaultShootSound";
    public static final String DELAY_PER_SHOOT = "delayPerShoot";
    public static final String FIRE_ACCURACY = "fireAccuracy";
    public static final String MAX_DISTANCE = "maxDistance";
    public static final String MAX_PENETRATE = "maxPenetrate";

    private final File file;
    private final FileConfiguration fileConfiguration;
    private final String ammoType;
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

    public GunConfiguration(GunEnum gunEnum) throws IOException {
        // Load the file ~
        this.file = new File(
                getWeaponist().getWeaponFolder().getFolder(),
                gunEnum.asFileName()
        );
        // Load the configuration
        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);

        // Initial
        setDefault(DISPLAY_NAME, gunEnum.getName());
        setDefault(MATERIAL_TYPE, gunEnum.getMaterial().toString());
        setDefault(AMMO_TYPE, gunEnum.getAmmunitionType().getId());

        setDefault(MAX_MAGAZINE, gunEnum.getMaxMagazine());
        setDefault(RELOAD_TIME, gunEnum.getReloadTime());
        setDefault(GENERIC_DAMAGE, gunEnum.getDamage());
        setDefault(DELAY_PER_SHOOT, gunEnum.getDelayPerShoot());
        setDefault(FIRE_ACCURACY, gunEnum.getFireAccuracy());
        setDefault(MATERIAL_TYPE, gunEnum.getMaterial().toString());

        setDefault(RELOAD_SOUND, gunEnum.getReloadSound());
        setDefault(SHOOT_NON_ACCESSORY_SOUND, gunEnum.getShootSound());
        setDefault(MAX_DISTANCE, gunEnum.getMaxDistance());
        setDefault(MAX_PENETRATE, gunEnum.getMaxPenetrate());

        // Getter
        this.itemMetadata = new DefaultItemMetadata(
                gunEnum.getId(),
                this.fileConfiguration.getString(DISPLAY_NAME),
                this.fileConfiguration.getStringList(DISPLAY_DESCRIPTION),
                Material.getMaterial(this.fileConfiguration.getString(MATERIAL_TYPE)),
                this.fileConfiguration.getInt(MAX_MAGAZINE)
        );

        // Generic attributes
        this.maxMagazine = this.fileConfiguration.getInt(MAX_MAGAZINE);
        this.ammoType = this.fileConfiguration.getString(AMMO_TYPE);
        this.reloadTime = this.fileConfiguration.getDouble(RELOAD_TIME);
        this.damage = this.fileConfiguration.getDouble(GENERIC_DAMAGE);
        this.delayPerShoot = this.fileConfiguration.getDouble(DELAY_PER_SHOOT);
        this.fireAccuracy = this.fileConfiguration.getDouble(FIRE_ACCURACY);
        this.maxDistance = this.fileConfiguration.getInt(MAX_DISTANCE);
        this.maxPenetrate = this.fileConfiguration.getInt(MAX_PENETRATE);
        // Sound attributes initial
        this.reloadSound = new ArrayList<>();
        addSoundFromListString(this.fileConfiguration.getStringList(RELOAD_SOUND), this.reloadSound);
        this.shootSound = new ArrayList<>();
        addSoundFromListString(this.fileConfiguration.getStringList(SHOOT_NON_ACCESSORY_SOUND), this.shootSound);

        // Check material
        if (itemMetadata.getMaterial().isBlock()) {
            throw new IllegalArgumentException("Material cannot be a block!");
        }

        // Not found ammo type ~
        if (getAmmunitionManager().getRegisteredAmmunition(ammoType) == null) {
            throw new NullPointerException("The ammo type not found!");
        }

        save();
    }

    public ItemMetadata getLoadedItemMetadata() {
        return this.itemMetadata;
    }

    public int getMaxMagazine() {
        return maxMagazine;
    }

    public String getAmmoType() {
        return ammoType;
    }

    public void save() throws IOException {
        this.fileConfiguration.save(file);
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

    private void setDefault(String key, Object object) {
        if (!fileConfiguration.contains(key)) {
            fileConfiguration.set(key, object);
        }
    }
}
