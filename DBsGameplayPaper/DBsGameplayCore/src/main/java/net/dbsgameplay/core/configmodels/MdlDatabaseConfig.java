package net.dbsgameplay.core.configmodels;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MdlDatabaseConfig {

    private String host;
    private Integer port;
    private String databaseName;
    private String username;
    private String password;
    private Boolean showSqlInConsole;
    private String databaseEntitiesPackage;

    public MdlDatabaseConfig() {}

    public MdlDatabaseConfig(String host, Integer port, String databaseName, String username, String password, Boolean showSqlInConsole, String databaseEntitiesPackage) {
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
        this.showSqlInConsole = showSqlInConsole;
        this.databaseEntitiesPackage = databaseEntitiesPackage;
    }


    public String getHost() {
        return this.host;
    }

    public Integer getPort() {
        return this.port;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Boolean getShowSqlInConsole() {
        return this.showSqlInConsole;
    }

    public String getDatabaseEntitiesPackage() {
        return this.databaseEntitiesPackage;
    }

    @JsonIgnore
    public String getConnectionURL() {
        return "jdbc:mysql://" + this.getHost() + ":" + this.getPort() + "/" + this.getDatabaseName() + "?useUnicode=true";
    }
}
