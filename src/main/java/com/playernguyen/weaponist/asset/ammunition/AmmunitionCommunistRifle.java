package com.playernguyen.weaponist.asset.ammunition;

import java.io.IOException;

public class AmmunitionCommunistRifle extends DefaultAmmunition {

    /**
     * 7.62mm rifle class
     * @throws IOException cannot save the configuration
     */
    public AmmunitionCommunistRifle() throws IOException {
        super(AmmunitionEnum.COMMUNIST_RIFLE);
    }
}
