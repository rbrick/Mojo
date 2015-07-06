package me.rbrickis.mojo.utils.auth;

public interface Authenticator {

    boolean testPermissions(String permission);

}
