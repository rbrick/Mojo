package me.rbrickis.mojo.parametric.graph;

import me.rbrickis.mojo.parametric.ParametricRegistry;

public class CommandGraph {

    private Class<?> senderType = null;

    private ParametricRegistry registry = new ParametricRegistry();

    public CommandGraph useSender(Class<?> clazz) {
        this.senderType = clazz;
        return this;
    }

    public CommandGraph withParametricRegistry(ParametricRegistry registry) {
        this.registry = registry;
        return this;
    }

    public ParametricRegistry getRegistry() {
        return registry;
    }

    public Class<?> getSenderType() {
        return senderType;
    }
}
