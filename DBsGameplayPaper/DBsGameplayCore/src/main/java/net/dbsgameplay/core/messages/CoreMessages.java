package net.dbsgameplay.core.messages;

import net.dbsgameplay.core.interfaces.IMessageBase;

public enum CoreMessages implements IMessageBase {

    LANGUAGE_NAME("languagename"),

    // Common
    COMMON_NOPERMISSION("common.nopermission"),
    COMMON_PLAYERNOTONLINE("common.playernotonline"),
    COMMON_PLAYERNOTFOUND("common.playernotfound"),

    // Commands
    // Fly
   COMMANDS_FLY_ENABLED("commands.fly.enabled");
   // COMMANDS_FLY_DISABLED("commands.fly.disabled"),
   // COMMANDS_FLY_ENABLED_OTHERS("commands.fly.enabledothers"),
   // COMMANDS_FLY_DISABLED_OTHERS("commands.fly.disabledothers"),
   // COMMANDS_FLY_ENABLED_TARGET("commands.fly.enabledtarget"),
   // COMMANDS_FLY_DISABLED_TARGET("commands.fly.disabledtarget"),
//
   // COMMANDS_FLY_ERROR_SPEED_MISSING_ARGUMENTS("commands.fly.errorspeed.missingarguments"),
   // COMMANDS_FLY_ERROR_SPEED_CORRECT_USAGE("commands.fly.errorspeed.correctusage"),
//
   // COMMANDS_FLY_HELP_TITLE("commands.fly.help.title"),
   // COMMANDS_FLY_HELP_FLY("commands.fly.help.fly"),
   // COMMANDS_FLY_HELP_FLY_ON_OFF("commands.fly.help.flyonoff"),
   // COMMANDS_FLY_HELP_FLY_PLAYER("commands.fly.help.flyplayer"),
   // COMMANDS_FLY_HELP_FLY_PLAYER_ON_OFF("commands.fly.help.flyplayeronoff"),
   // COMMANDS_FLY_HELP_FLY_SPEED_SPEED("commands.fly.help.flyspeedspeed"),
   // COMMANDS_FLY_HELP_FLY_SPEED_RESET("commands.fly.help.flyspeedreset"),
   // COMMANDS_FLY_HELP_FLY_SPEED_PLAYER_SPEED("commands.fly.help.flyspeedplayerspeed"),
   // COMMANDS_FLY_HELP_FLY_SPEED_PLAYER_RESET("commands.fly.help.flyspeedplayerreset");

    private final String path;

    CoreMessages(String path) {
        this.path = path;
    }

    public String getYamlConfigurationPath() {
        return this.path;
    }
}
