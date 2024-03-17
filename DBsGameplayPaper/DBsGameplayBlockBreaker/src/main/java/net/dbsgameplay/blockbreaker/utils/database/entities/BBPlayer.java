package net.dbsgameplay.blockbreaker.utils.database.entities;

import jakarta.persistence.*;
import net.dbsgameplay.blockbreaker.utils.BasePlayer;
import net.dbsgameplay.core.database.converter.UUIDConverter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.io.Serial;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Entity
@NamedQuery(name= "BBPlayer.findAll", query = "SELECT p FROM BBPlayer p")
@NamedQuery(name= "BBPlayer.findByUUID", query = "SELECT p FROM BBPlayer p WHERE p.playerUUID = :uuid")
@NamedQuery(name = "BBPlayer.getLanguage", query = "SELECT p.language FROM BBPlayer p WHERE p.playerUUID = :uuid")
@Table(name = "bb_player")
public class BBPlayer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id", unique = true, nullable = false)
    private Long Id;

    @Column(name = "player_uuid", unique = true, nullable = false)
    @Convert(converter = UUIDConverter.class)
    public final UUID playerUUID;

    @Column(name = "player_language", nullable = false)
    private String language;

    @Column(name = "created_at", nullable = false )
    private final String createdAt = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", new Locale("de", "DE")).format(new Date());

    public BBPlayer(@NotNull BasePlayer basePlayer) {
        this.playerUUID = basePlayer.getPlayer().getUniqueId();
        this.language = basePlayer.getPlayer().locale().getDisplayLanguage();
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

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
    // endregion
}
