package com.playernguyen.asset.weapon;

import com.playernguyen.WeaponistInstance;
import com.playernguyen.asset.ItemMetadata;
import com.playernguyen.asset.ammunition.DefaultItemMetadata;
import com.playernguyen.sound.SoundConfiguration;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GunConfiguration extends WeaponistInstance {

    private final File file;
    private final FileConfiguration fileConfiguration;
    private final ItemMetadata itemMetadata;
    private final int maxMagazine;
    private final String ammoType;
    private final double reloadTime;
    private final double damage;
    private final List<SoundConfiguration> reloadSound;
    private final List<SoundConfiguration> shootSound;

    public GunConfiguration(GunEnum gunEnum) throws IOException {
        this.file = new File(
                getWeaponist().getWeaponFolder().getFolder(),
                gunEnum.asFileName()
        );

        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);

        if (!this.file.exists()) {
            this.fileConfiguration.set("name", gunEnum.getName());
            this.fileConfiguration.set("ammoType", gunEnum.getAmmunitionType().getId());
            this.fileConfiguration.set("material", gunEnum.getMaterial().toString());
            this.fileConfiguration.set("maxMagazine", gunEnum.getMaxMagazine());
            this.fileConfiguration.set("description", gunEnum.getDescription());
            this.fileConfiguration.set("reloadTime", gunEnum.getReloadTime());
            this.fileConfiguration.set("damage", gunEnum.getDamage());
            this.fileConfiguration.set("reloadSound", gunEnum.getReloadSound());
            this.fileConfiguration.set("shootSound", gunEnum.getShootSound());
        }

        this.itemMetadata = new DefaultItemMetadata(
                gunEnum.getId(),
                this.fileConfiguration.getString("name"),
                this.fileConfiguration.getStringList("description"),
                Material.getMaterial(this.fileConfiguration.getString("material")),
                this.fileConfiguration.getInt("maxMagazine")
        );
        // Generic attributes
        this.maxMagazine = this.fileConfiguration.getInt("maxMagazine");
        this.ammoType = this.fileConfiguration.getString("ammoType");
        this.reloadTime = this.fileConfiguration.getDouble("reloadTime");
        this.damage = this.fileConfiguration.getDouble("damage");

        // Sound attributes initial
        this.reloadSound = new ArrayList<>();
        addSoundFromListString(this.fileConfiguration.getStringList("reloadSound"), this.reloadSound);
        this.shootSound = new ArrayList<>();
        addSoundFromListString(this.fileConfiguration.getStringList("shootSound"), this.shootSound);


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
}
