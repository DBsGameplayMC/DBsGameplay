package net.dbsgameplay.core.messages;

import net.dbsgameplay.core.DBsGameplayCore;
import net.dbsgameplay.core.constants.ChatPrefixes;
import net.dbsgameplay.core.constants.FilePaths;
import net.dbsgameplay.core.interfaces.IMessageBase;
import net.dbsgameplay.core.messages.util.MessageFileLoader;
import net.dbsgameplay.core.messages.util.MessageWrapper;
import net.dbsgameplay.core.messages.util.Placeholders;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class MessageFactory<MessageEnum extends Enum<MessageEnum> & IMessageBase> {
    private final Map<String, MessageWrapper<MessageEnum>> messages = new HashMap<>();

    /**
     * Erstellt eine neue MessageFactory, die Nachrichten aus den Dateien im Ordner FilePaths.MESSAGES_FOLDER lädt.
     */
    public MessageFactory(Class<MessageEnum> enumClass, MessageEnum languageNameKey) {
        File folder = new File(FilePaths.MESSAGES_FOLDER);

        MessageFileLoader.copyDefaultMessages();

        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isFile()) {

                String name = file.getName();
                // Hole [Sprache] aus "messages_[Sprache].yml"
                String fileLanguage = name.substring(name.lastIndexOf("_") + 1, name.lastIndexOf("."));

                try {
                    MessageFileLoader<MessageEnum> messageFileLoader = new MessageFileLoader<>(enumClass);
                    MessageWrapper<MessageEnum> messageWrapper = messageFileLoader.loadMessagesFromFile(file);

                    if (messageWrapper == null) {
                        DBsGameplayCore.getInstance().getServer().getConsoleSender().sendMessage(ChatPrefixes.ERROR + "Die Nachrichten-Datei \"" + file.getName() + "\" für die Sprache \"" + fileLanguage + "\" konnte §cnicht §7geladen werden: Der Schlüssel §b'languagename' §7wurde nicht gefunden.");
                        continue;
                    }

                    messages.put(fileLanguage, messageFileLoader.loadMessagesFromFile(file));

                    PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
                    String languageName = serializer.serialize(getMessage(languageNameKey, fileLanguage));

                    DBsGameplayCore.getInstance().getServer().getConsoleSender().sendMessage(ChatPrefixes.INFO + "Die Nachrichten aus der Datei \"" + file.getName() + "\" für die Sprache " + fileLanguage + " (" + languageName + ") wurden §aerfolgreich §7geladen.");
                } catch (IOException e) {
                    DBsGameplayCore.getInstance().getServer().getConsoleSender().sendMessage(ChatPrefixes.ERROR + "Die Nachrichten aus der Datei \"" + file.getName() + "\" konnten §cnicht §7geladen werden: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Gibt Nachrichten anhand der Sprache aus den Dateien im Ordner FilePaths.MESSAGES_FOLDER zurück.
     */
    public Component getMessage(MessageEnum key, String languageCode, String... placeholderValues) {
        MessageWrapper<MessageEnum> messages = this.messages.get(languageCode);

        if (messages == null) {
            // Fallback auf Deutsch
            messages = this.messages.get("de");
        }

        if (messages == null) {
            return Component.text("&c&l[Found no message for key \"" + key + "\" in language \"" + languageCode + "\", please inform an team member.]");
        }

        String message = messages.getMessage(key);

        return Placeholders.parseMessage(message, placeholderValues);
    }

    /**
     * Gibt den Namen der Sprache zurück, die zu dem Sprachcode passt.
     */
    public String getLanguageFromCode(String languageCode) {
        MessageWrapper<MessageEnum> messages = this.messages.get(languageCode);

        if (messages == null) {
            // Fallback auf Deutsch
            messages = this.messages.get("de");
        }

        if (messages == null) {
            return "§c§l[Found no language name for languageCode §b§l\"" + languageCode + "\"§c§l, please inform an team member.]";
        }

        return messages.getLanguage();
    }
}
