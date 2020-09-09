package com.playernguyen.weaponist.command;

public enum  CommandPermissionEnum {
    COMMAND_ALL("weaponist.command.*"),

    COMMAND_GROUP_ADMIN("weaponist.admin"),

    COMMAND_WEAPON("weaponist.command.weapon"),
    COMMAND_AMMO("weaponist.command.ammo"),

    ;


    private final String node;

    CommandPermissionEnum(String node) {
        this.node = node;
    }

    public String getNode() {
        return node;
    }
}
