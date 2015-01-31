package org.nebulamc.ncommand.reflection.binder;

/**
 * Created by Ryan on 1/30/2015
 * <p/>
 * Project: nCommand
 *
 * This tells the system how to read input from an object (not a primitive or a string), e.g. what method is called,
 * how to execute, etc.
 */
public interface Binder<T, R> {
    /**
     * This specifies how to read a argument given in a command
     */
    R bind(T type);

}
