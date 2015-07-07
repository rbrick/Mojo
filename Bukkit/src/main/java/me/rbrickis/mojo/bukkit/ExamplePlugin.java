package me.rbrickis.mojo.bukkit;

import me.rbrickis.mojo.Mojo;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {

    private Mojo mojo;

    @Override
    public void onEnable() {
        this.mojo = new MojoBukkit(this);
        mojo.getRegistry().register(new ExampleCommands());
    }
}
