package net.dbsgameplay.core.constants;

import com.google.gson.reflect.TypeToken;
import jakarta.persistence.criteria.CriteriaQuery;
import net.dbsgameplay.core.database.entities.NetworkPlayer;

import java.lang.reflect.Type;

public final class Types {

    public static final Type CQ_NETWORK_PLAYER  = new TypeToken<CriteriaQuery<NetworkPlayer>>() {}.getType();

}
