package me.rbrickis.mojo.dispatcher;

import me.rbrickis.mojo.Arguments;

public interface Dispatcher<S> {

    boolean dispatch(String commandName, S sender, Arguments arguments) throws DispatchException;

}
