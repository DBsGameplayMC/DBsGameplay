package me.crayson.dbsgameplayadmintools.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ManagesupGUI {

    public ManagesupGUI(Player player, Player target){

        Inventory inventory = Bukkit.createInventory(null,27, ChatColor.YELLOW + "Managesup-Menu");

        ItemStack targetHead = new ItemStack(Material.PLAYER_HEAD,1);
        ItemMeta targetHeadMeta = targetHead.getItemMeta();
        targetHeadMeta.setDisplayName(target.getName());
        targetHead.setItemMeta(targetHeadMeta);
        inventory.setItem(22, targetHead);

        ItemStack kick = new ItemStack(Material.NETHERITE_BOOTS,1);
        ItemMeta kickmeta = kick.getItemMeta();
        kickmeta.setDisplayName(ChatColor.BLUE + "Spieler kicken");
        kick.setItemMeta(kickmeta);

        inventory.setItem(4,kick);
        ItemStack freezen = new ItemStack(Material.ICE,1);
        ItemMeta freezenmeta = freezen.getItemMeta();
        freezenmeta.setDisplayName(ChatColor.BLUE + "Spieler freezen");
        freezen.setItemMeta(freezenmeta);

        inventory.setItem(11,freezen);

        ItemStack entfreezen = new ItemStack(Material.LAVA_BUCKET,1);
        ItemMeta entfreezenmeta = entfreezen.getItemMeta();
        entfreezenmeta.setDisplayName(ChatColor.GOLD + "Spieler entfreezen");
        entfreezen.setItemMeta(entfreezenmeta);

        inventory.setItem(15,entfreezen);
        player.openInventory(inventory);



    }}
