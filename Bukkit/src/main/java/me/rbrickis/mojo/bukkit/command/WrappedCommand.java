package me.rbrickis.mojo.bukkit.command;

import me.rbrickis.mojo.Arguments;
import me.rbrickis.mojo.bukkit.annotations.Player;
import me.rbrickis.mojo.command.CommandHolder;
import me.rbrickis.mojo.command.CommandInvocationException;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class WrappedCommand extends Command {

    private CommandHolder holder;

    public WrappedCommand(CommandHolder holder) {
        super(holder.getName());
        setAliases(Arrays.asList(holder.getAliases()));
        setDescription(holder.getDescription());
        setUsage("/" + holder.getUsage());
        if (!holder.getPermission().isEmpty()) {
            setPermission(holder.getPermission());
            if (!holder.getPermissionMessage().isEmpty()) {
                setPermissionMessage(
                    ChatColor.translateAlternateColorCodes('&', holder.getPermissionMessage()));
            }
        }

        this.holder = holder;
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!commandSender.hasPermission(getPermission())) {
            if (getPermissionMessage() != null) {
                commandSender.sendMessage(
                    ChatColor.translateAlternateColorCodes('&', getPermissionMessage()));
            } else {
                commandSender.sendMessage(ChatColor.RED + "No permission.");
            }
            return false;
        }
        if (holder.isAnnotationPresent(Player.class)) {
            if (!(commandSender instanceof org.bukkit.entity.Player)) {
                commandSender
                    .sendMessage(ChatColor.RED + "You must be a player to perform this command!");
                return true;
            } else {
                try {
                    if (!holder.call(commandSender, new Arguments(strings))) {
                        commandSender.sendMessage(ChatColor.RED + "Usage: " + getUsage());
                    }
                } catch (CommandInvocationException e) {
                    commandSender.sendMessage(ChatColor.RED
                        + "An error has occurred while performing this command!\nStacktrace Header: "
                        + e.getMessage());
                    e.printStackTrace();
                }
            }
        } else {
            try {
                if (!holder.call(commandSender, new Arguments(strings))) {
                    commandSender.sendMessage(ChatColor.RED + "Usage: " + getUsage());
                }
            } catch (CommandInvocationException e) {
                commandSender.sendMessage(ChatColor.RED
                    + "An error has occurred while performing this command!\nStacktrace Header: "
                    + e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }
}
