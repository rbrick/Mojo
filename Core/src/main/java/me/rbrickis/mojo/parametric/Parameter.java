package me.rbrickis.mojo.parametric;

import me.rbrickis.mojo.annotations.As;
import me.rbrickis.mojo.annotations.Default;
import me.rbrickis.mojo.annotations.Text;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class Parameter {

    private Class<?> type;

    private ParametricRegistry registry;

    private List<Annotation> annotations;

    private int index;

    private String name;

    public Parameter(Class<?> type, Annotation[] annotations, ParametricRegistry registry,
        int index) {
        this.type = type;
        this.annotations = Arrays.asList(annotations);
        this.registry = registry;
        this.index = index;

        if (isAnnotationPresent(As.class)) {
            As as = (As) getAnnotation(As.class);
            setName(as.value());
        } else {
            setName("arg" + getArgumentIndex());
        }
    }


    public Class<?> getType() {
        return type;
    }

    public boolean isAnnotationPresent(Class<? extends Annotation> annotation) {
        boolean found = false;
        for (Annotation anno : annotations) {
            if (anno.annotationType() == annotation) {
                found = true;
            }
        }
        return found;
    }

    public Annotation getAnnotation(final Class<? extends Annotation> annotation) {
        return annotations.stream().filter((a) -> a.annotationType() == annotation).findFirst()
            .orElse(null);
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public int getArgumentIndex() {
        return index - 1;
    }

    public boolean hasText() {
        return isAnnotationPresent(Text.class);
    }

    public boolean hasDefault() {
        return isAnnotationPresent(Default.class);
    }

    public String getDefault() {
        if (hasDefault()) {
            Default defaultV = (Default) getAnnotation(Default.class);
            return defaultV.value();
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public Object parse(String argument) {
        return registry.get(type).resolve(argument);
    }
}
