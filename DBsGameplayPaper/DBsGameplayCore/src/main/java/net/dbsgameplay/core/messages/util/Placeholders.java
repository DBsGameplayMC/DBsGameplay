package net.dbsgameplay.core.messages.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Placeholders {

    public static Component parseMessage(String rawMessage, String... parameters) {
        MiniMessage miniMessage = MiniMessage.miniMessage();

        String parsedMessage = parsePlaceholders(rawMessage, parameters);
        Component component = miniMessage.deserialize(parsedMessage);

        return component;
    }


    private static String parsePlaceholders(String input, String... parameters) {
        String formattedMessage = input;

        for (int i = 0; i < parameters.length; i++) {
            formattedMessage = formattedMessage.replace("{" + i + "}", parameters[i]);
        }

        return translateColorCodes(formattedMessage);
    }

    private static String translateColorCodes(String message) {
        return message
                .replace("&0", "<black>")
                .replace("&1", "<dark_blue>")
                .replace("&2", "<dark_green>")
                .replace("&3", "<dark_aqua>")
                .replace("&4", "<dark_red>")
                .replace("&5", "<dark_purple>")
                .replace("&6", "<gold>")
                .replace("&7", "<gray>")
                .replace("&8", "<dark_gray>")
                .replace("&9", "<blue>")
                .replace("&a", "<green>")
                .replace("&b", "<aqua>")
                .replace("&c", "<red>")
                .replace("&d", "<light_purple>")
                .replace("&e", "<yellow>")
                .replace("&f", "<white>")
                .replace("&k", "<obfuscated>")
                .replace("&l", "<b>")
                .replace("&m", "<strikethrough>")
                .replace("&n", "<u>")
                .replace("&o", "<i>")
                .replace("&r", "<reset>");
    }
}
