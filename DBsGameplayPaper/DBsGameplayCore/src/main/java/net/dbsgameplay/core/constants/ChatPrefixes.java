package net.dbsgameplay.core.constants;

/**
 * Enthält die Chat-Präfixe für den Minecraft-Chat.
 */
public final class ChatPrefixes {

    public static final String PREFIX_RGB = "§x§9§8§6§3§E§7D§x§9§2§7§0§E§4B§x§8§D§7§C§E§0s§x§8§7§8§9§D§DG§x§8§1§9§6§D§9a§x§7§C§A§3§D§6m§x§7§6§A§F§D§3e§x§7§0§B§C§C§Fp§x§6§A§C§9§C§Cl§x§6§5§D§5§C§8a§x§5§F§E§2§C§5y";

    /**
     * Präfix des DBsGameplay Minecraft-Netzwerks.
     */
    public static final String NETWORK_PREFIX = applyPrefixTemplate(PREFIX_RGB);

    /**
     * Präfix für Datenbankoperationen.
     */
    public static final String DATABASE_PREFIX =  applyPrefixTemplate("§5§lDatenbank");

    /**
     * Doppelte Pfeile nach rechts.
     */
    public static final String ARROWS_POINTING_RIGHT = "§7>§8> §7";

    /**
     * Doppelte Pfeile nach links.
     */
    public static final String ARROWS_POINTING_LEFT = "§7 <§8<";

    /**
     * Präfix für Informationen.
     */
    public static final String INFO =  applyPrefixTemplate("§a§lInfo");

    /**
     * Präfix für Warnungen.
     */
    public static final String WARN = applyPrefixTemplate("§e§lWarnung");

    /**
     * Präfix für Fehler.
     */
    public static final String ERROR = applyPrefixTemplate("§c§lFehler");

    /**
     * Fügt das Präfix-Template zu einem Präfix hinzu.
     */
    public static String applyPrefixTemplate(String prefix) {
        return"§8§l| §r" + prefix + " §7>§8> §7";
    }

}
