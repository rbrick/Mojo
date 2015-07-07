package me.rbrickis.mojo.bukkit;

import me.rbrickis.mojo.bukkit.annotations.Player;
import me.rbrickis.mojo.bukkit.command.BukkitCommandRegistry;
import me.rbrickis.mojo.bukkit.parametric.BukkitParametricRegistry;
import me.rbrickis.mojo.parametric.graph.CommandGraph;
import me.rbrickis.mojo.registry.CommandRegistry;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        CommandGraph graph = new CommandGraph()
                .useSender(CommandSender.class)
                .unlessMarkedWith(Player.class)
                .thenUseSender(org.bukkit.entity.Player.class)
                .withParametricRegistry(new BukkitParametricRegistry());

        CommandRegistry registry = new BukkitCommandRegistry(graph, this);
        registry.registerClass(ExampleCommands.class);
    }
}
