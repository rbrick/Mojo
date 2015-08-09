package me.rbrickis.mojo.bukkit;

import me.rbrickis.mojo.bukkit.command.BukkitCommandRegistry;
import me.rbrickis.mojo.registry.CommandRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        CommandRegistry registry = new BukkitCommandRegistry(this);
        registry.registerClass(ExampleCommands.class);
    }


}
