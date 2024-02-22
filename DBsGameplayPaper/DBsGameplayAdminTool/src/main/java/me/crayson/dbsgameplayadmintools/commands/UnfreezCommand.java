package me.crayson.dbsgameplayadmintools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class UnfreezCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }
        Player player = (Player) sender;
        if (args.length != 1){
            player.sendMessage(ChatColor.RED + "Verwendung: /unfreeze <Player_name>");
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);


        Collection<PotionEffect> potionEffects = target.getActivePotionEffects();
        boolean hasSlow = false;
        boolean hasWeakness = false;
        boolean hasMiningFatigue = false;
        boolean hasDamageResistance = false;

        for (PotionEffect effect : potionEffects) {
            PotionEffectType type = effect.getType();
            if (type.equals(PotionEffectType.SLOW)) {
                hasSlow = true;
            } else if (type.equals(PotionEffectType.WEAKNESS)) {
                hasWeakness = true;
            } else if (type.equals(PotionEffectType.SLOW_DIGGING)) {
                hasMiningFatigue = true;
            } else if (type.equals(PotionEffectType.DAMAGE_RESISTANCE)) {
                hasDamageResistance = true;
            }
        }


        if(hasSlow && hasWeakness && hasMiningFatigue && hasDamageResistance && (!(Bukkit.getPlayer(args[0]) == null))) {

        target.removePotionEffect(PotionEffectType.SLOW);
        target.removePotionEffect(PotionEffectType.WEAKNESS);
        target.removePotionEffect(PotionEffectType.SLOW_DIGGING);
        target.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
        player.sendMessage(ChatColor.GREEN + target.getName() + " wurde unfreezt");
        return true;
        }else
            sender.sendMessage(ChatColor.RED + "Spieler nicht online || Spieler nicht gefreezt");
    {


        return true;
}}}
