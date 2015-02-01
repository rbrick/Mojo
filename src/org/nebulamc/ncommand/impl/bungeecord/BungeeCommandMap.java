package org.nebulamc.ncommand.impl.bungeecord;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.PluginManager;
import org.nebulamc.ncommand.reflection.ReflectionHelper;

import java.util.HashMap;

/**
 * Created by Ryan on 1/31/2015
 * <p/>
 * Project: nCommand
 */
public class BungeeCommandMap {

    HashMap<String, Command> map;

    public BungeeCommandMap() {
        try {
            map = (HashMap<String, Command>) ReflectionHelper.getPrivateField(PluginManager.class, "commandMap").get(BungeeCord.getInstance().getPluginManager());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void register(String name, Command cmd) {
        map.put(name, cmd);
    }





}
