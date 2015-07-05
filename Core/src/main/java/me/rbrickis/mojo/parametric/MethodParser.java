package me.rbrickis.mojo.parametric;

import me.rbrickis.mojo.Arguments;

import java.lang.reflect.Method;

public class MethodParser {

    private Method method;

    public MethodParser(Method method) {
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }

    /**
     * @param arguments - The arguments of the command
     * @return An array of objects to be passed into the method.
     */
    public Object[] parse(Arguments arguments) {

        boolean hasText = false;



        // Skip the first parameter since it should be the command context
        for (int i = 1; i < method.getParameterCount(); i++) {



        }

        return null;
    }
}
