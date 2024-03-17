package net.dbsgameplay.core.database.converter;

import jakarta.persistence.AttributeConverter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class UUIDConverter implements AttributeConverter<UUID, String> {


    /**
     * Konvertiert die gegebene UUID in einen String.
     */
    public String convertToDatabaseColumn(final @NotNull UUID uuid) {
        return uuid.toString();
    }

    /**
     * Konvertiert den gegebenen String in eine UUID.
     */
    public UUID convertToEntityAttribute(final @NotNull String uuid) {
        return UUID.fromString(uuid);
    }
}