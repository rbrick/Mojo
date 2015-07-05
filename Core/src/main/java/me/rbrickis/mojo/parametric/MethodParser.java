package me.rbrickis.mojo.parametric;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    private Method method;

    private ParametricRegistry registry;

    public MethodParser(Method method, ParametricRegistry registry) {
        this.method = method;
        this.registry = registry;
    }

    public Method getMethod() {
        return method;
    }

    public ParametricRegistry getRegistry() {
        return registry;
    }

    /**
     * @return A list of {@link Parameter}s
     */
    public List<Parameter> parse() {
        List<Parameter> parameters = new ArrayList<>();
        for (int i = 1; i < method.getParameterCount(); i++) {
            Parameter lp = new Parameter(method.getParameterTypes()[i], method.getParameterAnnotations()[i], registry, i);
            parameters.add(lp);
        }
        return parameters;
    }
}
