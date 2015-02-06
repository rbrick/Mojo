package org.nebulamc.ncommand.command;

import java.util.List;

/**
 * Created by Ryan on 1/30/2015
 * <p/>
 * Project: nCommand
 */
public interface CommandContext<S> {

    List<String> getArguments();

    S getSender();

    void respond(String message);
}
