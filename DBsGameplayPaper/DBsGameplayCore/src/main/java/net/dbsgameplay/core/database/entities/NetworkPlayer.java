package net.dbsgameplay.core.database.entities;

import jakarta.persistence.*;
import net.dbsgameplay.core.database.converter.UUIDConverter;
import net.dbsgameplay.core.players.CorePlayer;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "network_player")
public class NetworkPlayer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Column(name = "player_uuid", unique = true, nullable = false)
    @Convert(converter = UUIDConverter.class)
    public final UUID playerUUID;
    @Column(name = "created_at", nullable = false)
    private final String createdAt = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", new Locale("de", "DE")).format(new Date());
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id", unique = true, nullable = false)
    private Long Id;
    @Column(name = "player_language", nullable = false)
    private String language;

    public NetworkPlayer() {
        this.playerUUID = UUID.randomUUID();
    }

    public NetworkPlayer(@NotNull CorePlayer basePlayer) {
        this.playerUUID = basePlayer.getPlayer().getUniqueId();
        this.language = basePlayer.getPlayer().locale().getLanguage();
    }

    // region Getter und Setter
    public Long getId() {
        return Id;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    // endregion
}
