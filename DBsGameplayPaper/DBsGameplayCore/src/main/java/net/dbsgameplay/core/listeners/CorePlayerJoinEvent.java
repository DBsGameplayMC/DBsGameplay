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

        CorePlayer corePlayer = new CorePlayer(event.getPlayer(), plugin.getNetworkPlayerDao());
        this.plugin.getPlayerHandler().addPlayer(corePlayer);


        corePlayer.sendNetworkMessage("§7Willkommen auf " + ChatPrefixes.PREFIX_RGB + "§7, §6" + corePlayer.getPlayer().getName() + "§7!");
        corePlayer.sendDatabaseMessage("Alle Spieler-Daten wurden §aerfolgreich §7geladen.");
        corePlayer.sendInfoMessage("§7Du hast die Sprache §b" + corePlayer.getLanguageHumanFriendly() + " §7ausgewählt.");

        event.setJoinMessage("§8[§a+§8] §7" + event.getPlayer().getName());
    }
}