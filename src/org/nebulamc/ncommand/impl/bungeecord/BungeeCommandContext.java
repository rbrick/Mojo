package org.nebulamc.ncommand.impl.bungeecord;

import net.md_5.bungee.api.CommandSender;
import org.nebulamc.ncommand.command.CommandContext;

import java.util.List;

/**
 * Created by Ryan on 2/1/2015
 * <p/>
 * Project: nCommand
 */
public class BungeeCommandContext<S extends CommandSender> implements CommandContext {

    CommandSender sender;
    List<String> args;

    public BungeeCommandContext(CommandSender sender, List<String> args) {
        this.sender = sender;
        this.args = args;
    }


    @Override
    public List<String> getArguments() {
        return args;
    }

    @Override
    public CommandSender getSender() {
        return sender;
    }
}
