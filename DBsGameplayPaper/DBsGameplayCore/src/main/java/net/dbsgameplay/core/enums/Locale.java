package net.dbsgameplay.core.enums;

public enum Locale {

    GERMAN("de"),
    ENGLISH("en");

    private final String locale;

    Locale(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }

    /**
     * Gibt die Sprache anhand des Sprach-Codes zurück.
     */
    public static Locale fromLocaleCode(String code) {
        for (Locale loc : values()) {
            if (loc.locale.equals(code)) {
                return loc;
            }
        }

        return ENGLISH;
    }
}
