package net.dbsgameplay.core.database.daos;

import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import net.dbsgameplay.core.DBsGameplayCore;
import net.dbsgameplay.core.database.entities.NetworkPlayer;
import net.dbsgameplay.core.utils.configmodels.MdlDatabaseConfig;
import org.bukkit.Bukkit;
import org.hibernate.internal.build.AllowSysOut;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@Table(name = "network_player")
public class NetworkPlayerDao extends BaseDao<NetworkPlayer, DBsGameplayCore> {

    /**
     * Erstellt eine neue Instanz der Klasse des Player DAOs.
     */
    public NetworkPlayerDao(@NotNull DBsGameplayCore plugin, final @NotNull MdlDatabaseConfig databaseConfiguration) {
        super(plugin, databaseConfiguration);
    }

    /**
     * Gibt den Spieler anhand der UUID zurück.
     *
     * @param uuid UUID des Spielers, der zurückgegeben werden soll.
     * @return Spieler, der anhand der UUID gefunden wurde oder null.
     */
    public Optional<NetworkPlayer> getPlayer(final @NotNull String uuid) {
        CriteriaBuilder criteriaBuilder = this.getCriteriaBuilder();
        CriteriaQuery<NetworkPlayer> criteriaQuery = this.getCriteriaQuery();
        Root<NetworkPlayer> root = criteriaQuery.from(NetworkPlayer.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("playerUUID"), uuid));

        NetworkPlayer foundPlayer = this.getOrCreateSession().createQuery(criteriaQuery).uniqueResult();

        return Optional.ofNullable(foundPlayer);
    }

    /**
     * Gibt die Sprache des Spielers anhand der UUID zurück.
     *
     * @param uuid UUID des Spielers, dessen Sprache zurückgegeben werden soll.
     * @return Sprache des Spielers, der anhand der UUID gefunden wurde oder null.
     */
    public Optional<String> getLanguage(final @NotNull String uuid) {
        Root<NetworkPlayer> root = getCriteriaQuery().from(NetworkPlayer.class);
        CriteriaQuery<NetworkPlayer> query = getCriteriaQuery().select(root).where(getCriteriaBuilder().equal(root.get("playerUUID"), uuid));

        String foundLanguage = this.getOrCreateSession().createQuery(query).uniqueResult().getLanguage();

        return Optional.ofNullable(foundLanguage);
    }

    /**
     * Aktualisiert die Sprache des Spielers anhand der UUID.
     *
     * @param uuid     UUID des Spielers, dessen Sprache aktualisiert werden soll.
     * @param language Sprache, die aktualisiert werden soll.
     */
    public void updateLanguage(final @NotNull String uuid, final @NotNull String language) {
        Optional<NetworkPlayer> player = this.getPlayer(uuid);

        if (player.isPresent()) {
            player.get().setLanguage(language);
            this.persistEntity(player.get());
        }
    }

    public Boolean isPlayerRegistered(final @NotNull String uuid) {
        return this.getPlayer(uuid).isPresent();
    }

    public void registerPlayer(final @NotNull NetworkPlayer player) {
        this.persistEntity(player);
    }

    @Override
    protected Class<NetworkPlayer> getClazzType() {
        return NetworkPlayer.class;
    }

    @Override
    protected CriteriaBuilder getCriteriaBuilder() {
        return this.getOrCreateSession().getCriteriaBuilder();
    }

    protected CriteriaQuery<NetworkPlayer> getCriteriaQuery() {
        return this.getCriteriaBuilder().createQuery(NetworkPlayer.class);
    }
}
