package com.playernguyen.weaponist.asset;

public enum ItemTagEnum {

    IS_AMMO("is_ammo", true),
    @Deprecated AMMUNITION_ID("ammunition_id", ""),

    IS_WEAPON("is_weapon", true),
    ITEM_TYPE("item_type", "?"),

    IS_THROWABLE("is_throwable", true),

    @Deprecated WEAPON_ID("weapon_id", ""),

    ITEM_ID("item_id", null),

    WEAPON_AMMO_TYPE("weapon_ammo_type", ""),

    GUN_AMMO("gun_ammo", 0),
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
