package net.dbsgameplay.core;

import net.dbsgameplay.core.commands.CommandController;
import net.dbsgameplay.core.constants.FilePaths;
import net.dbsgameplay.core.constants.ChatPrefixes;
import net.dbsgameplay.core.database.daos.NetworkPlayerDao;
import net.dbsgameplay.core.listeners.CorePlayerJoinEvent;
import net.dbsgameplay.core.players.CorePlayer;
import net.dbsgameplay.core.players.PlayerHandler;
import net.dbsgameplay.core.utils.ConfigHandler;
import net.dbsgameplay.core.utils.configmodels.MdlDatabaseConfig;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class DBsGameplayCore extends JavaPlugin {

    private static DBsGameplayCore instance;
    private PlayerHandler<CorePlayer> playerHandler;
    private NetworkPlayerDao networkPlayerDao;

    @Override
    public void onEnable() {
        playerHandler = new PlayerHandler<>();
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

        this.networkPlayerDao = new NetworkPlayerDao(this, databaseConfig);
        instance = this;


        CommandController commandController = new CommandController(this);
        this.getServer().getPluginManager().registerEvents(new CorePlayerJoinEvent(this),this);



        this.getServer().getConsoleSender().sendMessage(ChatPrefixes.NETWORK_PREFIX + "DBsGameplay's §5Core §7wurde erfolgreich initialisiert!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Gibt die Instanz des DBsGameplayCore-Plugins zurück.
     */
    public static DBsGameplayCore getInstance() {
        return instance;
    }

    /**
     * Gibt die Instanz des PlayerHandlers zurück.
     */
    public PlayerHandler<CorePlayer> getPlayerHandler() {
        return playerHandler;
    }

    /**
     * Gibt die Instanz des NetworkPlayers zurück.
     */
    public NetworkPlayerDao getNetworkPlayerDao() {
        return networkPlayerDao;
    }
}
