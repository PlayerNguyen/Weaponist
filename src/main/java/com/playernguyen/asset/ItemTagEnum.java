package com.playernguyen.asset;

public enum ItemTagEnum {

    AMMUNITION_VALID("ammunition_valid", true),

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
