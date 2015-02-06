package org.nebulamc.ncommand.impl.bukkit;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.nebulamc.ncommand.command.CommandContext;

import java.util.List;

/**
 * Created by Ryan on 1/31/2015
 * <p/>
 * Project: nCommand
 */
public class BukkitCommandContext<S extends CommandSender> implements CommandContext {

    CommandSender sender;
    List<String> arguments;

    public BukkitCommandContext(CommandSender sender, List<String> arguments) {
         this.sender = sender;
         this.arguments = arguments;
    }

    @Override
    public List<String> getArguments() {
        return arguments;
    }

    @Override
    public CommandSender getSender() {
        return sender;
    }

    @Override
    public void respond(String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
