package com.playernguyen.weaponist.setting;

import com.playernguyen.weaponist.flag.Flagable;

import java.io.File;

public enum SettingFlag implements Flagable {

    DEBUG_MODE("settings.debug-mode", false),

    HEADSHOT_ACCURACY("settings.headshot-accuracy", 0.6),
    WEAPON_FOLDER("settings.weapon-directory", File.separatorChar + "weapon"),
    AMMUNITION_FOLDER("settings.ammunition-directory", File.separatorChar + "ammunition"),

    SWAP_HAND_DURATION("settings.swap-hand-duration", 2);

    ;

    private final String path;
    private final Object definite;

    SettingFlag(String path, Object definite) {
        this.path = path;
        this.definite = definite;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Object getDefinite() {
        return definite;
    }
}
