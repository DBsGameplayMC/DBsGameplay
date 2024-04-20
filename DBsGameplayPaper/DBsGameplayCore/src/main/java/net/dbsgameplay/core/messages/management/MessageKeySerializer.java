package net.dbsgameplay.core.messages.management;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import net.dbsgameplay.core.messages.MessageKey;

import java.io.IOException;

public class MessageKeySerializer extends StdSerializer<MessageKey> {
    public MessageKeySerializer() {
        super(MessageKey.class);
    }

    @Override
    public void serialize(MessageKey messageKey, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(messageKey.toString());
    }
}
