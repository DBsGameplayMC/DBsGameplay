package net.dbsgameplay.blockbreaker.utils;

import net.dbsgameplay.blockbreaker.utils.models.mdlresourcegroup;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;


public class ResourceGroupsConfig {
    private Plugin plugin;
    private File configFile;
    private FileConfiguration config;

    public ResourceGroupsConfig(Plugin plugin, String fileName) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), fileName);

        // Prüfe, ob Plugin-Ordner existiert
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        // Prüfe, ob Config-Datei existiert
        if (!configFile.exists()) {
            // Erstelle Config-Ordner
            configFile.getParentFile().mkdirs();

            try {
            // Erstelle Config-Datei
            configFile.createNewFile();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        // Lade YamlConfiguration
        this.config = YamlConfiguration.loadConfiguration(configFile);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public mdlresourcegroup getresourcegroup (String name) {
        AtomicReference<mdlresourcegroup> mdltoreturn = null;
        config.getConfigurationSection("resourcegroups").getKeys(false).forEach(resourcegroup->{
            String configname = config.getString("resourcegroups." +  resourcegroup + ".name");
            if (configname == name){

                String resourcetyp = config.getString("resourcegroups." +  resourcegroup + ".resourcetype");
                Integer basexp = config.getInt("resourcegroups." +  resourcegroup + ".basexp");
                Integer level = config.getInt("resourcegroups." +  resourcegroup + ".level");
                mdltoreturn.set(new mdlresourcegroup(resourcegroup, configname, resourcetyp, basexp, level));
            }
        });
        return mdltoreturn.get();
    }
    public boolean returntrueorfalse(String name){
        if (getresourcegroup(name) == null){
            return false;
        }else {
            return true;
        }
    }

    public void addResourceGroup(String groupId, String groupName, String resourceType, int baseXP, int level) {
        ConfigurationSection groupSection = config.createSection("resourcegroups." + groupId);
        groupSection.set("name", groupName);
        groupSection.set("resourcetype", resourceType);
        groupSection.set("basexp", baseXP);
        groupSection.set("level", level);

        saveConfig();
    }
}
