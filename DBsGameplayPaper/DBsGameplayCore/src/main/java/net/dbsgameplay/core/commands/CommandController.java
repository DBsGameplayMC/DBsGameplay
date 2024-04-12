package net.dbsgameplay.core.commands;

import net.dbsgameplay.core.DBsGameplayCore;
import net.dbsgameplay.core.commands.team.Fly;
import net.dbsgameplay.core.constants.ChatPrefixes;
import net.dbsgameplay.core.interfaces.commands.IDBSGCommand;
import net.dbsgameplay.core.players.CorePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandController implements CommandExecutor {

    private final DBsGameplayCore plugin;
    private Fly cmdFly;

    public CommandController(DBsGameplayCore plugin) {
        this.plugin = plugin;
        this.registerCommands();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            this.plugin.getServer().getConsoleSender().sendMessage(ChatPrefixes.NETWORK_PREFIX + "Dieser Befehl ist nur für §6Spieler §7verfügbar!");
            return false;
        }

        CorePlayer corePlayer = plugin.getPlayerHandler().getPlayer(player.getUniqueId());
        String commandName = command.getName();

        if(commandName.equalsIgnoreCase(this.cmdFly.getName())) {
            return this.cmdFly.onCommand(corePlayer, command, args, this.plugin);
        }

        return false;
    }
    //region Private Methoden
    /**
     * Registriert alle Befehle
     */
    private void registerCommands() {
        // /BlockBreaker
        this.cmdFly = new Fly();
        this.registerCommand(this.cmdFly);
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

        this.plugin.getServer().getConsoleSender().sendMessage(ChatPrefixes.INFO + "Der Befehl \"§3" + commandToRegister.getName() + "§7\" wurde §aerfolgreich §7registriert!");
    }
}
