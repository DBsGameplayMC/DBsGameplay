package net.dbsgameplay.core.interfaces;

public interface IConfigHandler<T> {
    void saveConfig();
    void saveConfig(T configModel);
    void loadConfig();
}
