package net.dbsgameplay.core.messages.util;

import net.dbsgameplay.core.interfaces.IMessageBase;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MessageWrapper<MessageEnum extends Enum<MessageEnum> & IMessageBase> {
    private Map<MessageEnum, String> messages;
    private Class<MessageEnum> enumClass;

    public MessageWrapper(@NotNull Map<MessageEnum, String> messages, Class<MessageEnum> enumClass) {
        this.messages = new HashMap<>(messages);
        this.enumClass = enumClass;
    }

    public String getMessage(MessageEnum key) {
        return messages.get(key);
    }

    public MessageEnum[] getEnumClass() {
        return this.enumClass.getEnumConstants();
    }
}
