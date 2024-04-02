package net.dbsgameplay.core.interfaces;

public interface IConfigHandler<ConfigModel> {
    void saveConfig();
    void saveConfig(ConfigModel configModel);
    void loadConfig();
}
