package net.dbsgameplay.blockbreaker.commands;

import net.dbsgameplay.blockbreaker.utils.PickaxeDefine;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;



public class BlockBreakerDefCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Nur für Spieler");
            return true;
        }
        if (args.length != 4) {
            return true;
        }

        // Codeabschnitt für /blockbreaker define group <ResourceGroupName> <ResourceType>
        if (sender.hasPermission("dbsgameplay.blockbreaker.definegroup")){
        if (args[0].equalsIgnoreCase("define") && args[1].equalsIgnoreCase("group")) {

            String[] validOptions = {"pit", "wood", "shears", "mine", "farm"};
            if (Arrays.asList(validOptions).contains(args[3].toLowerCase())) {
                sender.sendMessage(ChatColor.GREEN + "Du hast erfolgreich die ResourceGroup " + args[2] + " erstellt, mit dem ResourceTyp " + args[3]);

            } else {
                sender.sendMessage(ChatColor.RED + "Ungültige Option für die ResourceGroup");
            }
        }}


        // Codeabschnitt für /blockbreaker define area <ResourceAreaName> <ResourceGroupName>
        if (sender.hasPermission("dbsgameplay.blockbreaker.definegroup")){
        if (args[0].equalsIgnoreCase("define") && args[1].equalsIgnoreCase("area")) {
            PickaxeDefine.addPickaxeDef((Player) sender);
            sender.sendMessage("Zwei Punkte mit der Pickaxe mit Links Klick auswählen");
        }}


        return true;
    }
}
