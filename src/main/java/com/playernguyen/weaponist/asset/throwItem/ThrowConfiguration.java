package com.playernguyen.weaponist.asset.throwItem;

import com.playernguyen.weaponist.asset.AssetConfig;

public class ThrowConfiguration extends AssetConfig<ThrowEnum, ThrowFlags> {

    public static final String PARENT_THROW = "throw";

    public ThrowConfiguration(ThrowEnum assetEnum, ThrowFlags[] defaultLoader) {
        super(assetEnum, defaultLoader, PARENT_THROW);
        // Setter
        set(ThrowFlags.METADATA_DISPLAY_NAME, assetEnum.getDisplay());
        set(ThrowFlags.METADATA_DESCRIPTION, assetEnum.getDescription());
        set(ThrowFlags.METADATA_MATERIAL, assetEnum.getMaterial());
        set(ThrowFlags.ATTRIBUTE_EXPLODE_TIME, assetEnum.getExplodeTime());
        set(ThrowFlags.ATTRIBUTE_GENERIC_POWER, assetEnum.getPower());
        set(ThrowFlags.ATTRIBUTE_EXPLODING_TIME, assetEnum.getExplodingTime());
        // Save
        save();
    }
}
