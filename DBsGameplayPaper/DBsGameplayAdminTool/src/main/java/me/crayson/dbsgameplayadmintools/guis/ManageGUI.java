package me.crayson.dbsgameplayadmintools.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ManageGUI {

    public ManageGUI(Player player, Player target){

        Inventory inventory = Bukkit.createInventory(null,27, ChatColor.RED + "Manage-Menu");

        ItemStack targetHead = new ItemStack(Material.PLAYER_HEAD,1);
        ItemMeta targetHeadMeta = targetHead.getItemMeta();
        targetHeadMeta.setDisplayName(target.getName());
        targetHead.setItemMeta(targetHeadMeta);
        inventory.setItem(22, targetHead);


        ItemStack heal = new ItemStack(Material.RED_BANNER , 1);
        ItemMeta healmeta = heal.getItemMeta();
        healmeta.setDisplayName(ChatColor.RED + "Heilen");
        heal.setItemMeta(healmeta);

        inventory.setItem(1 , heal);

        ItemStack kill = new ItemStack(Material.NETHERITE_SWORD , 1);
        ItemMeta killMeta = kill.getItemMeta();
        killMeta.setDisplayName(ChatColor.RED + "Töten");
        kill.setItemMeta(killMeta);

        inventory.setItem(7 , kill);

        ItemStack kick = new ItemStack(Material.NETHERITE_BOOTS,1);
        ItemMeta kickmeta = kick.getItemMeta();
        kickmeta.setDisplayName(ChatColor.BLUE + "Spieler kicken");
        kick.setItemMeta(kickmeta);

        inventory.setItem(9,kick);

        ItemStack ban = new ItemStack(Material.BARRIER,1);
        ItemMeta banmeta = ban.getItemMeta();
        banmeta.setDisplayName(ChatColor.RED + "Spieler bannen");
        ban.setItemMeta(banmeta);

        inventory.setItem(11,ban);

        ItemStack freezen = new ItemStack(Material.ICE,1);
        ItemMeta freezenmeta = freezen.getItemMeta();
        freezenmeta.setDisplayName(ChatColor.BLUE + "Spieler freezen");
        freezen.setItemMeta(freezenmeta);

        inventory.setItem(15,freezen);

        ItemStack entfreezen = new ItemStack(Material.LAVA_BUCKET,1);
        ItemMeta entfreezenmeta = entfreezen.getItemMeta();
        entfreezenmeta.setDisplayName(ChatColor.GOLD + "Spieler entfreezen");
        entfreezen.setItemMeta(entfreezenmeta);

        inventory.setItem(17,entfreezen);





        player.openInventory(inventory);
    }
}
