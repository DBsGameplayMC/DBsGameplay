package net.dbsgameplay.core.messages.util;

import net.dbsgameplay.core.constants.FilePaths;
import net.dbsgameplay.core.interfaces.IMessageBase;
import net.dbsgameplay.core.messages.CoreMessages;
import net.dbsgameplay.core.utils.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Lädt und speichert die Nachrichten aus einer Datei.
 */
public class MessageFileLoader<MessageEnum extends Enum<MessageEnum> & IMessageBase> {

    Class<MessageEnum> enumClass;

    public MessageFileLoader(Class<MessageEnum> enumClass) {
        this.enumClass = enumClass;
    }

    /**
     * Lädt Nachrichten aus einer 'message_[language].yml'-Datei
     */
    public MessageWrapper<MessageEnum> loadMessages(File file) throws IOException {
        Map<MessageEnum, String> messages = new HashMap<>();
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        if (config.get("languagename") == null) {
            Bukkit.getLogger().severe("Could not find key 'languagename' in messages file " + file.getName());
            return null;
        }

        for (MessageEnum key : enumClass.getEnumConstants()) {
            String path = ((CoreMessages) key).getPath();
            if (config.contains(path)) {
                String message = config.getString(path);
                messages.put(key, message);
            } else {
                messages.put(key, "Please inform an team member: found no message for " + key);
            }
        }

        return new MessageWrapper<>(messages, enumClass);
    }


    /**
     * Kopiert die Standardnachrichten in das Zielverzeichnis, wenn sie noch nicht vorhanden sind.
     */
    public static void copyDefaultMessages() {
        File destinationFile = new File(FilePaths.MESSAGES_FOLDER, "messages_de.yml");
        if (!destinationFile.exists()) {

            FileUtils.createParentDirectories(destinationFile);

            try (InputStream inputStream = MessageFileLoader.class.getResourceAsStream("/messages_de.yml"); OutputStream outputStream = new FileOutputStream(destinationFile)) {
                if (inputStream == null) {
                    Bukkit.getLogger().severe("Could not find default messages file in resources (inputStream is null)");
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
