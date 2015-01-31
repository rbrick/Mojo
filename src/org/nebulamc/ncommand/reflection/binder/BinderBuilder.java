package org.nebulamc.ncommand.reflection.binder;

import java.lang.reflect.Field;

/**
 * Created by Ryan on 1/30/2015
 * <p/>
 * Project: nCommand
 */
public class BinderBuilder<T, R> {

    T base;

    R result;

    Field fToGet;

    public BinderBuilder(T b) {
        this.base = b;
    }

    public BinderBuilder withResultOf(R result) {
         this.result = result;
         return this;
    }
}
