package me.rbrickis.mojo;

public interface CommandContext<S> {

    /**
     * @return The person who issued the command
     */
    S sender();


    /**
     * @return The arguments of the command provided
     */
    Arguments arguments();


}
