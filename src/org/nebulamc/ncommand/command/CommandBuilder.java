package org.nebulamc.ncommand.command;

import com.sun.istack.internal.Nullable;
import org.nebulamc.ncommand.annotations.Command;
import org.nebulamc.ncommand.annotations.Permission;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by Ryan on 1/30/2015
 * <p/>
 * Project: nCommand
 */
public class CommandBuilder {

    Command _command;
    @Nullable Permission _perm;
    Method _m;
    boolean isStatic = true;

    public CommandBuilder(Command command, Method m) {
        this(command, null, m);
    }

    public CommandBuilder(Command command,Permission perm, Method m) {
        if(m.getParameterTypes().length <= 0) {
            throw new IllegalArgumentException("Must have at least one argument");
        } else if(!m.getParameterTypes()[0].isAssignableFrom(CommandContext.class)) {
            throw new IllegalArgumentException("First argument must be assignable from CommandContext.class");
        } else if(!Modifier.isStatic(m.getModifiers())) {
            isStatic = false;
        }
        this._command = command;
        this._m = m;
        this._perm = perm;
    }








    public NebulaCommand build() {
        if(_perm == null) {
            NebulaCommand nc = new NebulaCommand(_command.name(), _command.aliases(), _command.usage(), _command.desc(), _m);
            nc.setStatic(isStatic);
            return nc;
        } else {
            NebulaCommand nc = new NebulaCommand(_command.name(), _command.aliases(), _command.usage(), _perm.value(), _m);
            nc.setPermMessage(_perm.message());
            nc.setStatic(isStatic);
            return nc;
        }
    }



}
