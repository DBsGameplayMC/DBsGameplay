package net.dbsgameplay.core.messages.management;

import com.fasterxml.jackson.annotation.JsonCreator;
import net.dbsgameplay.core.messages.MessageKey;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Messages {
    private Map<MessageKey, String> messages;

    @JsonCreator
    public Messages(@NotNull Map<MessageKey, String> messages) {
        this.messages = new HashMap<>(messages);
    }

    public String getMessage(MessageKey key) {
        return messages.get(key);
    }
}
