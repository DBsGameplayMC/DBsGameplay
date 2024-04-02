package net.dbsgameplay.core.players;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerHandler<BasePlayer extends CorePlayer> {

    private final Map<UUID, BasePlayer> players = new HashMap<>();

    // region Add
    /**
     * Fügt einen Spieler anhand des BasePlayer-Objekts in die Spielerliste hinzu.
     */
    public void addPlayer(BasePlayer player) {
        players.put(player.getUniqueId(), player);
    }
    // endregion



    // region Get
    /**
     * Gibt den Spieler anhand der UUID zurück.
     */
    public BasePlayer getPlayer(UUID uuid) {
        return players.get(uuid);
    }

    /**
     * Gibt den Spieler anhand des Spielernamen zurück.
     */
    @Nullable
    public BasePlayer getPlayer(String name) {
        for (BasePlayer player : players.values()) {
            if (player.getPlayer().getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }
    // endregion



    // region Remove
    /**
     * Entfernt einen Spieler aus der Spielerliste anhand des BasePlayer-Objekts.
     */
    public void removePlayer(BasePlayer player) {
        players.remove(player.getUniqueId());
    }

    /**
     * Entfernt einen Spieler aus der Spielerliste anhand der UUID.
     */
    public void removePlayer(UUID uuid) {
        players.remove(uuid);
    }

    /**
     * Entfernt einen Spieler aus der Spielerliste anhand des Spielernamen.
     */
    public void removePlayer(String name) {
        for (BasePlayer player : players.values()) {
            if (player.getPlayer().getName().equalsIgnoreCase(name)) {
                players.remove(player.getUniqueId());
            }
        }
    }
    // endregion
}