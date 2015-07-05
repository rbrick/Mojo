package me.rbrickis.test;

import me.rbrickis.mojo.CommandContext;
import me.rbrickis.mojo.dispatcher.CommandHolder;
import me.rbrickis.mojo.dispatcher.Dispatcher;
import me.rbrickis.mojo.registry.CommandRegistry;

public class TestDispatcher implements Dispatcher {

    private CommandRegistry registry;

    public TestDispatcher(CommandRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void dispatch(String commandName, CommandContext sender) {
        CommandHolder commandHolder = registry.get(commandName);

        if (commandHolder != null) {
            commandHolder.call(sender);
        } else {
            System.out.println("Could not find command '" + commandName + "'.");
        }
    }
}
