package net.dbsgameplay.blockbreaker.commands;

import net.dbsgameplay.blockbreaker.utils.PickaxeStarter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BasicPickeCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
    Player player = (Player)sender;
    PickaxeStarter.addstarterPickaxe(player, 0, 0, 1);
    return false;
  }
}
