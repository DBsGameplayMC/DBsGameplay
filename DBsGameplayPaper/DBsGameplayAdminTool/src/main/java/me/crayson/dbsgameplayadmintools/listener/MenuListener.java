package me.crayson.dbsgameplayadmintools.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.time.LocalDate;
import javax.xml.stream.Location;
import java.net.http.WebSocket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MenuListener implements Listener {

    @EventHandler
    public void onInventoryCLick(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals(ChatColor.RED + "Manage-Menu")) {
            return;
        }

        event.setCancelled(true);

        if (event.getCurrentItem() == null) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        Player target = Bukkit.getPlayer(event.getView().getItem(22).getItemMeta().getDisplayName());

        if (target == null) {
            player.closeInventory();
            player.sendMessage("not online");
            return;
        }

        if (event.getCurrentItem().getType() == Material.RED_BANNER) {
            target.setHealth(20);
            player.closeInventory();
            player.sendMessage("Geheilt");
            return;
        }

        if (event.getCurrentItem().getType() == Material.NETHERITE_SWORD){
        target.setHealth(0);
        player.closeInventory();
        player.sendMessage("Getötet");
        return;
    }
        if (event.getCurrentItem().getType() == Material.NETHERITE_BOOTS){
            player.closeInventory();
            target.kickPlayer(ChatColor.RED + "Gekickt von " + player.getName());
            player.sendMessage("Player wurde gekickt");
            return;
        }
        if (event.getCurrentItem().getType() == Material.BARRIER){
            player.closeInventory();
            target.ban(ChatColor.RED + "Du wurdest von " + player.getName() + " gebannt", (Date) null,target.getName() +" wurde von "+ player.getName() + " gebannt");
            player.sendMessage(target.getName() + " wurde gebannt");
            return;
        }
        if (event.getCurrentItem().getType() == Material.ICE){
            player.closeInventory();
            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,Integer.MAX_VALUE, 255));
            target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,Integer.MAX_VALUE, 255));
            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,Integer.MAX_VALUE,255));
            target.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,Integer.MAX_VALUE,255));

            player.sendMessage(ChatColor.GREEN + target.getName() + " wurde gefreezt");
            return;
        }
        if (event.getCurrentItem().getType() == Material.LAVA_BUCKET){
            player.closeInventory();
            target.removePotionEffect(PotionEffectType.SLOW);
            target.removePotionEffect(PotionEffectType.WEAKNESS);
            target.removePotionEffect(PotionEffectType.SLOW_DIGGING);
            target.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            player.sendMessage(ChatColor.GREEN + target.getName() + " wurde unfreezt");
            return;
        }


    }}



