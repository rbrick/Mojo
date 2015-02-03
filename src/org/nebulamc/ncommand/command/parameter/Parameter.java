package org.nebulamc.ncommand.command.parameter;

import java.lang.reflect.Type;

/**
 * Created by Ryan on 2/2/2015
 * <p/>
 * Project: nCommand
 */
public class Parameter<T> {

    String name;
    Type type;
    T value = null;

    public Parameter(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public T attemptParse(String input) throws ParameterParseException{
       try {

       } catch (Throwable throwable) {
           throw new ParameterParseException(input + " is not type of " + type.getClass().getSimpleName());
       }
        return value;
    }

}
