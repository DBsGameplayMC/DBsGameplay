package net.dbsgameplay.core.database.results;

import net.dbsgameplay.core.enums.ResultType;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;

public class DbResult<T> {

    private final T result;
    private final Type type;
    private String message;
    private final ResultType resultType;

    public DbResult(T result, Class<T> clazz, String message, ResultType resultType) {
        this.result = result;
        this.type = clazz;
        this.message = message;
        this.resultType = resultType;
    }

    public DbResult(T result, Type type, String message, ResultType resultType) {
        this.result = result;
        this.type = type;
        this.message = message;
        this.resultType = resultType;
    }

    public @Nullable T getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public Boolean isSuccessful() {
        boolean success = this.resultType == ResultType.SUCCESS || this.resultType == ResultType.FOUND || this.resultType == ResultType.ALREADY_EXISTS;
        boolean isNull = this.result == null;

        return success && !isNull;
    }

}

