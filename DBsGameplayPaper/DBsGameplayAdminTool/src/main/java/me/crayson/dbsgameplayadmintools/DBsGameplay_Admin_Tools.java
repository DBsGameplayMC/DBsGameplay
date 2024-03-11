package me.crayson.dbsgameplayadmintools;

import me.crayson.dbsgameplayadmintools.commands.*;
import me.crayson.dbsgameplayadmintools.listener.ManagesupListener;
import me.crayson.dbsgameplayadmintools.listener.MenuListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DBsGameplay_Admin_Tools extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("DBsGameplay_Admin_Tools erfolgreich aktiviert");
        getCommand("freeze").setExecutor(new FreezeCommand());
        getCommand("unfreeze").setExecutor(new UnfreezCommand());
        getCommand("manage").setExecutor(new ManageCommand());
        getCommand("managesup").setExecutor(new ManagesupCommand());
        getCommand("gamemodesup").setExecutor(new GamemodesupCommand());

        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new ManagesupListener(), this);
    }

    @Override
    public void onDisable() {

        return null;
    }
}
