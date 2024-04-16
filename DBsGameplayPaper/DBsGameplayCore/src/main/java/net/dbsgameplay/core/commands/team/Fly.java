package net.dbsgameplay.core.commands.team;

import net.dbsgameplay.core.DBsGameplayCore;
import net.dbsgameplay.core.constants.Permissions;
import net.dbsgameplay.core.interfaces.commands.IPluginCommandExecutor;
import net.dbsgameplay.core.players.CorePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Fly implements IPluginCommandExecutor<CorePlayer, DBsGameplayCore> {

    private static final String NAME = "fly";


    /**
     * Führt den /fly Befehl aus.
     * <p>
     * Syntax:
     * - /fly - Toggled den Flugmodus
     * - /fly <on|off> - Schaltet den Flugmodus ein oder aus
     * - /fly <player> - Schaltet den Flugmodus für den angegebenen Spieler ein oder aus
     * - /fly <player> <on|off> - Schaltet den Flugmodus für den angegebenen Spieler ein oder aus
     * - /fly speed <speed|reset> - Setzt die Fluggeschwindigkei (zurück)
     * - /fly speed <player> <speed> - Setzt die Fluggeschwindigkeit für den angegebenen Spieler
     */
    @Override
    public Boolean onCommand(CorePlayer corePlayer, Command command, String[] arguments, DBsGameplayCore plugin) {

        if (!corePlayer.hasPermission(Permissions.FLY)) return false;

        if (arguments.length == 0) {

            if (corePlayer.getPlayer().isFlying()) {
                corePlayer.getPlayer().setAllowFlight(false);
                corePlayer.getPlayer().setFlying(false);
                corePlayer.sendInfoMessage("§7Du hast den Flugmodus §cdeaktiviert§7.");
            } else {
                corePlayer.getPlayer().setAllowFlight(true);
                corePlayer.getPlayer().setFlying(true);
                corePlayer.sendInfoMessage("§7Du hast den Flugmodus §aaktiviert§7.");
            }
            return true;
        }


        if (arguments.length == 1) {
            if (arguments[0].equalsIgnoreCase("on")) {
                corePlayer.getPlayer().setAllowFlight(true);
                corePlayer.getPlayer().setFlying(true);
                corePlayer.sendInfoMessage("§7Du hast den Flugmodus §aaktiviert§7.");
                return true;


            } else if (arguments[0].equalsIgnoreCase("off")) {
                corePlayer.getPlayer().setAllowFlight(false);
                corePlayer.getPlayer().setFlying(false);
                corePlayer.sendInfoMessage("§7Du hast den Flugmodus §cdeaktiviert§7.");
                return true;


            } else if (arguments[0].equalsIgnoreCase("speed")) {
                corePlayer.sendErrorMessage("Fehlende Argumente für den Befehl §8/§bfly speed§7.");
                corePlayer.sendArrowMessage("Korrekte Verwendung: §8/§bFly Speed §8<§aSpeed§8>");
                return false;


            } else {
                CorePlayer targetPlayer = plugin.getPlayerHandler().getPlayer(arguments[0]);

                if (targetPlayer == null) {
                    corePlayer.sendErrorMessage("§7Der Spieler §c" + arguments[0] + "§7 ist §cnicht §7online.");
                    return false;
                }


                if (targetPlayer.getPlayer().isFlying()) {
                    corePlayer.getPlayer().setAllowFlight(false);
                    targetPlayer.getPlayer().setFlying(false);
                    corePlayer.sendInfoMessage("§7Du hast den Flugmodus für §6" + targetPlayer.getPlayer().getName() + " §cdeaktiviert§7.");


                } else {
                    corePlayer.getPlayer().setAllowFlight(true);
                    targetPlayer.getPlayer().setFlying(true);
                    corePlayer.sendInfoMessage("§7Du hast den Flugmodus für §6" + targetPlayer.getPlayer().getName() + " §aaktiviert§7.");


                }
                return true;
            }
        }


        if (arguments.length == 2) {
            if (arguments[0].equalsIgnoreCase("speed")) {
                try {

                    if (arguments[1].equalsIgnoreCase("reset")) {
                        corePlayer.getPlayer().setFlySpeed(0.1f);
                        corePlayer.sendInfoMessage("§7Du hast die Fluggeschwindigkeit auf §bStandardgeschwindigkeit §7zurückgesetzt.");
                        return true;
                    }

                    int speed;
                    try {
                        speed = Integer.parseInt(arguments[1]);
                    } catch (NumberFormatException e) {
                        corePlayer.sendErrorMessage("§7Die Fluggeschwindigkeit muss eine §bZahl §7sein.");
                        return false;
                    }

                    if (speed < 1 || speed > 10) {
                        corePlayer.sendErrorMessage("§7Die Fluggeschwindigkeit muss zwischen §b1.0 §7und §b10.0 §7liegen.");
                        return false;
                    }

                    corePlayer.getPlayer().setFlySpeed(((float)speed/10));
                    corePlayer.sendInfoMessage("§7Du hast die Fluggeschwindigkeit auf §6" + speed + " §7gesetzt.");
                    return true;
                } catch (NumberFormatException exception) {
                    corePlayer.sendErrorMessage("§7Die Fluggeschwindigkeit muss eine §6Zahl §7sein.");
                    return false;
                }


            } else {
                if (arguments[1].equalsIgnoreCase("on")) {
                    CorePlayer targetPlayer = plugin.getPlayerHandler().getPlayer(arguments[0]);

                    if (targetPlayer == null) {
                        corePlayer.sendErrorMessage("§7Der Spieler §c" + arguments[0] + "§7 ist §cnicht §7online.");
                        return false;
                    }

                    if (targetPlayer.getPlayer().isFlying()) {
                        corePlayer.getPlayer().setAllowFlight(false);
                        targetPlayer.getPlayer().setFlying(false);
                        corePlayer.sendInfoMessage("§7Du hast den Flugmodus für §6" + targetPlayer.getPlayer().getName() + " §cdeaktiviert§7.");
                    } else {
                        corePlayer.getPlayer().setAllowFlight(true);
                        targetPlayer.getPlayer().setFlying(true);
                        corePlayer.sendInfoMessage("§7Du hast den Flugmodus für §6" + targetPlayer.getPlayer().getName() + " §aaktiviert§7.");
                    }
                    return true;


                } else if (arguments[1].equalsIgnoreCase("off")) {
                    CorePlayer targetPlayer = plugin.getPlayerHandler().getPlayer(arguments[0]);

                    if (targetPlayer == null) {
                        corePlayer.sendErrorMessage("§7Der Spieler §c" + arguments[0] + "§7 ist §cnicht §7online.");
                        return false;
                    }

                    if (targetPlayer.getPlayer().isFlying()) {
                        corePlayer.getPlayer().setAllowFlight(false);
                        targetPlayer.getPlayer().setFlying(false);
                        corePlayer.sendInfoMessage("§7Du hast den Flugmodus für §6" + targetPlayer.getPlayer().getName() + " §cdeaktiviert§7.");
                    } else {
                        corePlayer.getPlayer().setAllowFlight(true);
                        targetPlayer.getPlayer().setFlying(true);
                        corePlayer.sendInfoMessage("§7Du hast den Flugmodus für §6" + targetPlayer.getPlayer().getName() + " §aaktiviert§7.");
                    }
                    return true;


                } else {
                    corePlayer.sendErrorMessage("Fehlende Argumente für den Befehl §8/§bfly§7.");
                    corePlayer.sendArrowMessage("Korrekte Verwendung: §8/§bfly §8[§6Spieler§8] §8<§aon§8|§coff§8>");
                    return false;
                }
            }
        }


        return false;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
