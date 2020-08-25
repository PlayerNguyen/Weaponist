package com.playernguyen.asset;

public enum ItemTagEnum {

    AMMUNITION_VALID("is_ammo", true),
    AMMUNITION_ID("ammunition_id", ""),

    IS_WEAPON("is_weapon", true),
    WEAPON_ID("weapon_id", ""),
    WEAPON_AMMO_TYPE("weapon_ammo_type", ""),



    ;


    private final String key;
    private final Object initial;

    ItemTagEnum(String key, Object initial) {
        this.key = key;
        this.initial = initial;
    }

    public String getKey() {
        return key;
    }

    public Object getInitial() {
        return initial;
    }
}
