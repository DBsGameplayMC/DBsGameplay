package net.dbsgameplay.blockbreaker.utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PickaxeDefine {

    public static void addPickaxeDef(Player player){

        ItemStack pickaxe = new ItemStack(Material.WOODEN_PICKAXE);
        ItemMeta metapickaxe = pickaxe.getItemMeta();
        metapickaxe.setDisplayName(ChatColor.GREEN + "ResourceArea definieren");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + ">> Positionen auswählen mit Links Klick");
        metapickaxe.setLore(lore);
        pickaxe.setItemMeta(metapickaxe);
        player.getInventory().addItem(pickaxe);
    }
}

