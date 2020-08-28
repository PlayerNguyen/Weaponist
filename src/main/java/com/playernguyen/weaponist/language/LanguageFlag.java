package com.playernguyen.weaponist.language;

import com.playernguyen.weaponist.flag.Flagable;

public enum LanguageFlag implements Flagable {
    PREFIX("prefix", "&8[&cWeapon&8]"),

    COMMAND_RESULT_INVALID_SENDER("command-result.invalid-sender", "&cCommand cannot use by this sender."),
    COMMAND_RESULT_MISSING_ARGUMENTS("command-result.missing-arguments", "&cCommand missing arguments"),
    COMMAND_USAGE_NO_PERMISSION("command-usage.no-permission", "&cYou have no permission to access this command."),
    COMMAND_RESULT_NOT_FOUND("command-result.not-found", "&cCommand not found!"),


    GENERAL_PLAYER_NOT_FOUND("general.player-not-found", "&cPlayer not found in server!"),
    GENERAL_AMMO_NOT_FOUND("general.ammo-not-found", "&cThe ammunition you need not found!"),
    GENERAL_AMMO_GIVEN("general.ammo-given", "&aSucceed give ammo to player!"),

    GENERAL_WEAPON_NOT_FOUND("general.weapon-not-found", "&cThe weapon you need not found!"),
    GENERAL_WEAPON_OUT_OF_AMMO("general.weapon-out-of-ammo", "&7ø &cOut of ammo &7ø"),
    GENERAL_GUN_RELOAD_CANCELLED("general.gun-reload-cancelled", "&6ø &cCancel reload &6ø"),

    GENERAL_TIPS_DROP("general.tips.how-to-drop", "&7To drop item, use &6SHIFT+Q"),

    ;

    private final String path;
    private final Object definite;

    LanguageFlag(String path, Object definite) {
        this.path = path;
        this.definite = definite;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Object getDefinite() {
        return definite;
    }
}
