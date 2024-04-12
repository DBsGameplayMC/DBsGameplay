package net.dbsgameplay.core.constants;

public final class Permissions {

    // #region Präfixe
    /**
     * Permission-Präfix für Plugins des DBsGameplay Minecraft-Netzwerks.
     */
    public static final String DBSGAMEPLAY_PREFIX = "dbsgameplay";

    /**
     * Permission-Präfix für das DBsGameplay-Core-Plugin.
     */
    public static final String DBSGAMEPLAY_CORE = DBSGAMEPLAY_PREFIX + ".core";

    /**
     * Permission-Präfix für Team-Befehle und -Funktionen des DBsGameplay Core-Plugins.
     */
    public static final String DBSGAMEPLAY_TEAM = DBSGAMEPLAY_CORE + ".team";
    // #endregion Präfixe

    /**
     * Permission für alle Funktionen, Aktionen und Befehle des DBsGameplay-Netzwerks.
     */
    public static final String DBSGAMEPLAY_ALL = DBSGAMEPLAY_PREFIX + ".*";


    // #region Team
    /**
     * Permission, um den Flugmodus zu aktivieren oder deaktivieren.
     */
    public static final String FLY = DBSGAMEPLAY_TEAM + ".fly";

    /**
     * Permission, um den Flugmodus für andere Spieler zu aktivieren oder deaktivieren.
     */
    public static final String FLY_OTHERS = FLY + ".fly.others";
    // #endregion Team
}