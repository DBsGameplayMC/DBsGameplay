package net.dbsgameplay.core;

import org.bukkit.ChatColor;

/**
 * Enthält die Chat-Präfixe für den Minecraft-Chat.
 */
public final class ChatPrefixes {

    /**
     * Präfix für Informationen.
     */
    public static final String INFO = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + ChatColor.BOLD + "I" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;

    /**
     * Präfix für Warnungen.
     */
    public static final String WARNING = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "!" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;

    /**
     * Präfix für Fehler.
     */
    public static final String Error = ChatColor.DARK_GRAY + "[" + ChatColor.RED + ChatColor.BOLD + "!" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
}
