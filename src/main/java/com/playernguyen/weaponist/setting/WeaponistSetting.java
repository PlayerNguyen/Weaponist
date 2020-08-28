package com.playernguyen.weaponist.setting;

import com.playernguyen.weaponist.config.ConfigurationWeaponist;

import java.io.IOException;

public class WeaponistSetting extends ConfigurationWeaponist {

    public WeaponistSetting() throws IOException {
        super("config.yml", SettingFlag.values());
    }

}
