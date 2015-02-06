package org.nebulamc.ncommand.command;

import com.sun.istack.internal.Nullable;
import org.nebulamc.ncommand.annotations.Todo;
import org.nebulamc.ncommand.command.parameter.ParameterResolver;
import org.nebulamc.ncommand.command.parameter.ParametricRegistry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Objects;

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
            m.invoke(null, cmd);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
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



    @Todo("Fix the NPEs and errors with ParametricRegistry")
    private Object[] resolve(CommandContext<?> args) throws CommandParseException {
        if (m.getParameterTypes().length > 1) {
            if (args.getArguments().size() < (m.getParameterTypes().length - 1)) {
                throw new CommandParseException("Argument length does not match method length!");
            }
            Object[] obj = new Object[m.getParameterTypes().length - 1];
            for (int i = 1; i < m.getParameterTypes().length; i++) {
                Type type = m.getParameterTypes()[i];
                if (registry.get(type) == null) {
                    throw new CommandParseException("Could not find resolver for " + type.getClass().getSimpleName());
                } else {
                    ParameterResolver<?> typeResolver = registry.get(type);
                    try {
                        Object o = typeResolver.resolveType(args.getArguments().get(i));
                        obj[i - 1] = o;
                    } catch (Throwable throwable) {
                        throw new CommandParseException("Type mismatch");
                    }
                }
            }
            return obj;
        } else {
            return null;
        }
    }

}
