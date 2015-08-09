package me.rbrickis.test;

import me.rbrickis.mojo.Arguments;
import me.rbrickis.mojo.command.CommandHolder;
import me.rbrickis.mojo.command.CommandInvocationException;

public class WrappedCommand extends TestCommand {

    private CommandHolder holder;

    public WrappedCommand(CommandHolder holder) {
        super(holder.getName(), holder.getAliases(), holder.getDescription());
        this.holder = holder;
    }

    @Override
    public void execute(Actor sender, Arguments arguments) {
        try {
            holder.call(sender, arguments);
        } catch (CommandInvocationException e) {
            e.printStackTrace();
        }
    }
}
