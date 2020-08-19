package com.playernguyen.setting;

import com.playernguyen.config.ConfigurationWeaponist;

import java.io.IOException;

public class WeaponistSetting extends ConfigurationWeaponist {

    public WeaponistSetting() throws IOException {
        super("config.yml", SettingFlag.values());
    }

}
