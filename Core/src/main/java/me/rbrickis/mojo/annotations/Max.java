package me.rbrickis.mojo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines the maximum number that can be passed into a method
 * i.e I enter /flyspeed 11
 * fly speed can only go up to 10
 *
 * so throw in {@code @Max("10")} on the float parameter. and if it is over the amount given,
 * it will use the maximum amount
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Max {
    String value();
}
