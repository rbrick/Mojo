package me.rbrickis.mojo.bukkit.command;

import me.rbrickis.mojo.annotations.Command;
import me.rbrickis.mojo.bukkit.annotations.Player;
import me.rbrickis.mojo.bukkit.parametric.BukkitParametricRegistry;
import me.rbrickis.mojo.command.CommandHolder;
import me.rbrickis.mojo.parametric.graph.CommandGraph;
import me.rbrickis.mojo.registry.CommandRegistry;
import me.rbrickis.mojo.utils.Reflection;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BukkitCommandRegistry implements CommandRegistry {

    // Commands that we have registered ourselves.
    private Map<String, CommandHolder> ourCommands = new HashMap<>();

    private CommandGraph commandGraph;

    private SimpleCommandMap commandMap = Reflection.getSafeClass(Bukkit.getServer().getPluginManager())
            .<SimpleCommandMap>getField("commandMap").get();

    private HashMap<String, org.bukkit.command.Command> knownCommands = Reflection.getSafeClass(commandMap).
            <HashMap<String, org.bukkit.command.Command>>getField("knownCommands").get();


    private JavaPlugin parent;

    public BukkitCommandRegistry(JavaPlugin plugin) {
        this(new CommandGraph()
                .useSender(CommandSender.class)
                .unlessMarkedWith(Player.class)
                .thenUseSender(org.bukkit.entity.Player.class)
                .withParametricRegistry(new BukkitParametricRegistry()), plugin);
    }

    public BukkitCommandRegistry(CommandGraph graph, JavaPlugin plugin) {
        this.commandGraph = graph;
        this.parent = plugin;
    }

    public void registerCommand(CommandHolder command) {
        ourCommands.put(command.getName(), command);
        for (String alias : command.getAliases()) {
            ourCommands.put(alias, command);
        }

        if (knownCommands.containsKey(command.getName().toLowerCase())) {
            knownCommands.remove(command.getName());
            Reflection.getSafeClass(commandMap).getField("knownCommands").set(knownCommands);
        }
        commandMap.register(parent.getName(), new WrappedCommand(command));

        for (String alias : command.getAliases()) {
            if (knownCommands.containsKey(alias.toLowerCase())) {
                knownCommands.remove(alias.toLowerCase());
                Reflection.getSafeClass(commandMap).getField("knownCommands").set(knownCommands);
            }
            commandMap.register(parent.getName(), new WrappedCommand(command));
        }

    }

    @Override
    public void registerObject(Object object) {
        for (Method method : object.getClass().getMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                registerCommand(new CommandHolder(method, commandGraph.getRegistry(), commandGraph.getSenderForMethod(method, commandGraph.getSender(Command.class))));
            }
        }
    }

    @Override
    public void registerClass(Class<?> clazz) {
        try {
            Object object = clazz.newInstance();
            registerObject(object);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("Unable to instantiate the class!");
        }
    }

    @Override
    public CommandHolder get(String alias) {
        CommandHolder h = null;
        for (String a : ourCommands.keySet()) {
            if (a.equalsIgnoreCase(alias)) {
                h = ourCommands.get(a);
            }
        }
        return h;
    }
}
