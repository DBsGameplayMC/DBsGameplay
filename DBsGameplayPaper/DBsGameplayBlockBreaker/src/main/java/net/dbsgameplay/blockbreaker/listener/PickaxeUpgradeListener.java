package net.dbsgameplay.blockbreaker.listener;

import net.dbsgameplay.blockbreaker.guis.UpgradeGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class PickaxeUpgradeListener implements Listener {
  private int countItems(Player player, Material material) {
    int count = 0;
    PlayerInventory inventory = player.getInventory();
    ItemStack[] contents = inventory.getContents();
    for (ItemStack item : contents) {
      if (item != null && item.getType() == material)
        count += item.getAmount(); 
    } 
    return count;
  }
  
  private String getItemDisplayNameInPlayerInventory(Player player, Material material) {
    PlayerInventory inventory = player.getInventory();
    ItemStack[] contents = inventory.getContents();
    for (ItemStack item : contents) {
      if (item != null && item.getType() == material) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.hasDisplayName())
          return meta.getDisplayName(); 
      } 
    } 
    return null;
  }
}
