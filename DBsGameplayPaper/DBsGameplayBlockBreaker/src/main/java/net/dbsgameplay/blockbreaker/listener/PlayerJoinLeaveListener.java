package net.dbsgameplay.blockbreaker.listener;

import net.dbsgameplay.blockbreaker.DBsGameplayBlockBreaker;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerJoinLeaveListener implements Listener {

    private final DBsGameplayBlockBreaker plugin;

    public PlayerJoinLeaveListener(DBsGameplayBlockBreaker plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLeft(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.RED + "[-] " + ChatColor.WHITE + event.getPlayer().getName());
        Player player = event.getPlayer();
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
    }
}
