package net.dbsgameplay.core.messages.util;

import net.dbsgameplay.core.DBsGameplayCore;
import net.dbsgameplay.core.constants.ChatPrefixes;
import net.dbsgameplay.core.constants.FilePaths;
import net.dbsgameplay.core.interfaces.IMessageBase;
import net.dbsgameplay.core.utils.FileUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MessageFileLoader<MessageEnum extends Enum<MessageEnum> & IMessageBase> {

    Class<MessageEnum> enumClass;

    public MessageFileLoader(Class<MessageEnum> enumClass) {
        this.enumClass = enumClass;
    }

    /**
     * Lädt Nachrichten aus einer Datei.
     * <p>
     * Lädt Nachrichten aus einer 'message_[language].yml'-Datei
     */
    /**
     * Lädt Nachrichten aus einer 'message_[language].yml'-Datei
     */
    public MessageWrapper<MessageEnum> loadMessagesFromFile(File file) throws IOException {
        Map<MessageEnum, String> messages = new HashMap<>();
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        String language = "";

        if (config.get("languagename") == null) {
            return null;
        }

        language = config.getString("languagename");

        if (language == null || language.isEmpty()) {
            DBsGameplayCore.getInstance().getServer().getConsoleSender().sendMessage(ChatPrefixes.ERROR + "The message file \"" + file.getName() + "\" could not be loaded: The key 'languagename' was empty.");
            return new MessageWrapper<>(messages, "unknown");
        }


        for (MessageEnum messageKey : enumClass.getEnumConstants()) {
            String path = messageKey.getYamlConfigurationPath();

            if (config.contains(path)) {
                String message = config.getString(path);
                messages.put(messageKey, message);

            } else {
                messages.put(messageKey, "§c§l[Found no message for §b§l\"" + messageKey + "\" (YAML Path: " + messageKey.getYamlConfigurationPath() + ")§c§l, please inform an team member.]");
            }
        }

        return new MessageWrapper<>(messages, language);
    }

    /**
     * Kopiert die Standardnachrichten in das Zielverzeichnis, wenn sie noch nicht vorhanden sind.
     */
    public static void copyDefaultMessages() {
        File destinationFile = new File(FilePaths.MESSAGES_FOLDER, "messages_de.yml");

        if (!destinationFile.exists()) {
            FileUtils.createParentDirectories(destinationFile);

            InputStream inputStream = MessageFileLoader.class.getResourceAsStream("/messages_de.yml");

            if (inputStream == null) {
                DBsGameplayCore.getInstance().getServer().getConsoleSender().sendMessage(ChatPrefixes.ERROR + "Could not find default messages file in resources (inputStream is null)");
                return;
            }

            try (OutputStream outputStream = new FileOutputStream(destinationFile)) {

                byte[] buffer = new byte[1024];
                int length;

                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }

            } catch (IOException e) {
                DBsGameplayCore.getInstance().getServer().getConsoleSender().sendMessage(ChatPrefixes.ERROR + "Could not copy default messages file: " + e.getMessage());
            }
        }
    }
}
