package me.rbrickis.mojo.bungee.parametric;

import me.rbrickis.mojo.parametric.ParametricRegistry;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeParametricRegistry extends ParametricRegistry {

    public BungeeParametricRegistry() {
        super();
        bind(ServerInfo.class).to(arg -> {
            ServerInfo info = null;
            for (ServerInfo i : ProxyServer.getInstance().getServers().values()) {
                if (i.getName().equalsIgnoreCase(arg)) {
                    info = i;
                }
            }
            return info;
        });
        bind(ProxiedPlayer.class).to(arg -> {
            ProxiedPlayer p = null;
            for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                if (player.getName().equalsIgnoreCase(arg)) {
                    p = player;
                }
            }
            return p;
        });
    }

}
