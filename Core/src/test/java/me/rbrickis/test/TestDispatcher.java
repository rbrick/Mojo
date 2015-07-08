package me.rbrickis.test;

import me.rbrickis.mojo.Arguments;
import me.rbrickis.mojo.command.CommandHolder;
import me.rbrickis.mojo.dispatcher.DispatchException;
import me.rbrickis.mojo.dispatcher.Dispatcher;
import me.rbrickis.mojo.registry.CommandRegistry;

public class TestDispatcher implements Dispatcher<Actor> {

    private CommandRegistry registry;

    public TestDispatcher(CommandRegistry registry) {
        this.registry = registry;
    }

    @Override
    public boolean dispatch(String commandName, Actor sender, Arguments arguments) throws DispatchException{
        CommandHolder commandHolder = registry.get(commandName);

        if (commandHolder != null) {
            System.out.println("Sender " + sender.getName() + " issued command " + commandHolder.getName());
            commandHolder.call(sender, arguments);
            return true;
        } else {
            System.out.println("Could not find command '" + commandName + "'.");
            throw new DispatchException("Could not find command!");
        }
    }
}
