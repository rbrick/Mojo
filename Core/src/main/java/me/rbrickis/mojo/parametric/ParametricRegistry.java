package me.rbrickis.mojo.parametric;

import me.rbrickis.mojo.parametric.binder.Binder;

import java.util.HashMap;
import java.util.Map;

public class ParametricRegistry implements Binder<Class<?>, ParameterResolver<?>> {

    private Map<Class<?>, ParameterResolver<?>> currentBindings = new HashMap<Class<?>, ParameterResolver<?>>();

    private Class<?> currentBind = null;

    public ParametricRegistry() {
        bind(String.class).to(argument -> argument);

        bind(Integer.TYPE).to(argument -> {
            int parsed = 0;
            try {
                parsed = Integer.parseInt(argument);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            return parsed;
        });

        bind(Integer.class).to(currentBindings.get(Integer.TYPE));

        bind(Float.TYPE).to(argument -> {
            float parsed = 0;
            try {
                parsed = Float.parseFloat(argument);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            return parsed;
        });

        bind(Float.class).to(currentBindings.get(Float.TYPE));

        bind(Double.TYPE).to(argument -> {
            Double parsed = 0d;
            try {
                parsed = Double.parseDouble(argument);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            return parsed;
        });

        bind(Double.class).to(currentBindings.get(Double.TYPE));

        bind(Long.TYPE).to(argument -> {
            Long parsed = 0L;
            try {
                parsed = Long.parseLong(argument);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            return parsed;
        });

        bind(Long.class).to(currentBindings.get(Long.TYPE));



        bind(Byte.TYPE).to(argument -> {
            Byte parsed = 0x0;
            try {
                parsed = Byte.parseByte(argument);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            return parsed;
        });

        bind(Byte.class).to(currentBindings.get(Byte.TYPE));

        bind(Boolean.TYPE).to(argument -> {
            boolean parsed = false;
            if (argument.equalsIgnoreCase("true") || argument.equalsIgnoreCase("yes")) {
                parsed = true;
            } else if (argument.equalsIgnoreCase("false") || argument.equalsIgnoreCase("no")) {
                parsed = false;
            }
            return parsed;
        });

        bind(Boolean.class).to(currentBindings.get(Boolean.TYPE));

    }

    public Binder<Class<?>, ParameterResolver<?>> bind(Class<?> bind) {
        this.currentBind = bind;
        return this;
    }

    public void to(ParameterResolver<?> to) {
        if (currentBind == null) {
            throw new IllegalArgumentException("currentBind is null!");
        } else {
            currentBindings.put(currentBind, to);
            currentBind = null;
        }
    }

    public Map<Class<?>, ParameterResolver<?>> getBindings() {
        return currentBindings;
    }

    public ParameterResolver<?> get(Class<?> clazz) {
        if (!currentBindings.containsKey(clazz)) {
            throw new IllegalArgumentException("Could not find resolver for " + clazz.getSimpleName() + ".class");
        }
        return currentBindings.get(clazz);
    }
}
