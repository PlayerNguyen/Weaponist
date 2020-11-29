package com.playernguyen.weaponist.asset.throwItem;

import com.playernguyen.weaponist.asset.AssetConfig;

public class ThrowConfiguration extends AssetConfig<ThrowEnum, ThrowFlags> {

    public static final String PARENT_THROW = "throw";

    public ThrowConfiguration(ThrowEnum assetEnum, ThrowFlags[] defaultLoader) {
        super(assetEnum, defaultLoader, PARENT_THROW);
        // Setter
        nullSet(ThrowFlags.METADATA_DISPLAY_NAME, assetEnum.getDisplay());
        nullSet(ThrowFlags.METADATA_DESCRIPTION, assetEnum.getDescription());
        nullSet(ThrowFlags.METADATA_MATERIAL, assetEnum.getMaterial());
        nullSet(ThrowFlags.ATTRIBUTE_EXPLODE_TIME, assetEnum.getExplodeTime());
        nullSet(ThrowFlags.ATTRIBUTE_GENERIC_POWER, assetEnum.getPower());
        nullSet(ThrowFlags.ATTRIBUTE_EXPLODING_TIME, assetEnum.getExplodingTime());
        // Save
        save();
    }

    @Override
    protected void onInit() {

    }
}
