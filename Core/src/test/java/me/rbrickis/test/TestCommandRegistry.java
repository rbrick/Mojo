package me.rbrickis.test;

import me.rbrickis.mojo.annotations.Command;
import me.rbrickis.mojo.dispatcher.CommandHolder;
import me.rbrickis.mojo.parametric.graph.CommandGraph;
import me.rbrickis.mojo.registry.CommandRegistry;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TestCommandRegistry implements CommandRegistry {
    private Map<String, CommandHolder> commandMap = new HashMap<>();

    private CommandGraph commandGraph;

    public TestCommandRegistry(CommandGraph graph) {
        this.commandGraph = graph;
    }

    public void registerCommand(CommandHolder command) {
        commandMap.put(command.getName(), command);
        for (String alias : command.getAliases()) {
            commandMap.put(alias, command);
        }
    }

    @Override
    public void registerObject(Object object) {
        for (Method method : object.getClass().getMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                registerCommand(new CommandHolder(method, commandGraph.getRegistry(), commandGraph.getSenderForMethod(method, commandGraph.getSender(Command.class))));
            }
        }
    }

    @Override
    public void registerClass(Class<?> clazz) {
        try {
            Object object = clazz.newInstance();
            registerObject(object);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("Unable to instantiate the class!");
        }
    }


    public CommandHolder get(String alias) {
        CommandHolder h = null;
        for (String a : commandMap.keySet()) {
            if (a.equalsIgnoreCase(alias)) {
                h = commandMap.get(a);
            }
        }
        return h;
    }

    public Map<String, CommandHolder> getCommandMap() {
        return commandMap;
    }
}
