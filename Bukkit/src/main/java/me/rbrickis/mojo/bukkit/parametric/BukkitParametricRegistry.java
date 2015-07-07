package me.rbrickis.mojo.bukkit.parametric;

import me.rbrickis.mojo.parametric.ParametricRegistry;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BukkitParametricRegistry extends ParametricRegistry {

    public BukkitParametricRegistry() {
        super();
        bind(Player.class).to(argument -> {
            Player found = null;
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getName().equalsIgnoreCase(argument)) {
                    found = player;
                }
            }
            return found;
        });
    }

}
