package me.rbrickis.mojo.parametric.binder;

/**
 * @param <B>
 * @param <T>
 */
public interface Binder<B, T> {

    Binder<B, T> bind(B bind);

    void to(T to);

}
