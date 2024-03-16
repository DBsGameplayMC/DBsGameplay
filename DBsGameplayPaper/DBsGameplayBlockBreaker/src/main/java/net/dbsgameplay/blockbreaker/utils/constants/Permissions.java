package net.dbsgameplay.blockbreaker.utils.constants;

import net.dbsgameplay.core.constants.PCPermissions;

public class Permissions {

    // region Permission-Pfade
    public static final String BLOCKBREAKER_BASE = PCPermissions.DBSGAMEPLAY_PREFIX + ".blockbreaker";

    public static final String CMD_BASE = BLOCKBREAKER_BASE + ".cmds";

    public static final String ADMINCMD_BASE = CMD_BASE + ".admin";
    // endregion

    // region Admin-Command Permissions
    public static final String BLOCKBREAKER_CMD_PERM = ADMINCMD_BASE + ".blockbreaker";
    // endregion
}
