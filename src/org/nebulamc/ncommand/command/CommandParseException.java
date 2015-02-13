package org.nebulamc.ncommand.command;

/**
 * Created by Ryan on 2/5/2015
 * <p/>
 * Project: nCommand
 */
public class CommandParseException extends Exception {

    public CommandParseException(String message, CommandContext<?> cmd) {
        super(message);
        cmd.respond("§c" + message); // send the sender
    }

}

