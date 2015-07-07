package me.rbrickis.mojo;

import me.rbrickis.mojo.parametric.graph.CommandGraph;
import me.rbrickis.mojo.registry.CommandRegistry;

public class Mojo {

    private CommandRegistry registry;

    private CommandGraph graph;

    public Mojo(CommandGraph graph) {
        this.graph = graph;
        this.registry = new CommandRegistry(graph);
    }

    public CommandGraph getGraph() {
        return graph;
    }

    public CommandRegistry getRegistry() {
        return registry;
    }
}
