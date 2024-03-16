package net.dbsgameplay.blockbreaker.commands.admin;

import net.dbsgameplay.blockbreaker.commands.controlling.interfaces.IBasicCommandExecutor;
import net.dbsgameplay.blockbreaker.utils.BasePlayer;
import org.bukkit.command.Command;

public class CmdTest implements IBasicCommandExecutor {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public Boolean onCommand(BasePlayer basePlayer, Command command, String[] arguments) {
        return null;
    }
}
