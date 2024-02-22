package net.dbsgameplay.blockbreaker.listener;

import net.dbsgameplay.blockbreaker.guis.UpgradeGui;
import net.dbsgameplay.blockbreaker.items.PickaxeStarter;
import org.bukkit.ChatColor;
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
  
  @EventHandler
  public void onInventoryClick(InventoryClickEvent event) {
    if (event.getView().getTitle().equals(ChatColor.GREEN + "Pickaxe Upgraden")) {
      event.setCancelled(true);
      Player player = (Player)event.getWhoClicked();
      ItemStack clickedItem = event.getCurrentItem();
      if (clickedItem == null || clickedItem.getType() == Material.AIR)
        return; 
      if (clickedItem.getType() == Material.BARREL)
        new UpgradeGui(player); 
      String displayName = getItemDisplayNameInPlayerInventory(player, Material.WOODEN_PICKAXE);
      if (displayName != null && displayName.startsWith(ChatColor.BLUE + "Pickaxe-Level ")) {
        int currentLevel = Integer.parseInt(displayName.substring(displayName.lastIndexOf(" ") + 1));
        if (currentLevel >= 5)
          return; 
        int requiredPaper = currentLevel + 10;
        if (countItems(player, Material.PAPER) < requiredPaper) {
          player.sendMessage(ChatColor.RED + "Nicht genug Geld");
          return;
        } 
        player.getInventory().remove(Material.WOODEN_PICKAXE);
        if (currentLevel == 1) {
          PickaxeStarter.addstarterPickaxe(player, 0, 1, 2);
        } else if (currentLevel == 2) {
          PickaxeStarter.addstarterPickaxe(player, 0, 2, 3);
        } else if (currentLevel == 3) {
          PickaxeStarter.addstarterPickaxe(player, 0, 3, 4);
        } else if (currentLevel == 4) {
          PickaxeStarter.addstarterPickaxe(player, 0, 4, 5);
        } 
        player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.PAPER, requiredPaper) });
        player.sendMessage(ChatColor.GREEN + "Pickaxe auf Level " + (currentLevel + 1) + " upgegradet");
        if (displayName == null)
          return; 
      } 
    } 
  }
}
