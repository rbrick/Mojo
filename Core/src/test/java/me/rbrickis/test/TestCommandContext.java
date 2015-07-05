package me.rbrickis.test;

import me.rbrickis.mojo.Arguments;
import me.rbrickis.mojo.CommandContext;

public class TestCommandContext implements CommandContext<Actor> {

    private Actor actor;

    private Arguments args;

    public TestCommandContext(Actor actor, String[] args) {
        this.actor = actor;
        this.args = new Arguments(args);
    }

    /**
     * @return The person who issued the command
     */
    @Override
    public Actor sender() {
        return actor;
    }

    /**
     * @return The arguments of the command provided
     */
    @Override
    public Arguments arguments() {
        return args;
    }
}
