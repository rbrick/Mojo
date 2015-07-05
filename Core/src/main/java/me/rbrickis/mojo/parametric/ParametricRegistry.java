package me.rbrickis.mojo.parametric;

import me.rbrickis.mojo.parametric.binder.Binder;

import java.util.HashMap;
import java.util.Map;

public class ParametricRegistry implements Binder<Class<?>, ParameterResolver<?>> {

    private Map<Class<?>, ParameterResolver<?>> currentBindings = new HashMap<Class<?>, ParameterResolver<?>>();

    private Class<?> currentBind = null;

    public ParametricRegistry() {
        bind(String.class).to(new ParameterResolver<String>() {
            public String resolve(String argument) {
                return argument;
            }
        });

        bind(Integer.TYPE).to(new ParameterResolver<Integer>() {
            public Integer resolve(String argument) {
                int parsed = 0;
                try {
                    parsed = Integer.parseInt(argument);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
                return parsed;
            }
        });

        bind(Float.TYPE).to(new ParameterResolver<Float>() {
            public Float resolve(String argument) {
                float parsed = 0;
                try {
                    parsed = Float.parseFloat(argument);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
                return parsed;
            }
        });

        bind(Double.TYPE).to(new ParameterResolver<Double>() {
            public Double resolve(String argument) {
                Double parsed = 0d;
                try {
                    parsed = Double.parseDouble(argument);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
                return parsed;
            }
        });

        bind(Long.TYPE).to(new ParameterResolver<Long>() {
            public Long resolve(String argument) {
                Long parsed = 0L;
                try {
                    parsed = Long.parseLong(argument);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
                return parsed;
            }
        });

        bind(Byte.TYPE).to(new ParameterResolver<Byte>() {
            public Byte resolve(String argument) {
                Byte parsed = 0x0;
                try {
                    parsed = Byte.parseByte(argument);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
                return parsed;
            }
        });
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
