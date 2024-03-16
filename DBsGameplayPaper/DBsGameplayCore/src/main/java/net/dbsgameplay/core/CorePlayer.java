package net.dbsgameplay.core;

import net.dbsgameplay.core.constants.PCChatPrefixes;
import net.dbsgameplay.core.constants.PCPermissions;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CorePlayer {

    private final Player player;

    public CorePlayer(Player player) {
        this.player = player;
    }

    public CorePlayer(UUID playerUUID) {
        this.player = Bukkit.getServer().getPlayer(playerUUID);
    }

    /**
     * Prüft, ob der Spieler die angegebene Berechtigung hat.
     * Gibt true zurück, wenn der Spieler die Berechtigung hat, ansonsten false.
     * <br/> <br/>
     * <h3>WICHTIG:</h3>
     * Besitzt der Spieler die Berechtigung "dbsgameplay.*" oder "*", wird immer true zurückgegeben.
     */
    public boolean hasPermission(String permissionToCheck) {
        if (player.hasPermission("*") || player.hasPermission(PCPermissions.DBSGAMEPLAY_ALL) || player.hasPermission(permissionToCheck)) {
            return true;
        }

        Component message = Component.text()
                .append(Component.text(PCChatPrefixes.ERROR))
                .append(Component.text("§7Dafür hast du "))
                .append(Component.text("§ckeine"))
                .append(Component.text(" §7§oBerechtigung§7!").hoverEvent(HoverEvent.showText(Component.text("§7Fehlende Berechtigung: §c" + permissionToCheck).color(NamedTextColor.GRAY))))
                .build();

        player.sendMessage(message);
        return false;
    }

    /**
     * Gibt das org.bukkit.entity.Player-Objekt des CorePlayers zurück.
     */
    public Player getPlayer() {
        return this.player;
    }

    // #region Message-Funktionen
    /**
     * Sendet eine Nachricht mit dem ChatPrefixes.NETWORK_PREFIX-Prefix an den Spieler.
     */
    public void sendCoreMessage(String messageToSend) {
        player.sendMessage(PCChatPrefixes.NETWORK_PREFIX + messageToSend);
    }

    /**
     * Sendet eine Nachricht mit dem ChatPrefixes.ARROWS-Prefix an den Spieler.
     */
    public void sendArrowMessage(String messageToSend) {
        player.sendMessage(PCChatPrefixes.ARROWS + messageToSend);
    }

    /**
     * Sendet eine Nachricht mit dem ChatPrefixes.INFO-Prefix an den Spieler.
     */
    public void sendInfoMessage(String messageToSend) {
        player.sendMessage(PCChatPrefixes.INFO + messageToSend);
    }

    /**
     * Sendet eine Nachricht mit dem ChatPrefixes.WARN-Prefix an den Spieler.
     */
    public void sendWarnMessage(String messageToSend) {
        player.sendMessage(PCChatPrefixes.WARN + messageToSend);
    }

    /**
     * Sendet eine Nachricht mit dem ChatPrefixes.ERROR-Prefix an den Spieler.
     */
    public void sendErrorMessage(String messageToSend) {
        player.sendMessage(PCChatPrefixes.ERROR + messageToSend);
    }
    // #endregion Message-Funktionen


}