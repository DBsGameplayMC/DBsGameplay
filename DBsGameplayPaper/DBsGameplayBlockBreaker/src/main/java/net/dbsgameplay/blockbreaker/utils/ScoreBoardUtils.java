package net.dbsgameplay.blockbreaker.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreBoardUtils {


    public static Scoreboard getBaseScoreboard(Player player){
        Scoreboard s= Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = s.registerNewObjective("main-bl","main-bl", "§aDBsGameplay.net");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore("§7").setScore(14);
        objective.getScore("§a>> Miner Level 1").setScore(13);
        objective.getScore("§1").setScore(12);
        objective.getScore("§b>> Prestique level 1").setScore(11);
        objective.getScore("§2").setScore(10);
        objective.getScore("§c>> Afk Miner Level 1").setScore(9);
        objective.getScore("§3").setScore(8);
        objective.getScore("§f>> Abgebaute Blöcke").setScore(7);
        objective.getScore("§4").setScore(6);
        objective.getScore("§a>> Geld").setScore(5);
        objective.getScore("§5").setScore(4);
        objective.getScore("§e>> Dein aktelles Ebene").setScore(3);
        objective.getScore("§6").setScore(2);
        objective.getScore(">> Mining Token").setScore(1);













        return s;
    }
}
