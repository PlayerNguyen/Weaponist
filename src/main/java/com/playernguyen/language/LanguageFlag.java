package com.playernguyen.language;

import com.playernguyen.flag.Flagable;

public enum LanguageFlag implements Flagable {
    PREFIX("prefix", "&8[&cWeapon&8]"),

    COMMAND_RESULT_INVALID_SENDER("command-result.invalid-sender", "&cCommand cannot use by this sender."),
    COMMAND_RESULT_MISSING_ARGUMENTS("command-result.missing-arguments", "&cCommand missing arguments"),
    COMMAND_USAGE_NO_PERMISSION("command-usage.no-permission", "&cYou have no permission to access this command."),
    COMMAND_RESULT_NOT_FOUND("command-result.not-found", "&cCommand not found!"),


    GENERAL_PLAYER_NOT_FOUND("general.player-not-found", "&cPlayer not found in server!"),
    GENERAL_AMMO_NOT_FOUND("general.ammo-not-found", "&cThe ammunition you need not found!"),

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
