package com.playernguyen.weaponist.asset;

public enum ItemTagEnum {

    AMMUNITION_VALID("is_ammo", true),
    AMMUNITION_ID("ammunition_id", ""),

    IS_WEAPON("is_weapon", true),
    WEAPON_ID("weapon_id", ""),
    WEAPON_AMMO_TYPE("weapon_ammo_type", ""),

    WEAPON_ACCESSORY_1("wp_accessory_1", ""),
    WEAPON_ACCESSORY_2("wp_accessory_2", ""),
    WEAPON_ACCESSORY_3("wp_accessory_3", ""),
    WEAPON_ACCESSORY_4("wp_accessory_4", ""),



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
