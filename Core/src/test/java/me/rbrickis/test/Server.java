package me.rbrickis.test;

import me.rbrickis.mojo.CommandContext;
import me.rbrickis.mojo.dispatcher.Dispatcher;
import me.rbrickis.mojo.registry.CommandRegistry;

import java.util.Scanner;

public class Server {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        String command = "";
        String commandSpecifier = "!";

        CommandRegistry registry = new CommandRegistry();
        registry.register(new TestCommands());

        Actor systemActor = new Actor("System");

        Dispatcher dispatcher = new TestDispatcher(registry);

        CommandContext<Actor> sender = new TestCommandContext(systemActor, new String[]{});
        dispatcher.dispatch("help", sender);
        dispatcher.dispatch("broadcast", sender);
        sender = new TestCommandContext(systemActor, new String[]{"This", "is", "a", "test!"});
        dispatcher.dispatch("broadcast", sender);
    }

}
