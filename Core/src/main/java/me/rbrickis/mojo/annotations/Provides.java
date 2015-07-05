package me.rbrickis.mojo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Marks a class as one that provides sub-commands for another class that has an annotation marked with</p>
 * <p>{@link Command}.</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Provides {

    Class value();

}
