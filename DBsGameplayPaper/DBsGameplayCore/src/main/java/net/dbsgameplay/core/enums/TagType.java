package net.dbsgameplay.core.enums;

public enum TagType {

    /**
     * TagType, der es erlaubt, Texte zu markieren, die beim Hovern einen Text anzeigen.
     */
    HOVER_TEXT("hoverText"),

    /**
     * TagType, der es erlaubt, Texte zu markieren, die beim Klicken eine Aktion ausführen und beim Hovern einen Text anzeigen.
     */
    CLICK_ACTION_AND_HOVER_TEXT("clickActionAndHoverText"),

    /**
     * TagType, der es erlaubt, Texte zu markieren, die beim Klicken eine Aktion ausführen.
     */
    CLICK_ACTION("clickAction"),

    /**
     * Unbekannter TagType. obviously.
     */
    UNKNOWN("unknown");

    private final String tagName;

    TagType(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }
}
