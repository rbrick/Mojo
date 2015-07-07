package me.rbrickis.mojo.bukkit.dispatcher;

import me.rbrickis.mojo.Arguments;
import me.rbrickis.mojo.dispatcher.CommandHolder;
import me.rbrickis.mojo.dispatcher.Dispatcher;
import me.rbrickis.mojo.registry.CommandRegistry;
import org.bukkit.entity.Player;

public class PlayerDispatcher implements Dispatcher<Player> {

    private CommandRegistry registry;

    public PlayerDispatcher(CommandRegistry registry) {
        this.registry = registry;
    }

    @Override
    public boolean dispatch(String commandName, Player sender, Arguments arguments) {
        if (registry.get(commandName) != null) {
            CommandHolder holder = registry.get(commandName);
            holder.call(sender, arguments);
            return true;
        } else {
            return false;
        }
    }
}
