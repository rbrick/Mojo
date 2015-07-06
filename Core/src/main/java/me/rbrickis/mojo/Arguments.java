package me.rbrickis.mojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Arguments {

    private List<String> arguments;

    public Arguments() {
        this.arguments = new ArrayList<>();
    }

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

    public String join(int at, char delimiter) {
        StringBuilder builder = new StringBuilder();
        for (int x = at; x < arguments.size(); x++) {
            builder.append(get(x)).append(delimiter);
        }
        return builder.toString();
    }

    public String join(int at) {
        return join(at, ' ');
    }


    public List<String> getArguments() {
        return arguments;
    }
}
