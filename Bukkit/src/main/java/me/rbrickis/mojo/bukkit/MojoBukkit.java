package me.rbrickis.mojo.bukkit;

import me.rbrickis.mojo.Mojo;
import me.rbrickis.mojo.bukkit.annotations.Player;
import me.rbrickis.mojo.bukkit.dispatcher.BukkitDispatcher;
import me.rbrickis.mojo.bukkit.dispatcher.DispatcherHandler;
import me.rbrickis.mojo.bukkit.dispatcher.PlayerDispatcher;
import me.rbrickis.mojo.bukkit.parametric.BukkitParametricRegistry;
import me.rbrickis.mojo.parametric.graph.CommandGraph;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class MojoBukkit extends Mojo {

    public MojoBukkit(JavaPlugin plugin) {
        super(new CommandGraph()
        .useSender(CommandSender.class)
        .unlessMarkedWith(Player.class)
                .thenUseSender(org.bukkit.entity.Player.class)
                .withParametricRegistry(new BukkitParametricRegistry()));
        Bukkit.getPluginManager().registerEvents(
                new DispatcherHandler(new BukkitDispatcher(getRegistry()),
                        new PlayerDispatcher(getRegistry())), plugin);
    }
}
