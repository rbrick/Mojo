package me.rbrickis.mojo.bukkit;

import me.rbrickis.mojo.annotations.As;
import me.rbrickis.mojo.annotations.Command;
import me.rbrickis.mojo.annotations.Require;
import me.rbrickis.mojo.annotations.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ExampleCommands {
    @Command(aliases = {"broadcast", "bc", "raw"})
    @Require(value = "mojo.broadcast", message = "&cYou do not have permission to use this command.")
    public void asTest(CommandSender sender, @As("message") @Text String message) {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

}
