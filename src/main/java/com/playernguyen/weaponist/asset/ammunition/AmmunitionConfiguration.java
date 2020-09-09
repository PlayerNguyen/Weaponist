package com.playernguyen.weaponist.asset.ammunition;

import com.playernguyen.weaponist.WeaponistInstance;
import com.playernguyen.weaponist.asset.ItemMetadata;
import com.playernguyen.weaponist.asset.ItemType;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class AmmunitionConfiguration extends WeaponistInstance {

    private final File file;
    private final FileConfiguration fileConfiguration;
    private final ItemMetadata itemMetadata;
    private final int maxPenetrate;
    private final String globalId;

    public AmmunitionConfiguration(AmmunitionEnum ammunitionEnum) throws IOException {
        this.file = new File(
                getWeaponist().getAmmunitionFolder().getFolder(),
                ammunitionEnum.getFileName()
        );
        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);

        // Set default
        if (!file.exists()) {
            fileConfiguration.set("ammunition.name", ammunitionEnum.getDisplay());
            fileConfiguration.set("ammunition.material", ammunitionEnum.getMaterial().toString());
            fileConfiguration.set("ammunition.description", ammunitionEnum.getDescription());
            fileConfiguration.set("ammunition.maxStackSize", ammunitionEnum.getDefaultMaxStackSize());
            fileConfiguration.set("ammunition.maxPenetrate", ammunitionEnum.getDefaultMaxPenetrate());
        }

        // Set the item metadata
        this.itemMetadata = new DefaultItemMetadata(
                ammunitionEnum.getId(),
                fileConfiguration.getString("ammunition.name"),
                fileConfiguration.getStringList("ammunition.description"),
                Material.getMaterial(fileConfiguration.getString("ammunition.material")),
                fileConfiguration.getInt("ammunition.maxStackSize"),
                ItemType.AMMO
        );
        this.maxPenetrate = fileConfiguration.getInt("ammunition.maxPenetrate");
        this.globalId = ammunitionEnum.getGlobalId();
        save();
    }

    public String getGlobalId() {
        return globalId;
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    public File getFile() {
        return file;
    }

    public void save() throws IOException {
        getFileConfiguration().save(getFile());
    }

    public ItemMetadata getLoadedItemMetaData() {
        return itemMetadata;
    }

    public int getMaxPenetrate() {
        return maxPenetrate;
    }
}
