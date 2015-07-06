package me.rbrickis.test;

import me.rbrickis.mojo.annotations.Command;
import me.rbrickis.mojo.annotations.Default;
import me.rbrickis.mojo.annotations.Text;

public class TestCommands {

    @Command(aliases = {"help"})
    public void help(Actor sender) {
        System.out.println("Help stuff!!!");
    }

    @Command(aliases = {"broadcast", "bc"})
    public void broadcast(Actor sender, @Default("Hello World!") @Text String message) {
        System.out.println(message);
    }
}
