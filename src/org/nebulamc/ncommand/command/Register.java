package org.nebulamc.ncommand.command;

import java.util.List;

/**
 * Created by Ryan on 1/31/2015
 * <p/>
 * Project: nCommand
 */
public interface Register {

    public void register(Class<?> clz);

    public void registerAll(List<Class<?>> classes);
}
