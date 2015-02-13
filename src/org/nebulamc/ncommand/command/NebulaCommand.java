package org.nebulamc.ncommand.command;

import com.sun.istack.internal.Nullable;
import org.nebulamc.ncommand.command.parameter.ParameterResolver;
import org.nebulamc.ncommand.command.parameter.ParametricRegistry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan on 1/31/2015
 * <p/>
 * Project: nCommand
 */
public class NebulaCommand {

    String name;
    String[] aliases;
    @Nullable
    String permission;
    @Nullable
    String usage;
    @Nullable
    String desc;
    @Nullable
    String permmessage;
    Method m;
    boolean isStatic;


    ParametricRegistry registry = new ParametricRegistry();


    public NebulaCommand(String name, String[] aliases, String usage, String desc, Method m) {
        this(name, aliases, usage, desc, "", m);
    }

    public NebulaCommand(String name, String[] aliases, String usage, String desc, String permission, Method m) {
        this.name = name;
        this.aliases = aliases;
        this.permission = permission;
        this.m = m;
        this.usage = usage;
        this.desc = desc;
        m.setAccessible(true);
    }

    public void execute(CommandContext<?> cmd) {
        try {
            Object[] parameters = resolve(cmd);
            if (isStatic) {
                m.invoke(null, parameters);
            } else {
               m.invoke(m.getDeclaringClass().newInstance(), parameters);
            }
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException | CommandParseException e) {
            e.printStackTrace();
            cmd.respond("§cAn error occurred executing invoking the method!");
        }
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermMessage(String permmessage) {
        this.permmessage = permmessage;
    }

    public String getPermMessage() {
        return permmessage;
    }

    public String getName() {
        return name;
    }

    public Method getM() {
        return m;
    }

    public String getDesc() {
        return desc;
    }

    public String getUsage() {
        return usage;
    }

    public String[] getAliases() {
        return aliases;
    }

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    private Object[] resolve(CommandContext<?> arguments) throws CommandParseException {
        List<Object> obj = new ArrayList<>();
        obj.add(arguments);
        if (m.getParameterTypes().length > 1) {
            if (arguments.getArguments().size() != (m.getParameterTypes().length - 1)) {
                throw new CommandParseException("Argument length does not match method length!", arguments);
            }

            for (int i = 1; i < m.getParameterTypes().length; i++) {
                Class<?> type = m.getParameterTypes()[i];
                int argsIndex = i - 1;
                if (!registry.getResolvedTypes().containsKey(type)) {
                    throw new CommandParseException("Could not find resolver for " + type.getSimpleName(), arguments);
                } else {
                    ParameterResolver<?> typeResolver = registry.get(type);
                    Object o = type.cast(typeResolver.resolveType(arguments.getArguments().get(argsIndex)));
                    obj.add(o);
                    System.out.println("Type: " + type.getSimpleName() + ", Object Type: " + o.getClass().getSimpleName());
                }
            }
        }
        return obj.toArray();
    }

}
