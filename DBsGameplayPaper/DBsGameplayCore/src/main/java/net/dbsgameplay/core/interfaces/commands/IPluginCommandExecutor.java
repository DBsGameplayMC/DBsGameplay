package net.dbsgameplay.core.interfaces.commands;

import net.dbsgameplay.core.players.CorePlayer;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Interface für die Ausführung von Befehlen mit Zugriff auf die Plugin-Instanz
 *
 * @param <BasePlayer> Typ des BasePlayers (extends CorePlayer)
 * @param <MainClass> Typ der Hauptklasse des Plugins (extends JavaPlugin)
 */
public interface IPluginCommandExecutor<BasePlayer extends CorePlayer, MainClass extends JavaPlugin> extends IDBSGCommand {
    /**
     * Funktion, um einen Befehl mit zugriff auf die Plugin-Instanz auszuführen
     *
     * @param basePlayer Instanz des BasePlayers
     * @param command    Der Befehl
     * @param arguments  Argumente des Befehls
     * @param plugin     Instanz des Plugins
     */
    Boolean onCommand(BasePlayer basePlayer, Command command, String[] arguments, MainClass plugin);
}
