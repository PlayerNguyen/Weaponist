package com.playernguyen.weaponist.asset.ammunition;

import java.io.IOException;

public class AmmunitionPistol extends DefaultAmmunition {

    /**
     * Pistol class
     *
     * @throws IOException Cannot save the configuration file
     */
    public AmmunitionPistol() throws IOException {
        super(AmmunitionEnum.PISTOL);

    }
}
