package net.dbsgameplay.core.messages.management;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import net.dbsgameplay.core.constants.FilePaths;
import net.dbsgameplay.core.messages.MessageKey;
import net.dbsgameplay.core.utils.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Lädt und speichert die Nachrichten aus einer Datei.
 */
public class MessagesLoader {

    /**
     * Lädt die Nachrichten aus einer Datei.
     *
     * @param file Die Datei, aus der die Nachrichten geladen werden sollen.
     * @return Die geladenen Nachrichten.
     * @throws IOException Wenn ein Fehler beim Laden der Nachrichten auftritt.
     */
    public static Messages loadMessages(File file) throws IOException {
        Map<MessageKey, String> messages = new HashMap<>();
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        for (MessageKey key : MessageKey.values()) {
            String path = key.getPath();
            if (config.contains(path)) {
                String message = config.getString(path);
                messages.put(key, message);
            } else {
                // Wenn der Schlüssel nicht in der YAML-Datei vorhanden ist, füge eine Standardnachricht ein
                messages.put(key, "Default message for " + key);
            }
        }

        return new Messages(messages);
    }


    /**
     * Kopiert die Standardnachrichten in das Zielverzeichnis, wenn sie noch nicht vorhanden sind.
     */
    public static void copyDefaultMessages() {
        File destinationFile = new File(FilePaths.MESSAGES_FOLDER, "messages_de.yml");
        if (!destinationFile.exists()) {

            FileUtils.createParentDirectories(destinationFile);

            try (InputStream inputStream = MessagesLoader.class.getResourceAsStream("/messages_de.yml"); OutputStream outputStream = new FileOutputStream(destinationFile)) {
                if (inputStream == null) {
                    Bukkit.getLogger().severe("Could not find default messages file (inputStream is null)");
                    return;
                }

                byte[] buffer = new byte[1024];
                int length;

                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }

            } catch (IOException e) {
                Bukkit.getLogger().severe("Could not copy default messages file: " + e.getMessage());
            }
        }
    }
}
