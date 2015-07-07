package me.rbrickis.mojo.bukkit;

import me.rbrickis.mojo.annotations.Command;
import me.rbrickis.mojo.annotations.Default;
import me.rbrickis.mojo.annotations.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExampleCommands {

    @Command(aliases = "hello")
    @me.rbrickis.mojo.bukkit.annotations.Player
    public void helloWorld(Player player) {
        player.sendMessage(ChatColor.GOLD + "Hello, " + player.getName());
    }

    @Command(aliases = {"broadcast", "bc"})
    public void broadcast(CommandSender sender, @Text @Default("&6Hello World!") String message) {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
    }


}
