package net.dbsgameplay.blockbreaker.utils;

import net.dbsgameplay.core.database.daos.NetworkPlayerDao;
import net.dbsgameplay.core.players.CorePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BasePlayer extends CorePlayer {

    public BasePlayer(Player player, NetworkPlayerDao networkPlayerDao) {
        super(player, networkPlayerDao);
    }

    public BasePlayer(UUID playerUUID, NetworkPlayerDao networkPlayerDao) {
        super(playerUUID, networkPlayerDao);
    }
}
