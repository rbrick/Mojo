package me.rbrickis.test;

import me.rbrickis.mojo.Arguments;
import me.rbrickis.mojo.dispatcher.CommandHolder;
import me.rbrickis.mojo.dispatcher.Dispatcher;
import me.rbrickis.mojo.registry.CommandRegistry;

public class TestDispatcher implements Dispatcher<Actor> {

    private CommandRegistry registry;

    public TestDispatcher(CommandRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void dispatch(String commandName, Actor sender, Arguments arguments) {
        CommandHolder commandHolder = registry.get(commandName);

        if (commandHolder != null) {
            System.out.println("Sender " + sender.getName() + " issued command " + commandHolder.getName());
            commandHolder.call(sender, arguments);
        } else {
            System.out.println("Could not find command '" + commandName + "'.");
        }
    }
}
