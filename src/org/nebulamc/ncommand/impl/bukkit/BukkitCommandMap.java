package org.nebulamc.ncommand.impl.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;

/**
 * Created by Ryan on 1/31/2015
 * <p/>
 * Project: nCommand
 */
public class BukkitCommandMap {

    CommandMap map;

    public BukkitCommandMap() {
        try {
            Field field =  Bukkit.getServer().getPluginManager().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);

            map = (CommandMap) field.get(Bukkit.getServer().getPluginManager());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void register(String parent, Command cmd) {
        map.register(parent, cmd);
    }


}
