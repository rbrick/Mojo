package me.rbrickis.mojo.parametric;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    private Method method;

    public MethodParser(Method method, ParametricRegistry registry) {
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }

    /**
     * @return A list of {@link Parameter}s
     */
    public List<Parameter> parse() {
        List<Parameter> parameters = new ArrayList<>();
        for (int i = 1; i < method.getParameterCount(); i++) {
            Parameter lp = new Parameter(method.getParameterTypes()[i], method.getParameterAnnotations()[i]);
            parameters.add(lp);
        }
        return parameters;
    }
}
