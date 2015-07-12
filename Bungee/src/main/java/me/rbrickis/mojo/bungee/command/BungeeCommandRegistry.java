package me.rbrickis.mojo.bungee.command;

import me.rbrickis.mojo.bungee.annotations.Player;
import me.rbrickis.mojo.bungee.parametric.BungeeParametricRegistry;
import me.rbrickis.mojo.command.CommandHolder;
import me.rbrickis.mojo.parametric.graph.CommandGraph;
import me.rbrickis.mojo.registry.CommandRegistry;
import me.rbrickis.mojo.utils.Reflection;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BungeeCommandRegistry implements CommandRegistry {

    private final Map<String, CommandHolder> ourCommands = new HashMap<>();

    private final Map<String, Command> commandMap = Reflection.getSafeClass(ProxyServer.getInstance().getPluginManager())
            .<Map<String, Command>>getField("commandMap").get();

    private CommandGraph graph;

    private Plugin parent;

    public BungeeCommandRegistry(Plugin plugin) {
        this(new CommandGraph()
                .useSender(CommandSender.class)
                .unlessMarkedWith(Player.class)
                .thenUseSender(ProxiedPlayer.class)
                .withParametricRegistry(new BungeeParametricRegistry()), plugin);
    }

    public BungeeCommandRegistry(CommandGraph graph, Plugin plugin) {
        this.graph = graph;
        this.parent = plugin;
    }


    @Override
    public void registerCommand(CommandHolder holder) {
        ourCommands.put(holder.getName(), holder);
        if (commandMap.containsKey(holder.getName().toLowerCase())) {
            commandMap.remove(holder.getName().toLowerCase());
            Reflection.getSafeClass(ProxyServer.getInstance().getPluginManager()).
                    <Map<String, Command>>getField("commandMap").set(commandMap);
        }
        ProxyServer.getInstance().getPluginManager().registerCommand(parent, new WrappedCommand(holder));
    }

    @Override
    public void registerObject(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(me.rbrickis.mojo.annotations.Command.class)) {
                registerCommand(new CommandHolder(method, graph.getRegistry(), graph.getSenderForMethod(method, CommandSender.class)));
            }
        }
    }

    @Override
    public void registerClass(Class<?> clazz) {
        try {
            Object re = clazz.newInstance();
            registerObject(re);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CommandHolder get(String alias) {
        return ourCommands.get(alias);
    }
}
