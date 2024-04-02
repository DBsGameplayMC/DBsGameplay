package net.dbsgameplay.blockbreaker.controllers;

import net.dbsgameplay.blockbreaker.DBsGameplayBlockBreaker;
import net.dbsgameplay.blockbreaker.commands.admin.CmdBlockBreaker;
import net.dbsgameplay.core.interfaces.commands.IDBSGCommand;
import net.dbsgameplay.blockbreaker.utils.BasePlayer;
import net.dbsgameplay.core.constants.ChatPrefixes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandController implements CommandExecutor {

    private final DBsGameplayBlockBreaker plugin;
    private CmdBlockBreaker cmdBlockBreaker;

    public CommandController(DBsGameplayBlockBreaker plugin) {
        this.plugin = plugin;
        this.registerCommands();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            this.plugin.getServer().getConsoleSender().sendMessage(net.dbsgameplay.blockbreaker.utils.constants.ChatPrefixes.PREFIX + "Dieser Befehl ist nur für §6Spieler §7verfügbar!");
            return false;
        }

        Player player = (Player) sender;
        BasePlayer basePlayer = new BasePlayer(player);
        String commandName = command.getName();

        if(commandName.equalsIgnoreCase(this.cmdBlockBreaker.getName())) {
            return this.cmdBlockBreaker.onCommand(basePlayer, command, args, this.plugin);
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
    }

    /**
     * Registriert einen Befehl
     *
     * @param commandToRegister Befehl, der registriert werden soll
     * @param <T>               Typ des Befehls
     */
    private <T extends IDBSGCommand> void registerCommand(T commandToRegister) {
        if (commandToRegister.getName() == null || commandToRegister.getName().isBlank()) {
            this.plugin.getServer().getConsoleSender().sendMessage(ChatPrefixes.ERROR + "Die Befehlsklasse §3" + commandToRegister.getClass().getSimpleName() + " §7hat §ckeinen §7gültigen §cBefehlsnamen §7definiert!");
            return;
        }

        PluginCommand pluginCommand = this.plugin.getCommand(commandToRegister.getName());

        if (pluginCommand == null) {
            this.plugin.getServer().getConsoleSender().sendMessage(ChatPrefixes.ERROR + "Der Befehl \"§3/" + commandToRegister.getName() + "§7\" konnte $cnicht §7registriert werden!");
            return;
        }

        pluginCommand.setExecutor(this);

        this.plugin.getServer().getConsoleSender().sendMessage(net.dbsgameplay.blockbreaker.utils.constants.ChatPrefixes.PREFIX + "Der Befehl \"§3" + commandToRegister.getName() + "§7\" wurde §aerfolgreich §7registriert!");
    }
    //endregion
}
