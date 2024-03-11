package net.dbsgameplay.blockbreaker.commands;

import net.dbsgameplay.blockbreaker.utils.PickaxeDefine;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import net.dbsgameplay.blockbreaker.utils.ResourceGroupsConfig;
import java.util.Arrays;
import java.util.UUID;


public class BlockBreakerDefCommand implements CommandExecutor {
    private ResourceGroupsConfig resourceGroupsConfig;
    public BlockBreakerDefCommand(ResourceGroupsConfig resourceGroupsConfig) {
        this.resourceGroupsConfig = resourceGroupsConfig;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("[§c!§7] Command §cnur Ingame §7verfügbar");

            return true;
        }
        if (args.length != 4) {
            return true;
        }


        // Codeabschnitt für /blockbreaker define group <ResourceGroupName> <ResourceType>
        if (sender.hasPermission("dbsgameplay.blockbreaker.definegroup")) {
            if (args[0].equalsIgnoreCase("define") && args[1].equalsIgnoreCase("group")) {

                String[] validOptions = {"pit", "wood", "shears", "mine", "farm"};
                if (Arrays.asList(validOptions).contains(args[3].toLowerCase())) {
                    String groupId = UUID.randomUUID().toString();
                    String groupName = args[2];
                    String resourceType = args[3];
                    int baseXP = 0;
                    int level = 5;

                    resourceGroupsConfig.addResourceGroup(groupId, groupName, resourceType, baseXP, level);

                    sender.sendMessage("[§2I§7] Du hast §2erfolgreich §7die ResourceGroup " + groupName + " erstellt, mit dem ResourceTyp " + resourceType);
                } else {
                    sender.sendMessage("[§c!§7] §cUngültige Option §7für die ResourceGroup");
                }
            }
        }



        // Codeabschnitt für /blockbreaker define area <ResourceAreaName> <ResourceGroupName>
        if (sender.hasPermission("dbsgameplay.blockbreaker.definegroup")){
        if (args[0].equalsIgnoreCase("define") && args[1].equalsIgnoreCase("area")) {
            PickaxeDefine.addPickaxeDef((Player) sender);
            sender.sendMessage("[§2I§7] Zwei Punkte mit der Pickaxe mit Links Klick auswählen");
        }}


        return true;
    }
}
