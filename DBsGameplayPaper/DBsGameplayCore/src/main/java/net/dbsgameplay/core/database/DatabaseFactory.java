package net.dbsgameplay.core.database;

import com.google.common.reflect.ClassPath;
import net.dbsgameplay.core.configmodels.MdlDatabaseConfig;
import org.bukkit.plugin.java.JavaPlugin;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Verbindet sich mit der Datenbank und stellt eine SessionFactory bereit.
 */
public class DatabaseFactory<T extends JavaPlugin> implements IDatabaseProvider {

    private final Logger logger;
    private final MdlDatabaseConfig databaseConfiguration;
    private Configuration configuration;

    /**
     * Erstellt eine neue Instanz der Klasse der DatabaseFactory.
     */
    public DatabaseFactory(final @NotNull T pluginInstance, final @NotNull MdlDatabaseConfig databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
        this.logger = pluginInstance.getLogger();
        this.connect();
    }


    /**
     * Verbindet sich mit der Datenbank und liesst die Konfiguration ein.
     */
    @Override
    public void connect() {
        Properties properties = new Properties();

        properties.setProperty(Environment.JAKARTA_JDBC_DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.setProperty(Environment.AUTOCOMMIT, "true");
        properties.setProperty(Environment.AUTO_CLOSE_SESSION, "true");
        properties.setProperty(Environment.HBM2DDL_AUTO, "update");

        properties.setProperty(Environment.JAKARTA_JDBC_USER, this.databaseConfiguration.getUsername());
        properties.setProperty(Environment.JAKARTA_JDBC_PASSWORD, this.databaseConfiguration.getPassword());

        properties.setProperty(Environment.JAKARTA_JDBC_URL, this.databaseConfiguration.getConnectionURL());
        properties.setProperty(Environment.SHOW_SQL, String.valueOf(this.databaseConfiguration.getShowSqlInConsole()));

        this.configuration = new Configuration().addProperties(properties);
        this.includeAnnotatedClasses();
    }


    /**
     * Bindet ChildClasses ein, die mit einer Annotation markiert sind.
     */
    @Override
    public void includeAnnotatedClasses() {
        try {
            ClassPath.from(this.getClass().getClassLoader()).getAllClasses().stream().filter(classInfo -> {
                return classInfo.getPackageName().contains(this.databaseConfiguration.getDatabaseEntitiesPackage());
            }).toList().stream().map(ClassPath.ClassInfo::load).forEach(clazz -> {
                try {
                    this.getClass().getClassLoader().loadClass(clazz.getName());
                    this.configuration.addAnnotatedClass(clazz);
                } catch (final Exception exception) {
                    this.logger.log(Level.SEVERE, "An exception occurred while loading the class: " + clazz.getName(), exception);
                }
            });
        } catch (final Exception exception) {
            this.logger.log(Level.SEVERE, "An exception occurred while including annotated classes", exception);
        }
    }

    /**
     * Erstellt eine SessionFactory und gibt sie zurück.
     */
    @Nullable
    @Override
    public SessionFactory buildSessionFactory() {
        return this.configuration == null ? null : this.configuration.buildSessionFactory();
    }
}