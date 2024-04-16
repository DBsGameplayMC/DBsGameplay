package net.dbsgameplay.core.database.daos;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import net.dbsgameplay.core.database.results.DbResult;
import net.dbsgameplay.core.database.results.DbReturn;
import net.dbsgameplay.core.enums.ResultType;
import net.dbsgameplay.core.utils.configmodels.MdlDatabaseConfig;
import net.dbsgameplay.core.database.DatabaseFactory;
import org.bukkit.plugin.java.JavaPlugin;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseDao<T, P extends JavaPlugin> {

    private final Logger logger;
    private final P pluginInstance;
    private final MdlDatabaseConfig databaseConfig;

    private Session session;

    /**
     * Erstellt eine neue Instanz der Klasse des Base Data Access Objects.
     */
    public BaseDao(final @NotNull P pluginInstance, final MdlDatabaseConfig databaseConfiguration) {
        this.pluginInstance = pluginInstance;
        this.databaseConfig = (MdlDatabaseConfig) databaseConfiguration;
        this.logger = this.pluginInstance.getLogger();
    }

    /**
     * Erstellt oder gibt die aktuelle Session zurück, die für die Datenbankverbindung verwendet wird.
     */
    public DbResult<Session> getOrCreateSession() {
        final DbResult<SessionFactory> sessionFactoryResult = this.getSessionFactory();

        if (!sessionFactoryResult.isSuccessful()) {
            this.logger.log(Level.SEVERE, "Failed to get session factory (1st)");
            return new DbResult<>(null, Session.class, "Failed to get session factory (1st)", ResultType.ERROR);
        }

        final SessionFactory sessionFactory = sessionFactoryResult.getResult();

        if (sessionFactory == null) {
            this.logger.log(Level.SEVERE, "Failed to get session factory from result");
            return new DbResult<>(null, Session.class, "Failed to get session factory from result", ResultType.ERROR);
        }

        if (this.session == null || !this.session.isOpen())
            this.session = sessionFactory.withOptions().flushMode(FlushMode.AUTO).openSession();


        return new DbResult<>(this.session, Session.class,"Session created", ResultType.SUCCESS);
    }

    /**
     * Speichert die angegebene Entität in der Datenbank.
     */
    public DbReturn persistEntity(final @NotNull T data) {
        final String entityName = data.getClass().getTypeName();
        final Session session = this.getOrCreateSession().getResult();

        try (session) {
            if (session == null) {
                this.logger.log(Level.SEVERE, "Failed to get session");
                return new DbReturn("Failed to get session", ResultType.ERROR);
            }

            session.beginTransaction();
            session.persist(data);
            session.flush();
            session.getTransaction().commit();

        } catch (final Exception exception) {
            final String msg = "Failed to persist entity \"" + entityName + "\"";

            this.logger.log(Level.SEVERE, msg, exception);
            return new DbReturn(msg, ResultType.EXCEPTION);

        }

        final String msg = "Entity \"" + entityName + "\" persisted";

        this.logger.log(Level.INFO, msg);
        return new DbReturn(msg, ResultType.SUCCESS);
    }

    /**
     * Entfernt die gegebene Entität aus der Datenbank.
     */
    public DbReturn removeEntity(final @NotNull T data) {
        final String entityName = data.getClass().getTypeName();

        try {
            session.beginTransaction();
            session.remove(data);
            session.getTransaction().commit();

        } catch (final Exception exception) {
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }

            this.logger.log(Level.SEVERE, "Failed to remove entity", exception);
            return new DbReturn("Failed to remove entity", ResultType.ERROR);

        } finally {
            session.close();
        }

        return new DbReturn("Entity removed", ResultType.SUCCESS);
    }

    /**
     * Gibt die Klasse des Typs zurück, der vom DAO (Data Access Object) verwaltet wird.
     */
    protected abstract Class<T> getClazzType();

    /**
     * Gibt den CriteriaBuilder zurück, der für die Erstellung von Queries verwendet wird.
     */
    protected abstract DbResult<CriteriaBuilder> getCriteriaBuilder();

    /**
     * Gibt die CriteriaQuery zurück, die für die Erstellung von Queries verwendet wird.
     */
    protected abstract DbResult<CriteriaQuery<T>> getCriteriaQuery();

    /**
     * Gibt die SessionFactory zurück, die für die Datenbankverbindung verwendet wird.
     */
    private DbResult<SessionFactory> getSessionFactory() {
        final DatabaseFactory<P> databaseFactory = new DatabaseFactory<>(this.pluginInstance, databaseConfig);
        return databaseFactory.buildSessionFactory();
    }
}