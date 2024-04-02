package net.dbsgameplay.blockbreaker.utils;

import net.dbsgameplay.core.players.CorePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BasePlayer extends CorePlayer {

    public BasePlayer(Player player) {
        super(player);
    }

    public BasePlayer(UUID playerUUID) {
        super(playerUUID);
    }


}
