package net.dbsgameplay.core.messages.util.placeholders;

public class Placeholder<PlaceholderType, PlaceholderValue> {

    private PlaceholderValue placeholderValue;

    private Class<PlaceholderType> placeholderType;

    private Placeholder(PlaceholderValue placeholderValue, Class<PlaceholderType> placeholderType) {
        this.placeholderValue = placeholderValue;
        this.placeholderType = placeholderType;
    }
}
