package net.dbsgameplay.blockbreaker.commands.admin;

import net.dbsgameplay.blockbreaker.DBsGameplayBlockBreaker;
import net.dbsgameplay.blockbreaker.commands.controlling.interfaces.IConfigCommandExecutor;
import net.dbsgameplay.blockbreaker.utils.BasePlayer;
import net.dbsgameplay.blockbreaker.utils.constants.Permissions;
import net.dbsgameplay.blockbreaker.utils.enums.ResourceGroupType;
import net.dbsgameplay.blockbreaker.utils.models.MdlResourceGroupConfig;
import net.dbsgameplay.core.ConfigHandler;
import org.bukkit.command.Command;

public class CmdBlockBreaker implements IConfigCommandExecutor<MdlResourceGroupConfig> {

    public static final String NAME = "blockbreaker";

    @Override
    public Boolean onCommand(BasePlayer basePlayer, Command command, String[] arguments, DBsGameplayBlockBreaker plugin, ConfigHandler<MdlResourceGroupConfig> resouceGroupConfig) {

        if (!basePlayer.hasPermission(Permissions.BLOCKBREAKER_CMD_PERM)) return false;

        if (arguments.length == 0) {
            basePlayer.sendErrorMessage("Fehlende Argumente für den Befehl §b/BlockBreaker§7.");
            basePlayer.sendArrowMessage("Verfügbare Argumente für den Befehl §8/§bBlockBreaker§7:");
            basePlayer.sendArrowMessage("Argument \"§bDefine§7\":");
            basePlayer.sendArrowMessage("§bDefine Group §8<§aName§8> §8<§3ResourceAreaType§8> §8<§eBaseXP§8> §8<§eLevel§8>");
            basePlayer.sendArrowMessage("§bDefine Area §8<§bResourceAreaName§8> §8<§3ResourceArea§8>");
            return false;
        }

        // region Argument "Define"
        if (arguments[0].equalsIgnoreCase("define")) {

            if (arguments.length == 1) {
                basePlayer.sendInfoMessage("Lenght of arguments (in define): " + arguments.length);
                basePlayer.sendErrorMessage("Fehlende Argumente für den Befehl §b/BlockBreaker Define§7.");
                basePlayer.sendArrowMessage("Korrekte Verwendung: §8/§bBlockBreaker Define Group §8<§aName§8> §8<§3ResourceAreaType§8> §8<§eBaseXP§8> §8<§eLevel§8>");
                return false;
            }

            if (arguments[1].equalsIgnoreCase("group")) {
                if (arguments.length < 6) {
                    basePlayer.sendInfoMessage("Lenght of arguments (in group): " + arguments.length);
                    basePlayer.sendErrorMessage("Fehlende Argumente für den Befehl §b/BlockBreaker Define§7.");
                    basePlayer.sendArrowMessage("Korrekte Verwendung: §8/§bBlockBreaker Define Group §8<§aName§8> §8<§3ResourceAreaType§8> §8<§eBaseXP§8> §8<§eLevel§8>");
                    return false;
                }

                String resourceGroupName = arguments[2];
                ResourceGroupType resourceGroupType = null;
                int baseXP = 0;
                int level = 0;

                try {
                    resourceGroupType = ResourceGroupType.valueOf(arguments[3].toUpperCase());
                } catch (IllegalArgumentException e) {
                    basePlayer.sendErrorMessage("Bitte gebe einen gültigen Typ für die ResourceGroup an!");
                    basePlayer.sendArrowMessage("Mögliche Typen: §bPIT, §bWOOD, §bSHEARS, §bMINE, §bFARM");
                    return false;
                }

                try {
                    baseXP = Integer.parseInt(arguments[4]);
                } catch (NumberFormatException e) {
                    basePlayer.sendErrorMessage("Das Argument §bBasis-XP §7muss eine Zahl sein!");
                    return false;
                }

                try {
                    level = Integer.parseInt(arguments[5]);
                } catch (NumberFormatException e) {
                    basePlayer.sendErrorMessage("Das Argument §bLevel §7muss eine Zahl sein!");
                    return false;
                }

                if (resourceGroupName == null || resourceGroupName.isBlank()) {
                    basePlayer.sendErrorMessage("Bitte gebe einen gültigen Namen für die ResourceGroup an!");
                    return false;
                }

                if (baseXP == 0) {
                    basePlayer.sendErrorMessage("Bitte gebe eine gültige Basis-XP für die ResourceGroup an!");
                    return false;
                }

                if (level == 0) {
                    basePlayer.sendErrorMessage("Bitte gebe ein gültiges Level für die ResourceGroup an!");
                    return false;
                }

                if (resouceGroupConfig.getConfigModel().getResourceGroupByName(resourceGroupName) != null) {
                    basePlayer.sendErrorMessage("Eine ResourceGroup mit dem Namen §b" + resourceGroupName + " §7existiert bereits!");
                    return false;
                }

                MdlResourceGroupConfig.ResourceGroup definedResourceGroup = new MdlResourceGroupConfig.ResourceGroup(resourceGroupName, resourceGroupType, baseXP, level);

                resouceGroupConfig.getConfigModel().addOrUpdateResourceGroup(definedResourceGroup);
                resouceGroupConfig.saveConfig();

                if (resouceGroupConfig.getConfigModel().getResourceGroupByName(resourceGroupName) != null) {
                    basePlayer.sendInfoMessage("Die ResourceGroup §b" + resourceGroupName + " §7wurde §aerfolgreich §7definiert!");
                    return true;
                }

                return false;
            } else if (arguments[1].equalsIgnoreCase("area")) {
                basePlayer.sendWarnMessage("/blockbreaker define area is WIP");
                return false;
            } else {
                basePlayer.sendErrorMessage("Das Argument §b" + arguments[1] + " §7wurde §cnicht §7gefunden!");
                basePlayer.sendArrowMessage("§8/§bBlockBreaker Help §7für Hilfe.");

            }
        // region Argument "Redefine"
        } else if (arguments[0].equalsIgnoreCase("redefine")) {
            basePlayer.sendWarnMessage("/blockbreaker redefine is WIP");
            return false;
        } else {
            basePlayer.sendErrorMessage("Das Argument §b" + arguments[0] + " §7wurde §cnicht §7gefunden!");
            basePlayer.sendArrowMessage("§8/§bBlockBreaker Help §7für Hilfe.");
        }

        return false;
    }

    @Override
    public String getName() {
        return NAME;
    }
}