package org.nebulamc.ncommand.command.parameter;

import java.lang.reflect.Type;
import java.util.Hashtable;

/**
 * Created by Ryan on 2/2/2015
 * <p/>
 * Project: nCommand
 */
public class ParametricRegistry {

    Hashtable<Type, ParameterResolver<?>> resolvedTypes = new Hashtable<Type, ParameterResolver<?>>();

    public void addResolver(Type t, ParameterResolver<?> resolver) {
        resolvedTypes.put(t, resolver);
    }

    public ParameterResolver<?> get(Type type) {
        if (resolvedTypes.contains(type)) {
            return resolvedTypes.get(type);
        }
        return null;
    }

}
