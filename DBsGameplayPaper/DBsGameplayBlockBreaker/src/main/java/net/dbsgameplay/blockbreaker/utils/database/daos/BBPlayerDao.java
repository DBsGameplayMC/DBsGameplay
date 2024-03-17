package net.dbsgameplay.blockbreaker.utils.database.daos;

import jakarta.persistence.Table;
import net.dbsgameplay.blockbreaker.DBsGameplayBlockBreaker;
import net.dbsgameplay.blockbreaker.utils.database.entities.BBPlayer;
import net.dbsgameplay.core.configmodels.MdlDatabaseConfig;
import net.dbsgameplay.core.database.daos.BaseDao;
import org.hibernate.query.Query;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@Table(name = "bb_player")
public class BBPlayerDao extends BaseDao<BBPlayer, DBsGameplayBlockBreaker> {

    /**
     * Erstellt eine neue Instanz der Klasse des Player DAOs.
     */
    public BBPlayerDao(@NotNull DBsGameplayBlockBreaker plugin, final @NotNull MdlDatabaseConfig databaseConfiguration) {
        super(plugin, databaseConfiguration);
    }

    /**
     * Gibt den Spieler anhand der UUID zurück.
     *
     * @param uuid UUID des Spielers, der zurückgegeben werden soll.
     * @return Spieler, der anhand der UUID gefunden wurde oder null.
     */
    public Optional<BBPlayer> findByUUID(final @NotNull String uuid) {
        Query<BBPlayer> query = this.getOrCreateSession().createNamedQuery("BBPlayer.findByUUID", BBPlayer.class);
        query.setParameter("uuid", uuid);
        return Optional.ofNullable(query.getSingleResultOrNull());
    }

    /**
     * Gibt die Sprache des Spielers anhand der UUID zurück.
     *
     * @param uuid UUID des Spielers, dessen Sprache zurückgegeben werden soll.
     * @return Sprache des Spielers, der anhand der UUID gefunden wurde oder null.
     */
    public Optional<String> getLanguage(final @NotNull String uuid) {
        Query<String> query = this.getOrCreateSession().createNamedQuery("BBPlayer.getLanguage", String.class);
        query.setParameter("uuid", uuid);
        return Optional.ofNullable(query.getSingleResultOrNull());
    }

    /**
     * Aktualisiert die Sprache des Spielers anhand der UUID.
     *
     * @param uuid     UUID des Spielers, dessen Sprache aktualisiert werden soll.
     * @param language Sprache, die aktualisiert werden soll.
     */
    public void updateLanguage(final @NotNull String uuid, final @NotNull String language) {
        Optional<BBPlayer> player = this.findByUUID(uuid);
        if (player.isPresent()) {
            player.get().setLanguage(language);
            this.persistEntity(player.get());
        }
    }

    /**
     * Gibt den Klassentypen der Entität zurück.
     */
    @Override
    protected Class<BBPlayer> getClazzType() {
        return BBPlayer.class;
    }
}
