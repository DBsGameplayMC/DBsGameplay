package net.dbsgameplay.blockbreaker.commands.controlling.interfaces;

import net.dbsgameplay.blockbreaker.DBsGameplayBlockBreaker;
import net.dbsgameplay.blockbreaker.utils.BasePlayer;
import net.dbsgameplay.core.ConfigHandler;
import org.bukkit.command.Command;

import java.util.ArrayList;

public interface IConfigListCommandExecutor extends IBBCommand  {
    /**
     * Funktion, um einen BlockBreaker-Befehl auszuführen
     *
     * @param basePlayer       Instanz des BBPlayers
     * @param command          Der Befehl
     * @param arguments        Argumente des Befehls
     * @param plugin           Instanz des BlockBreaker-Plugins
     * @param configsToHandle  List von ConfigHandlers, mit denen der Befehl arbeiten soll
     */
    <T> Boolean onCommand(BasePlayer basePlayer, Command command, String[] arguments, DBsGameplayBlockBreaker plugin, ArrayList<ConfigHandler<T>> configsToHandle);
}
