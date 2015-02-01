package org.nebulamc.ncommand.impl.bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.nebulamc.ncommand.command.NebulaCommand;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ryan on 1/31/2015
 * <p/>
 * Project: nCommand
 */
public class BukkitNebulaCommand extends Command {

    NebulaCommand command;

    public BukkitNebulaCommand(String name, String description, String usageMessage, List<String> aliases, Method method) {
        super(name, description, usageMessage, aliases);
         command = new NebulaCommand(name, (String[]) aliases.toArray(), usageMessage, description, method);
        if(command.getPermission() != null && !command.getPermission().isEmpty()) {
            this.setPermission(command.getPermission());
        }
        if(command.getPermMessage() != null) {
            this.setPermissionMessage(command.getPermMessage());
        }
    }
    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {
        BukkitCommandContext context = new BukkitCommandContext(sender, Arrays.asList(strings));
        command.execute(context);
        return false;
    }



    public static BukkitNebulaCommand fromNebulaCommand(NebulaCommand cmd) {
        return new BukkitNebulaCommand(cmd.getName(), cmd.getDesc(), cmd.getUsage(), Arrays.asList(cmd.getAliases()), cmd.getM());
    }


}
