package net.dbsgameplay.core.messages.util.placeholders;

import net.dbsgameplay.core.enums.TagType;

import java.util.ArrayList;

public class ConfigTag {
    private TagType tagType;

    private ArrayList<String> parameters;

    private String innerValue;

    public ConfigTag(TagType tagType, ArrayList<String> parameters, String innerValue) {
        this.tagType = tagType;
        this.parameters = parameters;
        this.innerValue = innerValue;
    }

    public TagType getTagType() {
        return tagType;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public String getInnerValue() {
        return innerValue;
    }
}
