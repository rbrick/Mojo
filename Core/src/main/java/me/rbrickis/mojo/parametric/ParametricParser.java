package me.rbrickis.mojo.parametric;

import me.rbrickis.mojo.Arguments;
import me.rbrickis.mojo.utils.PrimitiveUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParametricParser {

    private List<Parameter> parameters;

    public ParametricParser(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public Object[] parse() {
        Arguments arguments = new Arguments(Arrays.asList("hello", "100", "yes"));

        List<Object> objects = new ArrayList<>();

        for (Parameter parameter : parameters) {
           Object obj = parameter.parse(arguments.get(parameter.getIndex()));
           if (parameter.getType().isPrimitive()) {
               if (Number.class.isAssignableFrom(parameter.getType())) {
                   obj = PrimitiveUtils.toPrimitiveNumber((Number) obj);
               } else if (Boolean.class.isAssignableFrom(parameter.getType())) {
                   obj = PrimitiveUtils.toPrimitiveBoolean((Boolean) obj);
               }
           }
            objects.add(obj);
        }

        return objects.toArray();
    }

}
