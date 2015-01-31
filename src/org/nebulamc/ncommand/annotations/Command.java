package org.nebulamc.ncommand.annotations;

import com.sun.istack.internal.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Ryan on 1/29/2015
 * <p/>
 * Project: nCommand
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {
    /**
     * The name of the command
     */
   @NotNull String name();

    /**
     * Aliases of the command.
     */
    String[] aliases() default {};

    /**
     * Usage of the command
     */
    String usage() default "§cUsage: /<command>";

    /**
     * Description of the command
     */
    String desc() default "An Nebula Command!";
}
