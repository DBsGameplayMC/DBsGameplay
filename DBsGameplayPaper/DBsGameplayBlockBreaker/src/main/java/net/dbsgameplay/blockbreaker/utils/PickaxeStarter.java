package net.dbsgameplay.blockbreaker.utils;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PickaxeStarter {
public static void addstarterPickaxe(Player player, int fortune, int effi, int level) {
    ItemStack item = new ItemStack(Material.WOODEN_PICKAXE, 1);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(ChatColor.BLUE + "Pickaxe-Level Starter " + level);
    meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_UNBREAKABLE });
    ArrayList<String> lore = new ArrayList<>();
    meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
    meta.addEnchant(Enchantment.DURABILITY, 10000, true);
    meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, fortune, true);
    meta.addEnchant(Enchantment.DIG_SPEED, effi, true);
    lore.add(ChatColor.GOLD + ">> Effizienz " + effi);
    lore.add(ChatColor.GOLD + ">> Fortune " + fortune);
    meta.setLore(lore);
    item.setItemMeta(meta);
    player.getInventory().addItem(new ItemStack[] { item });
  }
}
