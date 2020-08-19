package com.playernguyen.setting;

import com.playernguyen.flag.Flagable;

import java.io.File;

public enum SettingFlag implements Flagable {

    DEBUG_MODE("settings.debug-mode", false),
    WEAPON_FOLDER("settings.weapon-directory", File.separatorChar + "weapon"),

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
