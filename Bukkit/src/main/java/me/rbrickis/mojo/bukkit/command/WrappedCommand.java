package me.rbrickis.mojo.bukkit.command;

import me.rbrickis.mojo.Arguments;
import me.rbrickis.mojo.bukkit.annotations.Player;
import me.rbrickis.mojo.dispatcher.CommandHolder;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class WrappedCommand extends Command {

    CommandHolder holder;

    public WrappedCommand(CommandHolder holder) {
        super(holder.getName());
        setAliases(Arrays.asList(holder.getAliases()));
        this.holder = holder;
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (holder.isAnnotationPresent(Player.class)) {
            if (!(commandSender instanceof org.bukkit.entity.Player)) {
                commandSender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
                return true;
            } else {
                holder.call((org.bukkit.entity.Player) commandSender, new Arguments(strings));
            }
        } else {
            holder.call(commandSender, new Arguments(strings));
        }
        return false;
    }
}
