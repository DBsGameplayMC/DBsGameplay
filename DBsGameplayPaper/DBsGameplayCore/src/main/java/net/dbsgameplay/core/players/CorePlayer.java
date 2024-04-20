package net.dbsgameplay.core.players;

import net.dbsgameplay.core.DBsGameplayCore;
import net.dbsgameplay.core.constants.ChatPrefixes;
import net.dbsgameplay.core.constants.Permissions;
import net.dbsgameplay.core.database.daos.NetworkPlayerDao;
import net.dbsgameplay.core.database.entities.NetworkPlayer;
import net.dbsgameplay.core.database.results.DbResult;
import net.dbsgameplay.core.database.results.DbReturn;
import net.dbsgameplay.core.enums.Locale;
import net.dbsgameplay.core.enums.ResultType;
import net.dbsgameplay.core.messages.MessageKey;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Klasse, die ein org.bukkit.entity.Player-Objekt um zusätzliche Funktionen erweitert und für die Verwendung in den DBsGameplay-Plugins vorgesehen ist.
 */
public class CorePlayer {

    private final Player player;
    private final NetworkPlayerDao networkPlayerDao;

    // #region Eigenschaften
    private Locale locale;
    // #endregion Eigenschaften


    // #region Constructors
    public CorePlayer(Player player, NetworkPlayerDao networkPlayerDao) {
        this.player = player;
        this.networkPlayerDao = networkPlayerDao;

        this.init();
    }

    public CorePlayer(UUID playerUUID, NetworkPlayerDao networkPlayerDao) {
        this.player = Bukkit.getServer().getPlayer(playerUUID);
        this.networkPlayerDao = networkPlayerDao;

        this.init();
    }
    // #endregion


    /**
     * Initialisiert den CorePlayer.
     */
    private void init() {
        DbReturn playerRegisteredResult = this.networkPlayerDao.isPlayerRegistered(this.player.getUniqueId().toString());

        if (playerRegisteredResult.getResultType() == ResultType.ERROR) {
            this.player.kickPlayer(ChatPrefixes.NETWORK_PREFIX + "Ein §cFehler §7ist aufgetreten, während wir deine Spieler-Daten geladen haben. Die Datenbank scheint nicht erreichbar zu sein. \n\n §bSollte das Problem weiterhin bestehen, öffne bitte ein Ticket auf unserem Discord!.");
            return;
        }

        if (playerRegisteredResult.getResultType() == ResultType.NOT_FOUND) {
            DbReturn registerPlayerResult = this.networkPlayerDao.registerPlayer(new NetworkPlayer(this));

            if (!registerPlayerResult.isSuccessful()) {
                this.player.kickPlayer(ChatPrefixes.NETWORK_PREFIX + "Ein §cFehler §7ist aufgetreten, während wir deine Spieler-Daten angelegt haben. Bitte versuche es erneut. \n\n §bSollte das Problem weiterhin bestehen, öffne bitte ein Ticket auf unserem Discord!.");
                return;
            }
        }

        DbResult<NetworkPlayer> networkPlayerResult = getNetworkPlayer();
        System.out.println("ResultType: " + networkPlayerResult.getResultType() + " - Message   : " + networkPlayerResult.getMessage());
        if (!networkPlayerResult.isSuccessful()) {

            this.player.kickPlayer(ChatPrefixes.NETWORK_PREFIX + "Ein §cFehler §7ist aufgetreten, während wir deine Spieler-Daten geladen haben. Bitte versuche es erneut. \n\n §bSollte das Problem weiterhin bestehen, öffne bitte ein Ticket auf unserem Discord!.");
            return;
        }

        NetworkPlayer networkPlayer = networkPlayerResult.getResult();

        // Setze Eigenschaften
        assert networkPlayer != null;
        this.locale = Locale.fromLocaleCode(networkPlayer.getLanguage());
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


    // # region Sprache

    /**
     * Gibt den Sprach-Code des Spielers zurück.
     */
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        if (!saveLanguage().isSuccessful()) {
            sendErrorMessage("Deine Sprache konnte nicht gespeichert werden. Bitte versuche es erneut.");
            return;
        }

        this.locale = locale;

        sendInfoMessage("Deine Sprache §aerfolgreich wurde auf " + getLanguageHumanFriendly() + " geändert.");
    }

    /**
     * Gibt die Sprache des Spielers in einem menschenfreundlichen Format zurück.
     */
    public String getLanguageHumanFriendly() {
        return switch (locale) {
            case GERMAN -> "Deutsch";
            case ENGLISH -> "English";
        };
    }
    // #endregion Sprache


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

    public void sendTestMessage(MessageKey key) {
        player.sendMessage(getMessageFromKey(key));
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

    private String getMessageFromKey(MessageKey key) {
        return DBsGameplayCore.getInstance().getMessageFactory().getMessage(key, this.locale.getLocale());
    }
    // #endregion Message-Funktionen


    // #region Datenbankfunktionen
    private DbResult<NetworkPlayer> getNetworkPlayer() {
        DbResult<NetworkPlayer> playerResult = this.networkPlayerDao.getPlayer(this.player.getUniqueId().toString());

        if (!playerResult.isSuccessful()) {
            return new DbResult<>(null, NetworkPlayer.class, playerResult.getMessage(), playerResult.getResultType());
        }

        return new DbResult<>(playerResult.getResult(), NetworkPlayer.class, "Player data loaded", ResultType.SUCCESS);
    }

    /**
     * Speichert die Sprache des Spielers in der Datenbank.
     */
    private DbReturn saveLanguage() {
        return this.networkPlayerDao.updateLanguage(this.getUniqueId().toString(), this.locale.getLocale());
    }
    // #endregion Datenbankfunktionen

}