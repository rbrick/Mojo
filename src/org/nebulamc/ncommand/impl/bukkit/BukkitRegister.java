package org.nebulamc.ncommand.impl.bukkit;

import org.nebulamc.ncommand.annotations.Command;
import org.nebulamc.ncommand.annotations.Permission;
import org.nebulamc.ncommand.command.CommandBuilder;
import org.nebulamc.ncommand.command.Register;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Ryan on 1/31/2015
 * <p/>
 * Project: nCommand
 */
public class BukkitRegister implements Register {

    BukkitCommandMap map = new BukkitCommandMap();

    @Override
    public void register(Class<?> clz) {
        for (Method method : clz.getMethods()) {
            if(method.isAnnotationPresent(Command.class)) {
                Command cmd = method.getAnnotation(Command.class);

                if(method.isAnnotationPresent(Permission.class)) {
                    Permission perm = method.getAnnotation(Permission.class);
                    CommandBuilder builder = new CommandBuilder(cmd, perm, method);
                    BukkitNebulaCommand cmds = BukkitNebulaCommand.fromNebulaCommand(builder.build());
                    map.register(cmd.name(), cmds);
                    continue;
                }
                CommandBuilder builder = new CommandBuilder(cmd, method);
                BukkitNebulaCommand cmds = BukkitNebulaCommand.fromNebulaCommand(builder.build());
                System.out.println(cmds.getPermission());
                map.register(cmd.name(), cmds);
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
