package net.dbsgameplay.blockbreaker.utils.models;

import net.dbsgameplay.blockbreaker.utils.enums.ResourceGroupType;
import org.jetbrains.annotations.NotNull;

import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Enthält die Daten der ResourceGroups des BlockBreakers.
 */
public class MdlResourceGroupConfig {
    private Map<String, ResourceGroup> resourcegroups;

    /**
     * Gibt die ResourceGroups der Konfiguration zurück.
     */
    public Map<String, ResourceGroup> getResourcegroups() {
        return resourcegroups;
    }

    /**
     * Gibt eine ResourceGroup anhand ihres Namens zurück.
     *
     * @param name Name der ResourceGroup, die zurückgegeben werden soll.
     */
    public ResourceGroup getResourceGroupByName(String name) {

        Optional<String> resourceGroupId = this.resourcegroups.keySet().stream().filter(key -> this.resourcegroups.get(key).getName().equals(name)).findFirst();

        return resourceGroupId.map(s -> this.resourcegroups.get(s)).orElse(null);
    }

    /**
     * Fügt eine ResourceGroup hinzu oder aktualisiert eine bestehende ResourceGroup.
     */
    public void addOrUpdateResourceGroup(@NotNull ResourceGroup resourceGroup) {

        String idToSave = getIdByName(resourceGroup.getName());

        if (this.resourcegroups.containsKey(idToSave)) {
            this.resourcegroups.replace(idToSave, resourceGroup);
        } else {
            this.resourcegroups.put(UUID.randomUUID().toString(), resourceGroup);
        }
    }

    /**
     * Gibt die ID einer ResourceGroup anhand ihres Namens zurück.
     */
    public String getIdByName(String name){
        Optional<String> resourceGroupId = this.resourcegroups.keySet().stream().filter(key -> this.resourcegroups.get(key).getName().equals(name)).findFirst();
        return resourceGroupId.orElse(null);
    }

    /**
     * Enthält die Daten einer ResourceGroup.
     */
    public static class ResourceGroup {
        private String name;
        private ResourceGroupType resourcetype;
        private int basexp;
        private int level;

        /**
         * Erstellt eine neue Instanz von ResourceGroup.
         */
        public ResourceGroup() {}

        /**
         * Erstellt eine neue Instanz von ResourceGroup.
         *
         * @param name         Der Name der ResourceGroup.
         * @param resourcetype Der ResourceType der ResourceGroup.
         * @param basexp       Die Basis-XP der ResourceGroup.
         * @param level        Das Level der ResourceGroup gegenüber anderen ResourceGroups.
         */
        public ResourceGroup(String name, ResourceGroupType resourcetype, int basexp, int level) {
            this.name = name;
            this.resourcetype = resourcetype;
            this.basexp = basexp;
            this.level = level;
        }

        /**
         * Gibt den Namen der ResourceGroup zurück.
         */
        public String getName() {
            return name;
        }

        /**
         * Setzt den Namen der ResourceGroup.
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Gibt den ResourceType der ResourceGroup zurück.
         */
        public ResourceGroupType getResourcetype() {
            return resourcetype;
        }

        /**
         * Setzt den ResourceType der ResourceGroup.
         */
        public void setResourcetype(ResourceGroupType resourcetype) {
            this.resourcetype = resourcetype;
        }

        /**
         * Gibt die Basis-XP der ResourceGroup zurück.
         */
        public int getBasexp() {
            return basexp;
        }

        /**
         * Setzt die Basis-XP der ResourceGroup.
         */
        public void setBasexp(int basexp) {
            this.basexp = basexp;
        }

        /**
         * Gibt das Level der ResourceGroup zurück.
         */
        public int getLevel() {
            return level;
        }

        /**
         * Setzt das Level der ResourceGroup.
         */
        public void setLevel(int level) {
            this.level = level;
        }
    }
}
