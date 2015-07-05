package me.rbrickis.mojo;

import me.rbrickis.mojo.annotations.*;

@Command(
        aliases = {"example", "ex"}
)
@Nests(Mojo.MojoNests.class)
public class Mojo {

    @Provides(Mojo.class)
    public class MojoNests {

        @Command(aliases = {"help", "h"}, desc = "Helps you out!")
        @Provide
        public void help(CommandContext context) {
            System.out.println("CARD GAMES ON MOTORCYCLES");
        }

        /**
         * If a method is within a class marked with {@link Provides}, but not marked with {@link Provide},
         * this will get registered as a normal command.
         */
        @Command(aliases = {"broadcast", "bc"}, desc = "Broadcast shit.")
        public void broadcast(CommandContext context, @Text @Default("Hello World!") String message) {
            System.out.println(message);
        }

    }

}
