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
public @interface Permission {
    @NotNull String value();
    String message() default "§cYou do not have permission to execute this command!";
}
