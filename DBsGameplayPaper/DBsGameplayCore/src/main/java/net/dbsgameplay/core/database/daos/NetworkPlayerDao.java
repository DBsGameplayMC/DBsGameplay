package net.dbsgameplay.core.database.daos;

import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import net.dbsgameplay.core.DBsGameplayCore;
import net.dbsgameplay.core.database.entities.NetworkPlayer;
import net.dbsgameplay.core.utils.configmodels.MdlDatabaseConfig;
import org.bukkit.Bukkit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.build.AllowSysOut;
import org.jetbrains.annotations.NotNull;

import javax.swing.text.html.Option;
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
        Optional<CriteriaBuilder> criteriaBuilderOpt = this.getCriteriaBuilder();
        Optional<CriteriaQuery<NetworkPlayer>> criteriaQueryOpt = this.getCriteriaQuery();

        if (criteriaBuilderOpt.isEmpty()) {
            Bukkit.getLogger().warning("CriteriaBuilder is null");
            return Optional.empty();
        }

        if (criteriaQueryOpt.isEmpty()) {
            Bukkit.getLogger().warning("CriteriaQuery is null");
            return Optional.empty();
        }

        Root<NetworkPlayer> root = criteriaQueryOpt.get().from(NetworkPlayer.class);
        criteriaQueryOpt.get().select(root).where(criteriaBuilderOpt.get().equal(root.get("playerUUID"), uuid));

        Session session = this.getOrCreateSession();

        if (session == null) {
            Bukkit.getLogger().warning("SessionFactory is null");
            return Optional.empty();
        }

        NetworkPlayer foundPlayer = session.createQuery(criteriaQueryOpt.get()).uniqueResult();

        return Optional.ofNullable(foundPlayer);
    }

    /**
     * Gibt die Sprache des Spielers anhand der UUID zurück.
     *
     * @param uuid UUID des Spielers, dessen Sprache zurückgegeben werden soll.
     * @return Sprache des Spielers, der anhand der UUID gefunden wurde oder null.
     */
    public Optional<String> getLanguage(final @NotNull String uuid) {
        Optional<CriteriaBuilder> criteriaBuilderOpt = this.getCriteriaBuilder();
        Optional<CriteriaQuery<NetworkPlayer>> criteriaQueryOpt = this.getCriteriaQuery();

        if (criteriaBuilderOpt.isEmpty()) {
            Bukkit.getLogger().warning("CriteriaBuilder is null");
            return Optional.empty();
        }

        if (criteriaQueryOpt.isEmpty()) {
            Bukkit.getLogger().warning("CriteriaQuery is null");
            return Optional.empty();
        }


        Root<NetworkPlayer> root = criteriaQueryOpt.get().from(NetworkPlayer.class);
        CriteriaQuery<NetworkPlayer> query = criteriaQueryOpt.get().select(root).where(criteriaBuilderOpt.get().equal(root.get("playerUUID"), uuid));

        String foundLanguage = this.getOrCreateSession().createQuery(query).uniqueResult().getLanguage();

        return Optional.ofNullable(foundLanguage);
    }

    /**
     * Aktualisiert die Sprache des Spielers anhand der UUID.
     *
     * @param uuid     UUID des Spielers, dessen Sprache aktualisiert werden soll.
     * @param language Sprache, die aktualisiert werden soll.
     */
    public boolean updateLanguage(final @NotNull String uuid, final @NotNull String language) {
        Optional<NetworkPlayer> player = this.getPlayer(uuid);

        if (player.isPresent()) {
            player.get().setLanguage(language);
            return this.persistEntity(player.get());
        }

        return false;
    }

    public Optional<Boolean> isPlayerRegistered(final @NotNull String uuid) {

        Optional<NetworkPlayer> player = this.getPlayer(uuid);

        if (player.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(true);
    }

    public void registerPlayer(final @NotNull NetworkPlayer player) {
        this.persistEntity(player);
    }

    @Override
    protected Class<NetworkPlayer> getClazzType() {
        return NetworkPlayer.class;
    }

    @Override
    protected Optional<CriteriaBuilder> getCriteriaBuilder() {
        Session session = this.getOrCreateSession();

        if (session == null) {
            Bukkit.getLogger().warning("Session is null (DB offline?)");
            return Optional.empty();
        }

        CriteriaBuilder builder = session.getCriteriaBuilder();

         if (builder == null) {
            Bukkit.getLogger().warning("CriteriaBuilder is null");
            return Optional.empty();
         }

        return Optional.of(builder);
    }

    @Override
    protected Optional<CriteriaQuery<NetworkPlayer>> getCriteriaQuery() {
        Optional<CriteriaBuilder> criteriaBuilderOpt = this.getCriteriaBuilder();

        if (criteriaBuilderOpt.isEmpty()) {
            Bukkit.getLogger().warning("CriteriaBuilder is null");
            return Optional.empty();
        }

        return Optional.ofNullable(criteriaBuilderOpt.get().createQuery(NetworkPlayer.class));
    }
}
