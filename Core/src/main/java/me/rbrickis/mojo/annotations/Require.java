package me.rbrickis.mojo.annotations;


public @interface Require {
    /**
     * @return The permission required to perform the command
     */
    String value();
}
