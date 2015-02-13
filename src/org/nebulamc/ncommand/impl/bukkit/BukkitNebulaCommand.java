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

    public BukkitNebulaCommand(NebulaCommand command) {
        super(command.getName(), command.getDesc(), command.getUsage(), Arrays.asList(command.getAliases()));
        this.command = command;
        if (command.getPermission() != null) {
            System.out.println("Setting permission to " + command.getPermission());
            this.setPermission(command.getPermission());
        }
        if (command.getPermMessage() != null) {
            this.setPermissionMessage(command.getPermMessage());
        }
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {
        if(command.getPermission() != null) {
            if(sender.hasPermission(command.getPermission())) {
                BukkitCommandContext context = new BukkitCommandContext(sender, Arrays.asList(strings));
                command.execute(context);
                return true;
            } else {
                sender.sendMessage(this.getPermissionMessage());
            }
        } else {
            BukkitCommandContext context = new BukkitCommandContext(sender, Arrays.asList(strings));
            command.execute(context);
            return true;
        }

        return false;
    }


    public static BukkitNebulaCommand fromNebulaCommand(NebulaCommand cmd) {
        return new BukkitNebulaCommand(cmd);
    }


}
