package me.rbrickis.mojo.utils;

import me.rbrickis.mojo.utils.reflection.SafeClass;

/**
 * Created by Ryan on 6/27/2015
 * <p>
 * Project: Mojo
 */
public final class Reflection {

    public static <T> SafeClass<T> getSafeClass(T object) {
        return new SafeClass<T>(object);
    }

}
