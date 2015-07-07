package me.rbrickis.mojo.bukkit.dispatcher;

import me.rbrickis.mojo.Arguments;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class DispatcherHandler implements Listener {

    private BukkitDispatcher dispatcher;
    private PlayerDispatcher playerDispatcher;

    public DispatcherHandler(BukkitDispatcher dispatcher, PlayerDispatcher playerDispatcher) {
        this.dispatcher = dispatcher;
        this.playerDispatcher = playerDispatcher;
    }


    @EventHandler
    public void onCommand(ServerCommandEvent event) {
        String cmdName = event.getCommand().replace("/", "").split(" ")[0];
        List<String> list = Arrays.asList(event.getCommand().replace("/", "").split(" "));
        Vector<String> fixed = new Vector<>(list);
        fixed.remove(cmdName);
        dispatcher.dispatch(cmdName, event.getSender(), new Arguments(fixed));


    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().replace("/", "").length() > 0) {
            String command = event.getMessage().replace("/", "").split(" ")[0];
            List<String> stuffs = Arrays.asList(event.getMessage().replace("/", "").split(" "));
            Vector<String> v = new Vector<String>(stuffs);
            v.remove(command);
            Arguments args = new Arguments(v);
            if (playerDispatcher.dispatch(command, event.getPlayer(), args)) {
                event.setCancelled(true);
            }
        }
    }
}
