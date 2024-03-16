package net.dbsgameplay.blockbreaker.commands.controlling.interfaces;

import net.dbsgameplay.blockbreaker.DBsGameplayBlockBreaker;
import net.dbsgameplay.blockbreaker.utils.BasePlayer;
import net.dbsgameplay.core.ConfigHandler;
import org.bukkit.command.Command;

public interface IConfigCommandExecutor<T> extends IBBCommand  {
    /**
     * Funktion, um einen BlockBreaker-Befehl auszuführen
     *
     * @param basePlayer     Instanz des BBPlayers
     * @param command        Der Befehl
     * @param arguments      Argumente des Befehls
     * @param plugin         Instanz des BlockBreaker-Plugins
     * @param configToHandle Instanz des ConfigHandlers
     */
    Boolean onCommand(BasePlayer basePlayer, Command command, String[] arguments, DBsGameplayBlockBreaker plugin, ConfigHandler<T> configToHandle);
}
