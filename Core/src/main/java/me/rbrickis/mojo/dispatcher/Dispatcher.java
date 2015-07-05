package me.rbrickis.mojo.dispatcher;

import me.rbrickis.mojo.CommandContext;

public interface Dispatcher {

    void dispatch(String commandName, CommandContext sender);

}
