package net.dbsgameplay.blockbreaker.commands.controlling.interfaces;

import net.dbsgameplay.blockbreaker.utils.BasePlayer;
import org.bukkit.command.Command;

public interface IBasicCommandExecutor extends IBBCommand {
    /**
     * Funktion, um einen BlockBreaker-Befehl auszuführen
     *
     * @param basePlayer Instanz des BBPlayers
     * @param command    Der Befehl
     * @param arguments  Argumente des Befehls
     */
    Boolean onCommand(BasePlayer basePlayer, Command command, String[] arguments);
}
