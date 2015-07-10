package me.rbrickis.test;

import me.rbrickis.mojo.Arguments;
import me.rbrickis.mojo.dispatcher.DispatchException;
import me.rbrickis.mojo.dispatcher.Dispatcher;
import me.rbrickis.mojo.parametric.graph.CommandGraph;
import me.rbrickis.mojo.registry.CommandRegistry;

import java.util.Arrays;
import java.util.Collections;

public class Server {

    public static void main(String... args) throws DispatchException {
        CommandGraph graph = new CommandGraph()
                .useSender(Actor.class);

        Actor sender = new Actor("System", 18);

        CommandRegistry registry = new TestCommandRegistry(graph);

        registry.registerClass(TestCommands.class);

        Dispatcher<Actor> dispatcher = new TestDispatcher(registry);

        // no args in method (doesn't matter what is provided, it will always execute the code)
        dispatcher.dispatch("help", sender, new Arguments(Arrays.asList("Blah", "blah", "bal")));
        // has 1 argument, however the 1 argument is marked with a @Default annotation, so the default value gets passed
        // in.
        dispatcher.dispatch("broadcast", sender, new Arguments());

        // has 1 argument, but argument marked with @Text annotation, so i can provide as many arguments as i want,
        // and it will go on forever (or at least until the jvm poops out).
        dispatcher.dispatch("broadcast", sender, new Arguments("This", "is", "a", "test"));
        dispatcher.dispatch("setage", sender, new Arguments(Collections.singletonList("18")));
    }

}
