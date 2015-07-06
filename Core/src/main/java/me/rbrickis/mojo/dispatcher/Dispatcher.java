package me.rbrickis.mojo.dispatcher;

import me.rbrickis.mojo.Arguments;

public interface Dispatcher<S> {

    void dispatch(String commandName, S sender, Arguments arguments);

}
