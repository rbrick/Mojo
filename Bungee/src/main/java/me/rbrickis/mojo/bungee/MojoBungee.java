package me.rbrickis.mojo.bungee;

import me.rbrickis.mojo.bungee.annotations.Player;
import me.rbrickis.mojo.bungee.command.BungeeCommandRegistry;
import me.rbrickis.mojo.bungee.parametric.BungeeParametricRegistry;
import me.rbrickis.mojo.parametric.graph.CommandGraph;
import me.rbrickis.mojo.registry.CommandRegistry;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public class MojoBungee extends Plugin {

    @Override
    public void onEnable() {
        CommandGraph graph = new CommandGraph()
                .useSender(CommandSender.class)
                .unlessMarkedWith(Player.class)
                .thenUseSender(ProxiedPlayer.class)
                .withParametricRegistry(new BungeeParametricRegistry());
        CommandRegistry registry = new BungeeCommandRegistry(graph, this);
        registry.registerObject(new SomeCommands());
    }

}
