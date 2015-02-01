package org.nebulamc.ncommand.impl.bungeecord;

import org.nebulamc.ncommand.annotations.Command;
import org.nebulamc.ncommand.annotations.Permission;
import org.nebulamc.ncommand.command.CommandBuilder;
import org.nebulamc.ncommand.command.Register;
import org.nebulamc.ncommand.impl.bukkit.BukkitCommandMap;
import org.nebulamc.ncommand.impl.bukkit.BukkitNebulaCommand;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Ryan on 1/31/2015
 * <p/>
 * Project: nCommand
 */
public class BungeeRegister implements Register {

    BungeeCommandMap map = new BungeeCommandMap();

    @Override
    public void register(Class<?> clz) {
       for (Method method : clz.getMethods()) {
            if(method.isAnnotationPresent(Command.class)) {
                Command cmd = method.getAnnotation(Command.class);
                Permission perm = null;
                if(method.isAnnotationPresent(Permission.class)) {
                    perm = method.getAnnotation(Permission.class);
                }
                CommandBuilder builder = new CommandBuilder(cmd, perm, method);
                BungeeNebulaCommand cmds = BungeeNebulaCommand.fromNebulaCommand(builder.build());
                map.register(cmd.name().toLowerCase(), cmds);

                for (String s : cmd.aliases()) {
                    map.register(s.toLowerCase(), cmds);
                }

            }
       }
    }

    @Override
    public void registerAll(List<Class<?>> classes) {
         for (Class<?> clazz : classes) {
              register(clazz);
         }
    }
}
