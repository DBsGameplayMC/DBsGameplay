package net.dbsgameplay.blockbreaker.commands.controlling;

import net.dbsgameplay.blockbreaker.DBsGameplayBlockBreaker;
import net.dbsgameplay.blockbreaker.commands.admin.CmdBlockBreaker;
import net.dbsgameplay.blockbreaker.commands.admin.CmdTest;
import net.dbsgameplay.blockbreaker.commands.controlling.interfaces.IBBCommand;
import net.dbsgameplay.blockbreaker.utils.BasePlayer;
import net.dbsgameplay.blockbreaker.utils.constants.ChatPrefixes;
import net.dbsgameplay.blockbreaker.utils.constants.FilePaths;
import net.dbsgameplay.blockbreaker.utils.models.MdlResourceGroupConfig;
import net.dbsgameplay.core.ConfigHandler;
import net.dbsgameplay.core.constants.PCChatPrefixes;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class CommandController implements CommandExecutor {

    private final DBsGameplayBlockBreaker plugin;

    private CmdBlockBreaker cmdBlockBreaker;
    private CmdTest cmdTest;

    public CommandController(DBsGameplayBlockBreaker plugin) {
        this.plugin = plugin;
        this.registerCommands();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            this.plugin.getServer().getConsoleSender().sendMessage(ChatPrefixes.PREFIX + "Dieser Befehl ist nur für §6Spieler §7verfügbar!");
            return false;
        }

        Player player = (Player) sender;
        BasePlayer basePlayer = new BasePlayer(player);
        String commandName = command.getName();

        if(commandName.equalsIgnoreCase(this.cmdBlockBreaker.getName())) {
            ConfigHandler<MdlResourceGroupConfig> resourceGroupConfig = new ConfigHandler<>(new File(FilePaths.RESOURCE_GROUPS_CONFIG), MdlResourceGroupConfig.class);
            return this.cmdBlockBreaker.onCommand(basePlayer, command, args, this.plugin, resourceGroupConfig);
        }

        return false;
    }

    //region Private Methoden
    /**
     * Registriert alle Befehle
     */
    private void registerCommands() {
        // /BlockBreaker
        this.cmdBlockBreaker = new CmdBlockBreaker();
        this.registerCommand(this.cmdBlockBreaker);

        this.cmdTest = new CmdTest();
        this.registerCommand(this.cmdTest);
    }

    /**
     * Registriert einen Befehl
     *
     * @param commandToRegister Befehl, der registriert werden soll
     * @param <T>               Typ des Befehls
     */
    private <T extends IBBCommand> void registerCommand(T commandToRegister) {
        if (commandToRegister.getName() == null || commandToRegister.getName().isBlank()) {
            this.plugin.getServer().getConsoleSender().sendMessage(PCChatPrefixes.ERROR + "Die Befehlsklasse §3" + commandToRegister.getClass().getSimpleName() + " §7hat §ckeinen §7gültigen §cBefehlsnamen §7definiert!");
            return;
        }

        PluginCommand pluginCommand = this.plugin.getCommand(commandToRegister.getName());

        if (pluginCommand == null) {
            this.plugin.getServer().getConsoleSender().sendMessage(PCChatPrefixes.ERROR + "Der Befehl \"§3/" + commandToRegister.getName() + "§7\" konnte $cnicht §7registriert werden!");
            return;
        }

        pluginCommand.setExecutor(this);

        this.plugin.getServer().getConsoleSender().sendMessage(ChatPrefixes.PREFIX + "Der Befehl \"§3" + commandToRegister.getName() + "§7\" wurde §aerfolgreich §7registriert!");
    }
    //endregion
}
