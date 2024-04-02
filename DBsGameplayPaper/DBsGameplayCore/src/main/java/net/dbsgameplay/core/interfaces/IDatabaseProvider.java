package net.dbsgameplay.core.interfaces;

import org.hibernate.SessionFactory;
import org.jetbrains.annotations.Nullable;

public interface IDatabaseProvider {

    void connect() throws Exception;

    void includeAnnotatedClasses() throws Exception;

    @Nullable
    SessionFactory buildSessionFactory();
}
