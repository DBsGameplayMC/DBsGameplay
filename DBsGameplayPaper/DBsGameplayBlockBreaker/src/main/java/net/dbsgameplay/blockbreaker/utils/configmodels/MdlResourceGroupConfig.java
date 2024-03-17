package net.dbsgameplay.blockbreaker.utils.configmodels;

import net.dbsgameplay.blockbreaker.utils.enums.ResourceGroupType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Enthält die Daten der ResourceGroups des BlockBreakers.
 */
public class MdlResourceGroupConfig {
    private Map<String, ResourceGroup> resourceGroups;

    /**
     * Gibt die ResourceGroups der Konfiguration zurück.
     */
    public Map<String, ResourceGroup> getResourceGroups() {
        return resourceGroups;
    }

    /**
     * Gibt eine ResourceGroup anhand ihres Namens zurück.
     *
     * @param name Name der ResourceGroup, die zurückgegeben werden soll.
     */
    public ResourceGroup getResourceGroupByName(String name) {

        Optional<String> resourceGroupId = this.resourceGroups.keySet().stream().filter(key -> this.resourceGroups.get(key).getName().equals(name)).findFirst();

        return resourceGroupId.map(s -> this.resourceGroups.get(s)).orElse(null);
    }

    /**
     * Fügt eine ResourceGroup hinzu oder aktualisiert eine bestehende ResourceGroup.
     */
    public void addOrUpdateResourceGroup(@NotNull ResourceGroup resourceGroup) {

        String idToSave = getIdByName(resourceGroup.getName());

        if (this.resourceGroups.containsKey(idToSave)) {
            this.resourceGroups.replace(idToSave, resourceGroup);
        } else {
            this.resourceGroups.put(UUID.randomUUID().toString(), resourceGroup);
        }
    }

    /**
     * Gibt die ID einer ResourceGroup anhand ihres Namens zurück.
     */
    public String getIdByName(String name){
        Optional<String> resourceGroupId = this.resourceGroups.keySet().stream().filter(key -> this.resourceGroups.get(key).getName().equals(name)).findFirst();
        return resourceGroupId.orElse(null);
    }

    /**
     * Enthält die Konfiguration einer ResourceGroup.
     */
    public static class ResourceGroup {
        private String name;
        private ResourceGroupType resourceType;
        private int baseXp;
        private int level;

        /**
         * Erstellt eine neue Instanz von ResourceGroup.
         */
        public ResourceGroup() {}

        /**
         * Erstellt eine neue Instanz von ResourceGroup.
         *
         * @param name         Der Name der ResourceGroup.
         * @param resourceType Der ResourceType der ResourceGroup.
         * @param baseXp       Die Basis-XP der ResourceGroup.
         * @param level        Das Level der ResourceGroup gegenüber anderen ResourceGroups.
         */
        public ResourceGroup(String name, ResourceGroupType resourceType, int baseXp, int level) {
            this.name = name;
            this.resourceType = resourceType;
            this.baseXp = baseXp;
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ResourceGroupType getResourceType() {
            return resourceType;
        }

        public void setResourceType(ResourceGroupType resourcetype) {
            this.resourceType = resourcetype;
        }

        public int getBaseXp() {
            return baseXp;
        }

        public void setBaseXp(int baseXp) {
            this.baseXp = this.baseXp;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }
}
