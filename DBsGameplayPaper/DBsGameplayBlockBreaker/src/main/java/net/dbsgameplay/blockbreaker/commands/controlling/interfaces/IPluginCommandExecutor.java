package net.dbsgameplay.blockbreaker.commands.controlling.interfaces;

import net.dbsgameplay.blockbreaker.DBsGameplayBlockBreaker;
import net.dbsgameplay.blockbreaker.utils.BasePlayer;
import org.bukkit.command.Command;

public interface IPluginCommandExecutor extends IBBCommand  {
    /**
     * Funktion, um einen BlockBreaker-Befehl auszuführen
     *
     * @param basePlayer Instanz des BBPlayers
     * @param command    Der Befehl
     * @param arguments  Argumente des Befehls
     * @param plugin     Instanz des BlockBreaker-Plugins
     */
    Boolean onCommand(BasePlayer basePlayer, Command command, String[] arguments, DBsGameplayBlockBreaker plugin);
}
