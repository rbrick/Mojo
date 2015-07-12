package me.rbrickis.mojo.bungee;

import me.rbrickis.mojo.annotations.Command;
import me.rbrickis.mojo.annotations.Text;
import me.rbrickis.mojo.bungee.annotations.Player;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SomeCommands {

    private Map<UUID, UUID> replyMap = new HashMap<>();

    @Command(aliases = {"sendto", "send"})
    public void sendTo(CommandSender sender, ProxiedPlayer player, ServerInfo serverInfo) {
        if (player == null) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Could not find specified player!"));
        } else if (serverInfo == null) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Server specified does not exist!"));
        } else {
            if (!serverInfo.canAccess(player)) {
                sender.sendMessage(new TextComponent(ChatColor.RED + "Player cannot access requested server!"));
            } else {
                player.connect(serverInfo, (aBoolean, throwable) -> {
                    if (aBoolean) {
                        sender.sendMessage(new TextComponent(ChatColor.GREEN + "Successfully sent player to " +
                                serverInfo.getName()));
                    } else {
                        sender.sendMessage(new TextComponent(ChatColor.RED + "An error has occurred: " + throwable.getMessage()));
                    }
                });
            }
        }
    }

    @Command(aliases = {"message", "msg", "tell"})
    @Player
    public void message(ProxiedPlayer sender, ProxiedPlayer receiver, @Text String message) {
        if (receiver == null) {
            new ComponentBuilder("").color(ChatColor.AQUA);
            sender.sendMessage(new TextComponent(ChatColor.RED + "Could not find player!"));
        } else {
            replyMap.put(sender.getUniqueId(), receiver.getUniqueId());
            replyMap.put(receiver.getUniqueId(), sender.getUniqueId());

            sender.sendMessage(new TextComponent(ChatColor.GRAY + "(To " + receiver.getDisplayName() + ChatColor.GRAY + "): "
                    + ChatColor.WHITE + message));
            receiver.sendMessage(new TextComponent(ChatColor.GRAY + "(From " + sender.getDisplayName() + ChatColor.GRAY + "): "
                    + ChatColor.WHITE + message));
        }
    }

    @Command(aliases = {"reply", "r"}, desc = "Reply to someone who has messaged you!")
    @Player
    public void reply(ProxiedPlayer sender, @Text String message) {
        if (replyMap.containsKey(sender.getUniqueId())) {
            UUID toSend = replyMap.get(sender.getUniqueId());

            if (ProxyServer.getInstance().getPlayer(toSend) != null) {
                ProxiedPlayer toSendPlayer = ProxyServer.getInstance().getPlayer(toSend);
                sender.sendMessage(new TextComponent(ChatColor.GRAY + "(To " + toSendPlayer.getDisplayName() + ChatColor.GRAY + "): "
                        + ChatColor.WHITE + message));
                toSendPlayer.sendMessage(new TextComponent(ChatColor.GRAY + "(From " + sender.getDisplayName() + ChatColor.GRAY + "): "
                        + ChatColor.WHITE + message));
            } else {
                replyMap.remove(toSend);
                replyMap.remove(sender.getUniqueId());
                sender.sendMessage(new TextComponent(ChatColor.RED + "The player you were messaging is not online!"));
            }
        } else {
            sender.sendMessage(new TextComponent(ChatColor.RED + "You have no one to reply to!"));
        }
    }


}
