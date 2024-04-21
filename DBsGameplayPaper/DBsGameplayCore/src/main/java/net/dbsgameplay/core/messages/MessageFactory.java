package net.dbsgameplay.core.messages;

import net.dbsgameplay.core.constants.FilePaths;
import net.dbsgameplay.core.interfaces.IMessageBase;
import net.dbsgameplay.core.messages.util.MessageFileLoader;
import net.dbsgameplay.core.messages.util.MessageWrapper;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class MessageFactory<MessageEnum extends Enum<MessageEnum> & IMessageBase> {
    private final Map<String, MessageWrapper<MessageEnum>> messages = new HashMap<>();

    public MessageFactory(Class<MessageEnum> enumClass, MessageEnum lanuguageNameKey) {
        File folder = new File(FilePaths.MESSAGES_FOLDER);

        MessageFileLoader.copyDefaultMessages();

        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isFile()) {
                String name = file.getName();
                String fileLanguage = name.substring(name.lastIndexOf("_") + 1, name.lastIndexOf("."));
                try {
                    MessageFileLoader<MessageEnum> messageFileLoader = new MessageFileLoader<>(enumClass);
                    MessageWrapper<MessageEnum> messageWrapper = messageFileLoader.loadMessages(file);

                    if (messageWrapper == null) {
                        Bukkit.getLogger().severe("Could not load messages file \"" + file.getName() + "\" for language " + fileLanguage + ": no key 'languagename' found in file.");
                        continue;
                    }

                    messages.put(fileLanguage, messageFileLoader.loadMessages(file));

                    String languageName = getMessage(lanuguageNameKey, fileLanguage);
                    Bukkit.getLogger().info("Loaded messages file \"" + file.getName() + "\" for language " + fileLanguage + " (" + languageName + ")");
                } catch (IOException e) {
                    Bukkit.getLogger().severe("Could not load messages file \"" + file.getName() + "\":" + e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public String getMessage(MessageEnum key, String language, String... placeholderValues) {
        MessageWrapper<MessageEnum> messages = this.messages.get(language);

        if (messages == null) {
            messages = this.messages.get("de");
        }

        if (messages == null) {
            return "Please inform an team member: found no message file for " + language;
        }

        return messages.getMessage(key);
    }
}
