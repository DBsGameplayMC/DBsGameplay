package net.dbsgameplay.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public final class Helpers {

    public static <T> T createWithDefaultValues(Class<T> clazz) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                if (!field.getType().isPrimitive()) {
                    field.setAccessible(true);
                    field.set(instance, null);
                }
            }

            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
