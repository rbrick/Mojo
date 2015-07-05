package me.rbrickis.mojo;

import java.util.Arrays;
import java.util.List;

public class Arguments {

    private List<String> arguments;

    public Arguments(String[] arguments) {
        this.arguments = Arrays.asList(arguments);
    }

    public Arguments(List<String> arguments) {
        this.arguments = arguments;
    }

    /**
     *  @see Arguments#size()
     */
    public int length() {
        return size();
    }

    /**
     * @return The size of the arguments provide
     */
    public int size() {
        return arguments.size();
    }

    /**
     * @param index the index to pull from
     * @return 'null' if the index is out of bounds, or the String at the index.
     */
    public String get(int index) {
        try {
            return arguments.get(index);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public List<String> getArguments() {
        return arguments;
    }
}
