package me.rbrickis.mojo.parametric.graph;

import me.rbrickis.mojo.annotations.Command;
import me.rbrickis.mojo.parametric.ParametricRegistry;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CommandGraph {

    private Map<Class<? extends Annotation>, Class<?>> senderMap = new HashMap<>();
    private ParametricRegistry registry = new ParametricRegistry();
    private Class<? extends Annotation> b = null;

    public CommandGraph useSender(Class<?> clazz) {
        senderMap.put(Command.class, clazz);
        return this;
    }

    public CommandGraph unlessMarkedWith(Class<? extends Annotation> clazz) {
        this.b = clazz;
        return this;
    }

    public CommandGraph thenUseSender(Class<?> clazz) {
        if (b == null) {
            throw new IllegalArgumentException("b is null!");
        }
        senderMap.put(b, clazz);
        b = null;
        return this;
    }

    public CommandGraph withParametricRegistry(ParametricRegistry registry) {
        this.registry = registry;
        return this;
    }


    public ParametricRegistry getRegistry() {
        return registry;
    }

    public Class<?> getSender(Class<? extends Annotation> annotation) {
        Class<?> found = null;
        for (Class<? extends Annotation> a : senderMap.keySet()) {
            if (a == annotation) {
                found = senderMap.get(a);
            }
        }
        return found;
    }

    public Class<?> getSenderForMethod(Method method, Class<?> defaultType) {
        Class<?> found = null;
        if (method.isAnnotationPresent(Command.class)) {
            for (Annotation annotation : method.getAnnotations()) {
                if (annotation.annotationType() != Command.class && senderMap
                    .containsKey(annotation.annotationType())) {
                    found = senderMap.get(annotation.annotationType());
                }
            }
        }
        if (found == null) {
            return defaultType;
        }
        return found;
    }

}
