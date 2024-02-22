package me.crayson.dbsgameplayadmintools.commands;

import me.crayson.dbsgameplayadmintools.guis.ManageGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ManageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Nur Spieler");
            return true;
        }
        Player player = (Player) sender;
        if(args.length != 1){
            player.sendMessage(ChatColor.RED + " Nützen: /manage <player>");
            return true;
        }

        if(Bukkit.getPlayer(args[0]) == null){
            player.sendMessage(ChatColor.RED + "Spieler nicht gefunden!");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        new ManageGUI(player, target);
        return true;

    }
}
