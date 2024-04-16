package net.dbsgameplay.core.database.results;

import net.dbsgameplay.core.enums.ResultType;

public class DbReturn {

    private final String message;
    private final ResultType resultType;

    public DbReturn(String message, ResultType resultType) {
        this.message = message;
        this.resultType = resultType;
    }

    public String getMessage() {
        return message;
    }

    public ResultType getResultType() {
        return resultType;
    }
    public Boolean isSuccessful() {
        return this.resultType == ResultType.SUCCESS;
    }

}
