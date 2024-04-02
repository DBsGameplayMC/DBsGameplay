package net.dbsgameplay.core.listeners;

import net.dbsgameplay.core.DBsGameplayCore;
import net.dbsgameplay.core.constants.ChatPrefixes;
import net.dbsgameplay.core.database.entities.NetworkPlayer;
import net.dbsgameplay.core.players.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Optional;

public class CorePlayerJoinEvent implements Listener {

    private final DBsGameplayCore plugin;

    public CorePlayerJoinEvent(DBsGameplayCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCorePlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("§8[§a+§8] §7" + event.getPlayer().getName());

        Player player = event.getPlayer();
        CorePlayer corePlayer = new CorePlayer(player);
        boolean playerExists = plugin.getNetworkPlayerDao().isPlayerRegistered(player.getUniqueId().toString());

        if (!playerExists) {
            plugin.getNetworkPlayerDao().registerPlayer(new NetworkPlayer(corePlayer));
        }

        Optional<NetworkPlayer> networkPlayer = plugin.getNetworkPlayerDao().getPlayer(player.getUniqueId().toString());

        if (networkPlayer.isEmpty()) {
            player.kickPlayer(ChatPrefixes.NETWORK_PREFIX + "Ein §cFehler §7ist aufgetreten, während wir deine Spieler-Daten geladen haben. Bitte versuche es erneut. \n\n §bSollte das Problem weiterhin bestehen, öffne bitte ein Ticket auf unserem Discord!.");
            return;
        }

        corePlayer.setLanguage(networkPlayer.get().getLanguage());

        plugin.getPlayerHandler().addPlayer(corePlayer);

        corePlayer.sendNetworkMessage("§7Willkommen auf " + ChatPrefixes.PREFIX_RGB + "§7, §6" + player.getName() + "§7!");
        corePlayer.sendDatabaseMessage("Alle Spieler-Daten wurden §aerfolgreich §7geladen.");
        corePlayer.sendInfoMessage("§7Du hast die Sprache §b" + corePlayer.getLanguageHumanFriendly() + " §7ausgewählt.");
    }
}