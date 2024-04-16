package net.dbsgameplay.core.database.daos;

import com.google.gson.reflect.TypeToken;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import net.dbsgameplay.core.DBsGameplayCore;
import net.dbsgameplay.core.constants.Types;
import net.dbsgameplay.core.database.entities.NetworkPlayer;
import net.dbsgameplay.core.database.results.DbResult;
import net.dbsgameplay.core.database.results.DbReturn;
import net.dbsgameplay.core.enums.ResultType;
import net.dbsgameplay.core.utils.configmodels.MdlDatabaseConfig;
import org.bukkit.Bukkit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.build.AllowSysOut;
import org.jetbrains.annotations.NotNull;

import javax.swing.text.html.Option;
import java.lang.reflect.Type;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("OptionalOfNullableMisuse")
@Table(name = "network_player")
public class NetworkPlayerDao extends BaseDao<NetworkPlayer, DBsGameplayCore> {

    private final Logger logger;


    /**
     * Erstellt eine neue Instanz der Klasse des Player DAOs.
     */
    public NetworkPlayerDao(@NotNull DBsGameplayCore plugin, final @NotNull MdlDatabaseConfig databaseConfiguration) {
        super(plugin, databaseConfiguration);
        this.logger = plugin.getLogger();

    }

    /**
     * Gibt den Spieler anhand der UUID zurück.
     */
    public DbResult<NetworkPlayer> getPlayer(final @NotNull String uuid) {
        DbResult<Session> sessionResult = this.getOrCreateSession();

        DbResult<CriteriaBuilder> criteriaBuilderResult = this.getCriteriaBuilder();
        DbResult<CriteriaQuery<NetworkPlayer>> criteriaQueryResult = this.getCriteriaQuery();

        if (!sessionResult.isSuccessful()) {
            String msg = "sessionResult ResultType is " + sessionResult.getResultType().toString();

            Bukkit.getLogger().warning(msg);
            return new DbResult<>(null, NetworkPlayer.class, msg, ResultType.ERROR);
        }

        if (!criteriaBuilderResult.isSuccessful()) {
            String msg = "criteriaBuilderResult ResultType is " + sessionResult.getResultType().toString();
            Bukkit.getLogger().warning(msg);
            return new DbResult<>(null, NetworkPlayer.class, msg, ResultType.ERROR);
        }

        if (!criteriaQueryResult.isSuccessful()) {
            String msg = "criteriaQueryResult ResultType is " + sessionResult.getResultType().toString();
            Bukkit.getLogger().warning(msg);
            return new DbResult<>(null, NetworkPlayer.class, msg, ResultType.ERROR);
        }

        Session session = sessionResult.getResult();
        CriteriaBuilder criteriaBuilder = criteriaBuilderResult.getResult();
        CriteriaQuery<NetworkPlayer> criteriaQuery = criteriaQueryResult.getResult();

        if (session == null) {
            Bukkit.getLogger().warning("Session is null");
            return new DbResult<>(null, NetworkPlayer.class, "Session is null", ResultType.ERROR);
        }

        if (criteriaBuilder == null) {
            Bukkit.getLogger().warning("CriteriaBuilder is null");
            return new DbResult<>(null, NetworkPlayer.class, "CriteriaBuilder is null", ResultType.ERROR);
        }

        if (criteriaQuery == null) {
            Bukkit.getLogger().warning("CriteriaQuery is null");
            return new DbResult<>(null, NetworkPlayer.class, "CriteriaQuery is null", ResultType.ERROR);
        }

        Root<NetworkPlayer> root = criteriaQuery.from(NetworkPlayer.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("playerUUID"), uuid));

        NetworkPlayer foundPlayer = session.createQuery(criteriaQuery).uniqueResult();
        if (foundPlayer == null) {
            System.out.println("foundPlayer is null");
            return new DbResult<>(null, NetworkPlayer.class, "Player not found", ResultType.NOT_FOUND);
        }

        System.out.println("foundPlayer: " + foundPlayer.getPlayerUUID());
        return new DbResult<>(foundPlayer, NetworkPlayer.class, "Player found", ResultType.FOUND);
    }

    /**
     * Gibt die Sprache des Spielers anhand der UUID zurück.
     *
     * @param uuid UUID des Spielers, dessen Sprache zurückgegeben werden soll.
     * @return Sprache des Spielers, der anhand der UUID gefunden wurde oder null.
     */
    public DbResult<String> getLanguage(final @NotNull String uuid) {
        DbResult<Session> sessionResult = this.getOrCreateSession();
        DbResult<CriteriaBuilder> criteriaBuilderResult = this.getCriteriaBuilder();
        DbResult<CriteriaQuery<NetworkPlayer>> criteriaQueryResult = this.getCriteriaQuery();

        if (!sessionResult.isSuccessful()) {
            Bukkit.getLogger().warning("Session wasn't successfully created: " + sessionResult.getMessage());
            return new DbResult<>(null, String.class, "Session wasn't successfully created", ResultType.ERROR);
        }

        if (!criteriaBuilderResult.isSuccessful()) {
            Bukkit.getLogger().warning("CriteriaBuilder wasn't successfully created: " + criteriaBuilderResult.getMessage());
            return new DbResult<>(null, String.class, "CriteriaBuilder wasn't successfully created", ResultType.ERROR);
        }

        if (criteriaQueryResult.isSuccessful()) {
            Bukkit.getLogger().warning("CriteriaQuery wasn't successfully created: " + criteriaQueryResult.getMessage());
            return new DbResult<>(null, String.class, "CriteriaQuery wasn't successfully created", ResultType.ERROR);
        }

        Session session = sessionResult.getResult();
        CriteriaBuilder criteriaBuilder = criteriaBuilderResult.getResult();
        CriteriaQuery<NetworkPlayer> criteriaQuery = criteriaQueryResult.getResult();

        if (session == null) {
            Bukkit.getLogger().warning("Session is null");
            return new DbResult<>(null, String.class, "Session is null", ResultType.ERROR);
        }

        if (criteriaBuilder == null) {
            Bukkit.getLogger().warning("CriteriaBuilder is null");
            return new DbResult<>(null, String.class, "CriteriaBuilder is null", ResultType.ERROR);
        }

        if (criteriaQuery == null) {
            Bukkit.getLogger().warning("CriteriaQuery is null");
            return new DbResult<>(null, String.class, "CriteriaQuery is null", ResultType.ERROR);
        }

        Root<NetworkPlayer> root = criteriaQuery.from(NetworkPlayer.class);
        CriteriaQuery<NetworkPlayer> query = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("playerUUID"), uuid));

        String foundLanguage = session.createQuery(query).uniqueResult().getLanguage();

        if (foundLanguage == null) {
            return new DbResult<>(null, String.class, "Language not found", ResultType.NOT_FOUND);
        }

        return new DbResult<>(foundLanguage, String.class, "Language found", ResultType.FOUND);
    }

    /**
     * Aktualisiert die Sprache des Spielers anhand der UUID.
     *
     * @param uuid     UUID des Spielers, dessen Sprache aktualisiert werden soll.
     * @param language Sprache, die aktualisiert werden soll.
     */
    public DbReturn updateLanguage(final @NotNull String uuid, final @NotNull String language) {
        DbResult<NetworkPlayer> playerResult = this.getPlayer(uuid);

        if (!playerResult.isSuccessful()) {
            return new DbReturn(playerResult.getMessage(), ResultType.ERROR);
        }

        NetworkPlayer player = playerResult.getResult();

        if (player == null) {
            return new DbReturn("Player is null", ResultType.ERROR);
        }

        player.setLanguage(language);

        return this.persistEntity(player);
    }

    public DbReturn isPlayerRegistered(final @NotNull String uuid) {
        DbResult<NetworkPlayer> player = this.getPlayer(uuid);

        if (!player.isSuccessful()) {
            return new DbReturn(player.getMessage(), player.getResultType());
        }

        if (player.getResult() == null) {
            return new DbReturn("Player not found", ResultType.NOT_FOUND);
        }

        return new DbReturn("Player found", ResultType.FOUND);
    }

    public DbReturn registerPlayer(final @NotNull NetworkPlayer player) {
        return this.persistEntity(player);
    }

    @Override
    protected Class<NetworkPlayer> getClazzType() {
        return NetworkPlayer.class;
    }

    @Override
    protected DbResult<CriteriaBuilder> getCriteriaBuilder() {
        final DbResult<Session> sessionResult = this.getOrCreateSession();

        if (!sessionResult.isSuccessful()) {
            this.logger.log(Level.SEVERE, "Failed to get session");
            return new DbResult<>(null, CriteriaBuilder.class, "Failed to get session", ResultType.ERROR);
        }

        Session session = sessionResult.getResult();

        if (session == null) {
            this.logger.log(Level.SEVERE, "Session is null");
            return new DbResult<>(null, CriteriaBuilder.class, "Session is null", ResultType.ERROR);
        }

        CriteriaBuilder builder = session.getCriteriaBuilder();

         if (builder == null) {
            Bukkit.getLogger().warning("CriteriaBuilder is null");
            return new DbResult<>(null, CriteriaBuilder.class, "CriteriaBuilder is null", ResultType.ERROR);
         }

        return new DbResult<>(builder, CriteriaBuilder.class, "CriteriaBuilder created", ResultType.SUCCESS);
    }

    @Override
    protected DbResult<CriteriaQuery<NetworkPlayer>> getCriteriaQuery() {
        DbResult<CriteriaBuilder> criteriaBuilderResult = this.getCriteriaBuilder();

        if (!criteriaBuilderResult.isSuccessful()) {
            Bukkit.getLogger().warning("CriteriaBuilder is null");
            return new DbResult<>(null, Types.CQ_NETWORK_PLAYER, "CriteriaBuilder is null", ResultType.ERROR);
        }

        CriteriaBuilder criteriaBuilder = criteriaBuilderResult.getResult();

        if (criteriaBuilder == null) {
            Bukkit.getLogger().warning("CriteriaBuilder is null");
            return new DbResult<>(null, Types.CQ_NETWORK_PLAYER, "CriteriaBuilder is null", ResultType.ERROR);
        }

        CriteriaQuery<NetworkPlayer> criteriaQuery = criteriaBuilder.createQuery(NetworkPlayer.class);

        if (criteriaQuery == null) {
            Bukkit.getLogger().warning("CriteriaQuery is null");
            return new DbResult<>(null, Types.CQ_NETWORK_PLAYER, "CriteriaQuery is null", ResultType.ERROR);
        }


        return new DbResult<>(criteriaQuery, Types.CQ_NETWORK_PLAYER, "CriteriaQuery created", ResultType.SUCCESS);
    }
}
