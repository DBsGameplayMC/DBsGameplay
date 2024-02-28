package me.crayson.dbsgameplayadmintools.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GamemodesupCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Dieser Befehl kann nur von Spielern verwendet werden.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Verwendung: /gamemodesup <gamemode>");
            return true;
        }

        if (args[0].equals("survival")){
            sender.sendMessage(ChatColor.GREEN + "In den Gamemode Survival gewechselt");
            ((Player) sender).setGameMode(GameMode.SURVIVAL);
        }else if (args[0].equals("spectator")){
            sender.sendMessage(ChatColor.BLUE + "In den Gamemode Spectator gewechselt");
            ((Player) sender).setGameMode(GameMode.SPECTATOR);
        }else {
            sender.sendMessage(ChatColor.RED + "Error");
        }

            return false;
    }
}
