package net.dbsgameplay.core.messages.util;

import net.dbsgameplay.core.interfaces.IMessageBase;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MessageWrapper<MessageEnum extends Enum<MessageEnum> & IMessageBase> {
    private Map<MessageEnum, String> messages;
    private String language;

    public MessageWrapper(@NotNull Map<MessageEnum, String> messages, String language) {
        this.messages = new HashMap<>(messages);
        this.language = language;
    }

    /**
     * Gibt die Nachricht für den angegebenen Schlüssel zurück.
     */
    public String getMessage(MessageEnum key) {
        return messages.get(key);
    }

    /**
     * Gibt die Sprache zurück, in der die Nachrichten geladen wurden.
     */
    public String getLanguage() {
        return this.language;
    }
}
