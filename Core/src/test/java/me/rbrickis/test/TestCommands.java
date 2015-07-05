package me.rbrickis.test;

import me.rbrickis.mojo.CommandContext;
import me.rbrickis.mojo.annotations.Command;
import me.rbrickis.mojo.annotations.Default;
import me.rbrickis.mojo.annotations.Text;

public class TestCommands {

    @Command(aliases = {"help"})
    public void help(CommandContext<Actor> context) {
        System.out.println("Help stuff!!!");
    }

    @Command(aliases = {"broadcast", "bc"})
    public void broadcast(CommandContext<Actor> context, @Text @Default("Hello World!") String message) {
        System.out.println(message);
    }

}
