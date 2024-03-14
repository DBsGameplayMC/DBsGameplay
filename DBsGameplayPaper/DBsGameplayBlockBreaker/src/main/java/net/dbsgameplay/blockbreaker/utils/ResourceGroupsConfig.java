package net.dbsgameplay.blockbreaker.utils;

import net.dbsgameplay.blockbreaker.utils.models.MdlResourceGroupConfig;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Verwaltet die Konfigurationsdatei für RecourceGroups.
 */
public class ResourceGroupsConfig {
    private static final String RESOURCE_GROUPS_PATH = "resourcegroups";
    private static final String NAME_PATH = "name";
    private static final String RESOURCE_TYPE_PATH = "resourcetype";
    private static final String BASE_XP_PATH = "basexp";
    private static final String LEVEL_PATH = "level";

    private final Plugin plugin;
    private final File configFile;
    private final FileConfiguration config;

    /**
     * Erstellt eine neue Instanz von ResourceGroupsConfig.
     *
     * @param plugin   Die Instanz des Paper-Plugins, zu dem diese Konfiguration gehört.
     * @param fileName Der Name der Konfigurationsdatei.
     */
    public ResourceGroupsConfig(Plugin plugin, String fileName) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), fileName);

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();

            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.config = YamlConfiguration.loadConfiguration(configFile);
    }

    /**
     * Gibt die Konfiguration zurück.
     *
     * @return Die FileConfiguration-Instanz.
     */
    public FileConfiguration getConfig() {
        return config;
    }

    /**
     * Speichert die Konfiguration in der Datei.
     */
    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sucht nach einer ResourceGroup anhand des Namens.
     *
     * @param name Der Name der ResourceGroup.
     * @return Ein Optional, das ein MdlResourceGroupConfig-Objekt enthält, falls gefunden.
     */
    public Optional<MdlResourceGroupConfig> getResourceGroup(String name) {
        System.out.println("ResourceGroupsConfig.getResourceGroup config" + config);

        ConfigurationSection resourceGroupSection = config.getConfigurationSection(RESOURCE_GROUPS_PATH);

        if (resourceGroupSection == null) {
            return Optional.empty();
        }

        //return config.getConfigurationSection(RESOURCE_GROUPS_PATH).getKeys(false).stream()
         //       .filter(resourceGroup -> name.equals(config.getString(RESOURCE_GROUPS_PATH + "." + resourceGroup + "." + NAME_PATH)))
          //      .findFirst()
          //      .map(resourceGroup -> {
          //          String resourceType = config.getString(RESOURCE_GROUPS_PATH + "." + resourceGroup + "." + RESOURCE_TYPE_PATH);
          //          int baseXP = config.getInt(RESOURCE_GROUPS_PATH + "." + resourceGroup + "." + BASE_XP_PATH);
          //          int level = config.getInt(RESOURCE_GROUPS_PATH + "." + resourceGroup + "." + LEVEL_PATH);
          //          return new MdlResourceGroupConfig(resourceGroup, name, resourceType, baseXP, level);
          //      });

        return Optional.empty();
    }

    /**
     * Überprüft, ob eine ResourceGroup mit dem angegebenen Namen existiert.
     *
     * @param name Der Name der ResourceGroup.
     * @return true, wenn die ResourceGroup existiert; sonst false.
     */
    public boolean existsResourceGroup(String name){
        return getResourceGroup(name).isPresent();
    }

    /**
     * Fügt eine neue ResourceGroup zur Konfiguration hinzu.
     *
     * @param groupId      Die ID der ResourceGroup.
     * @param groupName    Der Name der ResourceGroup.
     * @param resourceType Der Typ der Ressourcen in der Gruppe.
     * @param baseXP       Die Basis-XP für die ResourceGroup.
     * @param level        Das Level der ResourceGroup.
     */
    public void addResourceGroup(String groupId, String groupName, String resourceType, int baseXP, int level) {
        ConfigurationSection groupSection = config.createSection(RESOURCE_GROUPS_PATH + "." + groupId);
        groupSection.set(NAME_PATH, groupName);
        groupSection.set(RESOURCE_TYPE_PATH, resourceType);
        groupSection.set(BASE_XP_PATH, baseXP);
        groupSection.set(LEVEL_PATH, level);

        saveConfig();
    }
}
