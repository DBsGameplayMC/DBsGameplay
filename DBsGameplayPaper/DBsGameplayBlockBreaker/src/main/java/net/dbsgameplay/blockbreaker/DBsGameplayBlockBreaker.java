package net.dbsgameplay.blockbreaker;

import net.dbsgameplay.blockbreaker.commands.*;
import net.dbsgameplay.blockbreaker.listener.*;
import net.dbsgameplay.blockbreaker.utils.ResourceGroupsConfig;
import net.dbsgameplay.blockbreaker.utils.constants.BBFilePaths;
import net.dbsgameplay.blockbreaker.utils.models.MdlResourceGroupConfig;
import net.dbsgameplay.core.ConfigHandler;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Optional;

public final class DBsGameplayBlockBreaker extends JavaPlugin {


  private ResourceGroupsConfig resourceGroupsConfig;

  public void onEnable() {
    getCommand("pickaxe").setExecutor((CommandExecutor)new BasicPickeCommand());
    getCommand("sellall").setExecutor((CommandExecutor)new SellCommand());
    getCommand("bupgrade").setExecutor((CommandExecutor)new UpgradeCommand());
    getCommand("bhelp").setExecutor((CommandExecutor)new HelpCommand());
    getCommand("mine").setExecutor((CommandExecutor)new MinesCommand());

    resourceGroupsConfig = new ResourceGroupsConfig(this,"/ResourceGroups.yml");

    ConfigHandler<MdlResourceGroupConfig> testConfig = new ConfigHandler<MdlResourceGroupConfig>(new File(BBFilePaths.RESOURCE_GROUPS_CONFIG), MdlResourceGroupConfig.class);
    testConfig.loadConfig();

    Optional<String> asd = testConfig.getConfigModel().getResourcegroups().keySet().stream().findFirst();

    System.out.println(testConfig.getConfigModel().getResourcegroups().get(asd.get()).getName());
    System.out.println("before: " + testConfig.getConfigModel().getResourcegroups().get(asd.get()).getLevel());
    testConfig.getConfigModel().getResourcegroups().get(asd.get()).setLevel(100);
    System.out.println("after: " + testConfig.getConfigModel().getResourcegroups().get(asd.get()).getLevel());
    testConfig.saveConfig();

    getCommand("blockbreaker").setExecutor((CommandExecutor)new BlockBreakerDefCommand(this, resourceGroupsConfig));
    System.out.println(resourceGroupsConfig.existsResourceGroup("123Test"));

    getServer().getPluginManager().registerEvents((Listener)new VerkaufenListener(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new UpgradeListener(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new PlayerJoinLeaveListener(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new FortuneListener(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new InstantInvListener(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new PickaxeUpgradeListener(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new MineListener(), (Plugin)this);
  }

  public ResourceGroupsConfig getResourceGroupConfig() {
    return this.resourceGroupsConfig;
  }
  // Beispiel: Nur true oder false:
  public boolean existsResourceGroup(String name) {
    return this.resourceGroupsConfig.existsResourceGroup(name);
  }
  public void onDisable() {
  }

  public ResourceGroupsConfig getResourceGroupsConfig() {
    return resourceGroupsConfig;
  }
}
