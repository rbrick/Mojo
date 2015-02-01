package org.nebulamc.ncommand;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.nebulamc.ncommand.annotations.Command;
import org.nebulamc.ncommand.command.CommandContext;
import org.nebulamc.ncommand.impl.bukkit.BukkitRegister;

import java.util.List;

/**
 * Created by Ryan on 1/31/2015
 * <p/>
 * Project: nCommand
 */
public class Example extends JavaPlugin {

    BukkitRegister register = new BukkitRegister();

    @Override
    public void onEnable() {
        register.register(this.getClass());
    }

    @Command(name = "example", aliases = "chickenbutt")
    public static void example(CommandContext cmd) {
       List args = cmd.getArguments();
       CommandSender sender = (CommandSender) cmd.getSender();

       if(args.size() == 0) {
           sender.sendMessage("§aI am a example");
       } else {
           sender.sendMessage("§a" + args.get(0));
       }
    }

    @Command(name = "example subcommand", aliases = "chickenbutt subcommand")
    public static void examples(CommandContext cmd) {
        List args = cmd.getArguments();
        CommandSender sender = (CommandSender) cmd.getSender();

        if(args.size() == 0) {
            sender.sendMessage("§aSubcommand");
        } else {
            sender.sendMessage("§a" + args.get(0));
        }
    }

}
