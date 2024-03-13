package net.dbsgameplay.blockbreaker.commands;

import net.dbsgameplay.blockbreaker.DBsGameplayBlockBreaker;
import net.dbsgameplay.blockbreaker.utils.PickaxeDefine;
import org.bukkit.block.Block;
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
    private DBsGameplayBlockBreaker plugin;
    public BlockBreakerDefCommand(DBsGameplayBlockBreaker plugin, ResourceGroupsConfig resourceGroupsConfig){
        this.resourceGroupsConfig = resourceGroupsConfig;
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§7[§c!§7] Command §cnur Ingame §7verfügbar");

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

                    sender.sendMessage("§7[§2I§7] Du hast §2erfolgreich §7die ResourceGroup " + groupName + " erstellt, mit dem ResourceTyp " + resourceType);
                } else {
                    sender.sendMessage("§7[§c!§7] §cUngültige Option §7für die ResourceGroup");
                }
            }
        }



        // Codeabschnitt für /blockbreaker define area <ResourceAreaName> <ResourceGroupName>
        if (sender.hasPermission("dbsgameplay.blockbreaker.definegroup")){
        if (args[0].equalsIgnoreCase("define") && args[1].equalsIgnoreCase("area")) {
        if (this.plugin.existsResourceGroup(args[3])){
            sender.sendMessage("§7[§2I§7] Markiere mit der Pickaxe §2zwei Punkte");
            PickaxeDefine.addPickaxeDef(((Player) sender).getPlayer());
        }else {
            sender.sendMessage("§7[§c!§7] §cUngültige Option §7für die ResourceGroup");
        }
        }


        return true;
    }
        return false;
    }}
