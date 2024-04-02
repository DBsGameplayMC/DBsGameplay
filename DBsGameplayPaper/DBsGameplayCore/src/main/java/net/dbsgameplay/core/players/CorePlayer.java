package net.dbsgameplay.core.players;

import net.dbsgameplay.core.DBsGameplayCore;
import net.dbsgameplay.core.constants.ChatPrefixes;
import net.dbsgameplay.core.constants.Permissions;
import net.dbsgameplay.core.database.daos.NetworkPlayerDao;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

/**
 * Klasse, die ein org.bukkit.entity.Player-Objekt um zusätzliche Funktionen erweitert und für die Verwendung in den DBsGameplay-Plugins vorgesehen ist.
 */
public class CorePlayer {

    private final Player player;
    private String language;

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
        if (player.hasPermission("*") || player.hasPermission(Permissions.DBSGAMEPLAY_ALL) || player.hasPermission(permissionToCheck)) {
            return true;
        }

        Component message = Component.text()
                .append(Component.text(ChatPrefixes.ERROR))
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

    /**
     * Gibt die UUID des Spielers zurück.
     */
    public UUID getUniqueId() {
        return this.player.getUniqueId();
    }

    /**
     * Gibt den Sprach-Code des Spielers zurück.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Gibt die Sprache des Spielers in einem menschenfreundlichen Format zurück.
     */
    public String getLanguageHumanFriendly() {
        return switch (language) {
            case "de" -> "Deutsch";
            case "en" -> "English";
            default -> "UNKNOWN";
        };
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void saveLanguage() {
        NetworkPlayerDao networkPlayerDao = DBsGameplayCore.getInstance().getNetworkPlayerDao();
        networkPlayerDao.updateLanguage(this.getUniqueId().toString(), this.language);
    }

    // #region Message-Funktionen
    /**
     * Sendet eine Nachricht mit dem ChatPrefixes.NETWORK_PREFIX-Prefix an den Spieler.
     */
    public void sendNetworkMessage(String messageToSend) {
        player.sendMessage(ChatPrefixes.NETWORK_PREFIX + messageToSend);
    }

    public void sendDatabaseMessage(String messageToSend) {
        player.sendMessage(ChatPrefixes.DATABASE_PREFIX + messageToSend);
    }

    /**
     * Sendet eine Nachricht mit dem ChatPrefixes.ARROWS-Prefix an den Spieler.
     */
    public void sendArrowMessage(String messageToSend) {
        player.sendMessage(ChatPrefixes.ARROWS_POINTING_RIGHT + messageToSend);
    }

    /**
     * Sendet eine Nachricht mit dem ChatPrefixes.INFO-Prefix an den Spieler.
     */
    public void sendInfoMessage(String messageToSend) {
        player.sendMessage(ChatPrefixes.INFO + messageToSend);
    }

    /**
     * Sendet eine Nachricht mit dem ChatPrefixes.WARN-Prefix an den Spieler.
     */
    public void sendWarnMessage(String messageToSend) {
        player.sendMessage(ChatPrefixes.WARN + messageToSend);
    }

    /**
     * Sendet eine Nachricht mit dem ChatPrefixes.ERROR-Prefix an den Spieler.
     */
    public void sendErrorMessage(String messageToSend) {
        player.sendMessage(ChatPrefixes.ERROR + messageToSend);
    }
    // #endregion Message-Funktionen


}