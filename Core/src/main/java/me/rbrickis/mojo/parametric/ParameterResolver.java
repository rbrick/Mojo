package me.rbrickis.mojo.parametric;

public interface ParameterResolver<T> {
    T resolve(String argument);
}
