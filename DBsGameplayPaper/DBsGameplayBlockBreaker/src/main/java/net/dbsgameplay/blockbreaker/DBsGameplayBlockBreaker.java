package net.dbsgameplay.blockbreaker;

import net.dbsgameplay.blockbreaker.commands.HelpCommand;
import net.dbsgameplay.blockbreaker.commands.MinesCommand;
import net.dbsgameplay.blockbreaker.commands.SellCommand;
import net.dbsgameplay.blockbreaker.commands.UpgradeCommand;
import net.dbsgameplay.blockbreaker.controllers.CommandController;
import net.dbsgameplay.blockbreaker.listener.*;
import net.dbsgameplay.blockbreaker.utils.constants.ChatPrefixes;
import net.dbsgameplay.blockbreaker.utils.constants.FilePaths;
import net.dbsgameplay.core.database.daos.NetworkPlayerDao;
import net.dbsgameplay.core.utils.ConfigHandler;
import net.dbsgameplay.core.utils.configmodels.MdlDatabaseConfig;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public final class DBsGameplayBlockBreaker extends JavaPlugin {

    private final Logger logger = Logger.getLogger(DBsGameplayBlockBreaker.class.getName());
    private CommandController commandController;
    private NetworkPlayerDao networkPlayerDao;

    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage(ChatPrefixes.PREFIX + "Initialisiere DBsGameplay's " + ChatPrefixes.PREFIX_RGB + "§7...");

        this.getServer().getConsoleSender().sendMessage(ChatPrefixes.PREFIX + "Verbinde mit Datenbank...");

        ConfigHandler<MdlDatabaseConfig> databaseConfigConfigHandler = new ConfigHandler<>(new File(FilePaths.DATABASE_CONFIG), MdlDatabaseConfig.class);
        MdlDatabaseConfig databaseConfig = databaseConfigConfigHandler.getConfigModel();

        if (!databaseConfigConfigHandler.getFile().exists()) {
            this.getServer().getConsoleSender().sendMessage(net.dbsgameplay.core.constants.ChatPrefixes.WARN + "Datenbankkonfiguration nicht abgeschlossen!");

            try {
                this.getServer().getConsoleSender().sendMessage(net.dbsgameplay.core.constants.ChatPrefixes.INFO + "Erstelle Konfigurationsdatei...!");
                databaseConfigConfigHandler.getFile().createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String filePath = databaseConfigConfigHandler.getFile().getAbsolutePath();
            databaseConfigConfigHandler.saveConfig();

            this.getServer().getConsoleSender().sendMessage(net.dbsgameplay.core.constants.ChatPrefixes .INFO + "Konfigurationsdatei §cerfolgreich §7erstellt. Bitte fülle die Konfiguration aus und starte den Server neu - Sie befindet sich in §e" + filePath + "§7.");
            return;
        }

        this.getServer().getConsoleSender().sendMessage(ChatPrefixes.PREFIX + "Registriere Befehle...");
        this.commandController = new CommandController(this);

        getCommand("sellall").setExecutor((CommandExecutor) new SellCommand());
        getCommand("bupgrade").setExecutor((CommandExecutor) new UpgradeCommand());
        getCommand("bhelp").setExecutor((CommandExecutor) new HelpCommand());
        getCommand("mine").setExecutor((CommandExecutor) new MinesCommand());


        getServer().getPluginManager().registerEvents((Listener) new VerkaufenListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new UpgradeListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new PlayerJoinLeaveListener(this), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new FortuneListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new InstantInvListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new PickaxeUpgradeListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new MineListener(), (Plugin) this);

        this.getServer().getConsoleSender().sendMessage(ChatPrefixes.PREFIX + "DBsGameplay's " + ChatPrefixes.PREFIX_RGB + "§7wurde erfolgreich initialisiert!");
    }

    public NetworkPlayerDao getNetworkPlayerDao() {
        return this.networkPlayerDao;
    }
}
