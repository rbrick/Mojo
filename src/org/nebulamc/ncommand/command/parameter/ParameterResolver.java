package org.nebulamc.ncommand.command.parameter;

/**
 * Created by Ryan on 2/2/2015
 * <p/>
 * Project: nCommand
 */
public interface ParameterResolver<T> {
    T resolveType(String input);
}
