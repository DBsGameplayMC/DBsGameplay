package net.dbsgameplay.blockbreaker.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
    sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Help-Blockbreaking Modus");
    sender.sendMessage(ChatColor.GOLD + " ");
    sender.sendMessage(ChatColor.GOLD + ">> sellall um Blöcke und Erze zu verkaufen");
    sender.sendMessage(ChatColor.GOLD + ">> bupgrade um Pickaxe upzugraden");
    sender.sendMessage(ChatColor.GOLD + ">> mine um die unterschiedlichen Minen zu betreten");
    return false;
  }
}
