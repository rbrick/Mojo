package org.nebulamc.ncommand.impl.bungeecord;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import org.nebulamc.ncommand.command.NebulaCommand;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Ryan on 2/1/2015
 * <p/>
 * Project: nCommand
 */
public class BungeeNebulaCommand extends Command {

    NebulaCommand command;

    public BungeeNebulaCommand(String name, String permission, Method m,String... aliases) {
        super(name, permission, aliases);
        command = new NebulaCommand(name, aliases, null, null,m);
        if (permission != null && !permission.isEmpty()) {
            command.setPermission(permission);
        }
    }

    @Override
    public void execute(CommandSender sender, String[] strings) {
        BungeeCommandContext ctx = new BungeeCommandContext(sender, Arrays.asList(strings));
        command.execute(ctx);
    }

    public static BungeeNebulaCommand fromNebulaCommand(NebulaCommand cmd) {
        return new BungeeNebulaCommand(cmd.getName(), cmd.getPermission(),cmd.getM() ,cmd.getAliases());
    }
}
