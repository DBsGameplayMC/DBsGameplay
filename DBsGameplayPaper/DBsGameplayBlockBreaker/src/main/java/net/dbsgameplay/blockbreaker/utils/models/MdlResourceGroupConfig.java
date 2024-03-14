package net.dbsgameplay.blockbreaker.utils.models;

import java.util.Map;

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
     * Setzt die ResourceGroups der Konfiguration.
     */
    public void setResourcegroups(Map<String, ResourceGroup> resourcegroups) {
        this.resourcegroups = resourcegroups;
    }

    /**
     * Enthält die Daten einer ResourceGroup.
     */
    public static class ResourceGroup {
        private String name;
        private String resourcetype;
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
        public ResourceGroup(String name, String resourcetype, int basexp, int level) {
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
        public String getResourcetype() {
            return resourcetype;
        }

        /**
         * Setzt den ResourceType der ResourceGroup.
         */
        public void setResourcetype(String resourcetype) {
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
