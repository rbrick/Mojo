package org.nebulamc.ncommand.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Ryan on 2/5/2015
 * <p/>
 * Project: nCommand
 */
@Retention(RetentionPolicy.CLASS)
public @interface Todo {
    String value();
}
