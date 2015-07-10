package me.rbrickis.mojo.bungee.command;

import me.rbrickis.mojo.Arguments;
import me.rbrickis.mojo.bungee.annotations.Player;
import me.rbrickis.mojo.command.CommandHolder;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class WrappedCommand extends Command {

    CommandHolder holder;

    public WrappedCommand(CommandHolder holder) {
        super(holder.getName());
        this.holder = holder;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (holder.isAnnotationPresent(Player.class)) {
            if (!(commandSender instanceof ProxiedPlayer)) {
                commandSender.sendMessage(new TextComponent(ChatColor.RED + "You must be a player to perform this command!"));
            } else {
                if (!holder.call(commandSender, new Arguments(strings))) {
                    commandSender.sendMessage(new TextComponent(ChatColor.RED + "Usage: " + holder.getUsage()));
                }
            }
        } else {
            if (!holder.call(commandSender, new Arguments(strings))) {
                commandSender.sendMessage(new TextComponent(ChatColor.RED + "Usage: " + holder.getUsage()));
            }
        }
    }

    @Override
    public String[] getAliases() {
        return holder.getAliases();
    }
}
