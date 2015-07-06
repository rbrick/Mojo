package me.rbrickis.mojo.registry;

import me.rbrickis.mojo.annotations.Command;
import me.rbrickis.mojo.dispatcher.CommandHolder;
import me.rbrickis.mojo.parametric.graph.CommandGraph;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {

    private Map<String, CommandHolder> commandMap = new HashMap<>();

    private CommandGraph commandGraph;

    public CommandRegistry(CommandGraph graph) {
        this.commandGraph = graph;
    }

    public void registerCommand(CommandHolder command) {
        commandMap.put(command.getName(), command);
        for (String alias : command.getAliases()) {
            commandMap.put(alias, command);
        }
    }

    public void register(Object object) {
        for (Method method : object.getClass().getMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                registerCommand(new CommandHolder(method, commandGraph.getRegistry(), commandGraph.getSenderType()));
            }
        }
    }


    public CommandHolder get(String alias) {
        return commandMap.get(alias);
    }

    public Map<String, CommandHolder> getCommandMap() {
        return commandMap;
    }
}
