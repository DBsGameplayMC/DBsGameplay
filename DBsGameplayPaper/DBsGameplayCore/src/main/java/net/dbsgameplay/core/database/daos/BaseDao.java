package net.dbsgameplay.core.database.daos;


import net.dbsgameplay.core.configmodels.MdlDatabaseConfig;
import net.dbsgameplay.core.database.DatabaseFactory;
import org.bukkit.plugin.java.JavaPlugin;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
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
     * Speichert die gegebene Entität in der Datenbank.
     * @param data
     */
    public void persistEntity(final T data) {
        try (final Session session = this.getOrCreateSession()) {
            session.beginTransaction();
            session.persist(data);
            session.flush();
            session.getTransaction().commit();
        } catch (final Exception exception) {
            this.logger.log(Level.SEVERE, "Failed to persist entity", exception);
        } finally {
            session.close();
        }
    }


    /**
     * Erstellt oder gibt die aktuelle Session zurück, die für die Datenbankverbindung verwendet wird.
     */
    public Session getOrCreateSession() {
        final SessionFactory sessionFactory = this.getSessionFactory();
        if (sessionFactory == null)
            return null;

        if (this.session == null)
            this.session = sessionFactory.withOptions().flushMode(FlushMode.AUTO).openSession();

        return !this.session.isOpen() ? this.getSessionFactory().withOptions().flushMode(FlushMode.AUTO).openSession() : this.session;
    }

    /**
     * Entfernt die gegebene Entität aus der Datenbank.
     */
    public void removeEntity(final T data) {
        try (final Session session = this.getOrCreateSession()) {
            session.beginTransaction();
            session.remove(data);
            session.getTransaction().commit();
        } catch (final Exception exception) {
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }

            this.logger.log(Level.SEVERE, "Failed to remove entity", exception);
        } finally {
            session.close();
        }
    }

    /**
     * Erstellt eine Query, die den gegebenen Query-String verwendet.
     */
    protected Query<T> createNamedQuery(final @NotNull String queryName) {
        return this.getOrCreateSession().createNamedQuery(queryName, this.getClazzType());
    }


    /**
     * Gibt die Klasse des Typs zurück, der vom DAO (Data Access Object) verwaltet wird.
     */
    protected abstract Class<T> getClazzType();


    /**
     * Gibt die SessionFactory zurück, die für die Datenbankverbindung verwendet wird.
     */
    private @Nullable SessionFactory getSessionFactory() {
        final DatabaseFactory<P> databaseFactory = new DatabaseFactory<>(this.pluginInstance, databaseConfig);
        return databaseFactory.buildSessionFactory();
    }
}