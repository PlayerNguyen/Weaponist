package com.playernguyen.weaponist.asset;

public enum ItemType {
    GUN("gun"),
    AMMO("ammo"),
    THROWABLE("throwable")
    ;

    private final String prefix;

    ItemType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
