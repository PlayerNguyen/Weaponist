package com.playernguyen.weaponist.asset.gun;

import com.playernguyen.weaponist.asset.AssetKeyFlag;

public enum  GunConfigurationFlagEnum implements AssetKeyFlag {

    GENERAL_DISPLAY_NAME("Display", "display_"),


    ;

    private final String path;
    private final Object define;

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Object getDefine() {
        return define;
    }

    GunConfigurationFlagEnum(String path, Object define) {
        this.path = path;
        this.define = define;
    }
}
