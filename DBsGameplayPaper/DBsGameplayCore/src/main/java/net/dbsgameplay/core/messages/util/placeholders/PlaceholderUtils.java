package net.dbsgameplay.core.messages.util.placeholders;

import net.dbsgameplay.core.enums.TagType;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceholderUtils {

    public static ConfigTag parseTag(String input) {
        TagType tagType = TagType.UNKNOWN;
        ArrayList<String> parameters = new ArrayList<>();
        String innerValue = "";

        // Hole TagType
        Pattern tagTypePattern = Pattern.compile("<(\\w+)");
        Matcher tagTypeMatcher = tagTypePattern.matcher(input);
        if (tagTypeMatcher.find()) {
            String foundTag = tagTypeMatcher.group(1).toLowerCase();

            for (TagType type : TagType.values()) {
                if (type.getTagName().toLowerCase().equals(foundTag)) {
                    tagType = type;
                    break;
                }
            }
        }

        // Hole Parameter
        Pattern paramsPattern = Pattern.compile("(\\w+)=\"(.*?)\"");
        Matcher paramsMatcher = paramsPattern.matcher(input);
        while (paramsMatcher.find()) {
            parameters.add(paramsMatcher.group(1) + " = " + paramsMatcher.group(2));
        }

        // Hole InnerValue
        Pattern innerValuePattern = Pattern.compile(">\\s*(.*?)\\s*<\\/");
        Matcher innerValueMatcher = innerValuePattern.matcher(input);
        if (innerValueMatcher.find()) {
            innerValue = innerValueMatcher.group(1);
        }

        System.out.println("TagType: " + tagType);
        System.out.println("Parameters: " + parameters);
        System.out.println("InnerValue: " + innerValue);

        return new ConfigTag(tagType, parameters, innerValue);


    }
}
