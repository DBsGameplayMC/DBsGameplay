package net.dbsgameplay.blockbreaker;

import net.dbsgameplay.blockbreaker.commands.*;
import net.dbsgameplay.blockbreaker.commands.controlling.CommandController;
import net.dbsgameplay.blockbreaker.listener.*;
import net.dbsgameplay.blockbreaker.utils.constants.ChatPrefixes;
import net.dbsgameplay.blockbreaker.utils.constants.FilePaths;
import net.dbsgameplay.blockbreaker.utils.models.MdlResourceGroupConfig;
import net.dbsgameplay.core.ConfigHandler;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class DBsGameplayBlockBreaker extends JavaPlugin {

    private CommandController commandController;

    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage(ChatPrefixes.PREFIX + "Initialisiere DBsGameplay's " + ChatPrefixes.PREFIX_RGB + "§7...");
        this.getServer().getConsoleSender().sendMessage(ChatPrefixes.PREFIX + "Registriere Befehle...");
        this.commandController = new CommandController(this);

        getCommand("sellall").setExecutor((CommandExecutor) new SellCommand());
        getCommand("bupgrade").setExecutor((CommandExecutor) new UpgradeCommand());
        getCommand("bhelp").setExecutor((CommandExecutor) new HelpCommand());
        getCommand("mine").setExecutor((CommandExecutor) new MinesCommand());


        getServer().getPluginManager().registerEvents((Listener) new VerkaufenListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new UpgradeListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new PlayerJoinLeaveListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new FortuneListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new InstantInvListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new PickaxeUpgradeListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new MineListener(), (Plugin) this);
    }
}
