package net.dbsgameplay.core.messages.management;

import net.dbsgameplay.core.constants.FilePaths;
import net.dbsgameplay.core.enums.Locale;
import net.dbsgameplay.core.messages.MessageKey;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MessageFactory {
    private final Map<Locale, Messages> messages = new HashMap<>();

    public MessageFactory() {
        File folder = new File(FilePaths.MESSAGES_FOLDER);

        MessagesLoader.copyDefaultMessages();

        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isFile()) {
                String name = file.getName();
                String locale = name.substring(name.lastIndexOf("_") + 1, name.lastIndexOf("."));
                Locale loc = Locale.fromLocaleCode(locale);
                try {
                    messages.put(loc, MessagesLoader.loadMessages(file));
                    Bukkit.getLogger().info("Loaded messages file \"" + file.getName() + "\"");
                } catch (IOException e) {
                    Bukkit.getLogger().severe("Could not load messages file \"" + file.getName() + "\":" + e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public String getMessage(MessageKey key, String language) {
        Messages messages = this.messages.get(Locale.fromLocaleCode(language));

        if (messages == null) {
            messages = this.messages.get(Locale.GERMAN);
        }

        return messages.getMessage(key);
    }
}
