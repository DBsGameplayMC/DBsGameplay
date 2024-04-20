package net.dbsgameplay.core.messages.management;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.dbsgameplay.core.messages.MessageKey;

import java.io.IOException;

public class MessageKeyDeserializer extends StdDeserializer<MessageKey> {
    public MessageKeyDeserializer() {
        super(MessageKey.class);
    }

    @Override
    public MessageKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        return MessageKey.valueOf(node.asText());
    }
}

