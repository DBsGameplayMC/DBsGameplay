package net.dbsgameplay.core.interfaces.commands;

import net.dbsgameplay.core.players.CorePlayer;
import org.bukkit.command.Command;

public interface IBasicCommandExecutor<BasePlayer extends CorePlayer> extends IDBSGCommand {
    /**
     * Funktion, um einen BlockBreaker-Befehl auszuführen
     *
     * @param basePlayer Instanz der BasePlayer-Klasse
     * @param command    Der Befehl
     * @param arguments  Argumente des Befehls
     */
    Boolean onCommand(BasePlayer basePlayer, Command command, String[] arguments);
}
