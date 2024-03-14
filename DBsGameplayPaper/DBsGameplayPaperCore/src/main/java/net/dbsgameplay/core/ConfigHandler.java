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
            System.out.println("File exists? -> " + file.exists());
            if (!file.exists()) {
                System.out.println("Create file");
                System.out.println("File path: " + file.getPath());
                if (file.createNewFile()) {
                    T instance = type.newInstance();
                    saveConfig();
                    Bukkit.getLogger().info("Die Konfigurationsdatei \"" + file.getName() + "\" wurde erstellt.");
                }
            }
            System.out.println("Load file: " + file.getName());

            this.configModel = mapper.readValue(file, type);

        } catch (IOException | IllegalAccessException | InstantiationException e) {
            Bukkit.getLogger().severe("Fehler beim Laden der Konfigurationsdatei \"" + file.getName() + "\"." + file.getName() + "\n" + e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Speichert die geladene Konfiguration in der Konfigurationsdatei.
     */
    public void saveConfig()  {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            mapper.writeValue(file, this.configModel);
        } catch (IOException e) {
            Bukkit.getLogger().severe("Fehler beim Speichern der Konfigurationsdatei \"" + file.getName() + "\"." + file.getName());
        }
    }

    /**
     * Gibt das geladene Konfigurationsmodel zurück.
     *
     * @return Das geladene Konfigurationsmodel.
     */
    public T getConfigModel() {
        if (configModel == null) {
            System.out.println("ConfigModel is null");
            loadConfig();
        }

        if (configModel == null) {
            System.out.println("ConfigModel is still null");
        }

        return configModel;
    }
}