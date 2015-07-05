package me.rbrickis.mojo.utils.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SafeMethod {

    private SafeClass<?> owner;
    private Method method;
    private Object[] parameters;

    public SafeMethod(SafeClass<?> owner, String name, final Object... parameters) {
        this.owner = owner;
        this.parameters = parameters;
        final Class<?>[] classes = new Class[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            classes[i] = parameters[i].getClass();
        }
        try {
            this.method = owner.getClazz().getDeclaredMethod(name, classes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public Object invoke() {
        method.setAccessible(true);
        if (method.getReturnType().equals(Void.TYPE)) {
            try {
                method.invoke(owner.get(), parameters);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return null; // returns nothing
        } else {
            try {
                return method.getReturnType().cast(method.invoke(owner.get(), parameters));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
