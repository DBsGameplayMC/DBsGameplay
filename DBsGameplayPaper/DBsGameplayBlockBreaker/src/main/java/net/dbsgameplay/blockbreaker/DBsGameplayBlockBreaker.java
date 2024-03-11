package net.dbsgameplay.blockbreaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.dbsgameplay.blockbreaker.commands.*;
import net.dbsgameplay.blockbreaker.listener.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class DBsGameplayBlockBreaker extends JavaPlugin {
  public void onEnable() {
    getCommand("pickaxe").setExecutor((CommandExecutor)new BasicPickeCommand());
    getCommand("sellall").setExecutor((CommandExecutor)new SellCommand());
    getCommand("bupgrade").setExecutor((CommandExecutor)new UpgradeCommand());
    getCommand("bhelp").setExecutor((CommandExecutor)new HelpCommand());
    getCommand("mine").setExecutor((CommandExecutor)new MinesCommand());
    getCommand("blockbreaker").setExecutor((CommandExecutor)new BlockBreakerDefCommand());


    getServer().getPluginManager().registerEvents((Listener)new VerkaufenListener(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new UpgradeListener(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new PlayerJoinLeaveListener(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new FortuneListener(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new InstantInvListener(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new PickaxeUpgradeListener(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new MineListener(), (Plugin)this);
  }
  
  public void onDisable() {}
}
