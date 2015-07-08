package me.rbrickis.mojo.parametric;

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

    public Parameter(Class<?> type, Annotation[] annotations, ParametricRegistry registry, String name, int index) {
        this.type = type;
        this.annotations = Arrays.asList(annotations);
        this.registry = registry;
        this.index = index;
        this.name = name;
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
        if (isAnnotationPresent(annotation)) {
            return annotations.stream().filter((a) -> a.annotationType() == annotation)
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("Could not find annotation!"));
        }
        return null;
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
