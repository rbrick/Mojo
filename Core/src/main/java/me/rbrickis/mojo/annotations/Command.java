package me.rbrickis.mojo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Command {

    /**
     * @return The names of the commands. The first alias in the list takes priority
     */
    String[] aliases();

    /**
     * @return The description of the command
     */
    String desc() default "";

}
