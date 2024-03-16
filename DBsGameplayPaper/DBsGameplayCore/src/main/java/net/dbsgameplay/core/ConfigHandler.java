package net.dbsgameplay.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import net.dbsgameplay.core.interfaces.IConfigHandler;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Ein Handler, der das Laden und Speichern von Konfigurationsdateien ermöglicht.
 *
 * @param <T> Der Typ des Konfigurationsmodels.
 */
public class ConfigHandler<T> implements IConfigHandler<T> {
    private final Class<T> type;
    private final File file;
    private T configModel;

    /**
     * Erstellt einen neuen ConfigHandler für die angegebene Konfigurationsdatei.
     *
     * @param file Die Konfigurationsdatei.
     * @param type Der Typ des Konfigurationsmodels.
     */
    public ConfigHandler(File file, Class<T> type) {
        this.file = file;
        this.type = type;
    }

    /**
     * Lädt die Konfiguration aus der Konfigurationsdatei und gibt das geladene Konfigurationsmodel zurück.
     *
     * @return Das geladene Konfigurationsmodel.
     */
    public void loadConfig() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    T instance = type.newInstance();
                    saveConfig();
                    Bukkit.getLogger().info("Die Konfigurationsdatei \"" + file.getName() + "\" wurde erstellt.");
                }
            }

            this.configModel = mapper.readValue(file, type);

        } catch (IOException | IllegalAccessException | InstantiationException e) {
            Bukkit.getLogger().severe("Fehler beim Laden der Konfigurationsdatei \"" + file.getName() + "\"." + file.getName() + "\n" + e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Speichert die geladene Konfiguration in der Konfigurationsdatei.
     */
    public void saveConfig()  {
        if (this.configModel == null)
            return;

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            mapper.writeValue(file, this.configModel);
        } catch (IOException e) {
            Bukkit.getLogger().severe("Fehler beim Speichern der Konfigurationsdatei \"" + file.getName() + "\"." + file.getName());
        }

        this.configModel = null;
        this.loadConfig();
    }

    /**
     * Speichert das angegebene Konfigurationsmodel in der Konfigurationsdatei.
     *
     * @param configModel Das Konfigurationsmodel, das gespeichert werden soll.
     */
    public void saveConfig(T configModel)  {
        if (this.configModel == null)
            return;

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            mapper.writeValue(file, configModel);
        } catch (IOException e) {
            Bukkit.getLogger().severe("Fehler beim Speichern der Konfigurationsdatei \"" + file.getName() + "\"." + file.getName());
        }
    }

    /**
     * Gibt das geladene Konfigurationsmodel zurück.
     * Falls das Konfigurationsmodel noch nicht geladen wurde, wird es geladen.
     *
     * @return Das geladene Konfigurationsmodel.
     */
    public T getConfigModel() {
        if (configModel == null) {
            loadConfig();
        }

        return configModel;
    }
}