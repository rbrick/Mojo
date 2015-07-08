package me.rbrickis.mojo.registry;

import me.rbrickis.mojo.command.CommandHolder;

public interface CommandRegistry {

    void registerCommand(CommandHolder holder);

    void registerObject(Object object);

    void registerClass(Class<?> clazz);

    CommandHolder get(String alias);

}
