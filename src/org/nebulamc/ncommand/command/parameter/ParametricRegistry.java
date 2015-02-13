package org.nebulamc.ncommand.command.parameter;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by Ryan on 2/2/2015
 * <p/>
 * Project: nCommand
 */
public class ParametricRegistry {

    HashMap<Type, ParameterResolver<?>> resolvedTypes = new HashMap<Type, ParameterResolver<?>>();

    public ParametricRegistry() {
        addDefaults();
    }

    public void addResolver(Type t, ParameterResolver<?> resolver) {
        resolvedTypes.put(t, resolver);
    }

    public ParameterResolver<?> get(Type type) {
        if (resolvedTypes.containsKey(type)) {
            return resolvedTypes.get(type);
        }
        return null;
    }

    // Populates the table with default resolvers.
    public void addDefaults() {
        addResolver(Boolean.class, new ParameterResolver<Boolean>() {
            @Override
            public Boolean resolveType(String input) {
                return Boolean.parseBoolean(input);
            }
        });

        addResolver(Integer.class, new ParameterResolver<Integer>() {
            @Override
            public Integer resolveType(String input) {
                return Integer.parseInt(input);
            }
        });

        addResolver(Double.class, new ParameterResolver<Double>() {
            @Override
            public Double resolveType(String input) {
                return Double.parseDouble(input);
            }
        });
    }

    public HashMap<Type, ParameterResolver<?>> getResolvedTypes() {
        return resolvedTypes;
    }
}
