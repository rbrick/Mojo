package me.rbrickis.mojo.parametric;

import me.rbrickis.mojo.Arguments;
import me.rbrickis.mojo.utils.PrimitiveUtils;

import java.util.ArrayList;
import java.util.List;

public class ParametricParser {

    private List<Parameter> parameters;


    public ParametricParser(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public Object[] parse(Object sender, Arguments arguments) {

        List<Object> objects = new ArrayList<>();
        objects.add(sender);

        for (Parameter parameter : parameters) {
            Object obj;
            if (arguments.get(parameter.getArgumentIndex()) == null) {
                if (parameter.hasDefault()) {
                    obj = parameter.parse(parameter.getDefault());
                } else {
                    throw new IllegalArgumentException("Argument at index " + parameter.getArgumentIndex() + " is null!");
                }
            } else {
                obj = parameter.parse(arguments.get(parameter.getArgumentIndex()));
            }
            // is continuous
            if (parameter.hasText()) {
                if (arguments.get(parameter.getArgumentIndex()) != null) {
                    obj = arguments.join(parameter.getArgumentIndex());
                }
                objects.add(obj);
                break;
            } else if (parameter.getType().isPrimitive()) {
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
