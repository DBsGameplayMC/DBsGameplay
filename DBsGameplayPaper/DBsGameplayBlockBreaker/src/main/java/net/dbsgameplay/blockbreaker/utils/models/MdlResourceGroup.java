package net.dbsgameplay.blockbreaker.utils.models;

public class MdlResourceGroup {

    String groupId; String groupName; String resourceType; int baseXP; int level;

    public MdlResourceGroup(String groupId, String groupName, String resourceType, int baseXP, int level) {

        this.groupId = groupId;
        this.groupName = groupName;
        this.resourceType = resourceType;
        this.baseXP = baseXP;
        this.level = level;
        
        
    }
}
