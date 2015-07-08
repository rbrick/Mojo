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

    public ParsedResult parse(Object sender, Arguments arguments) {
        boolean wasSuccess = true;
        List<Object> objects = new ArrayList<>();
        objects.add(sender);

        for (Parameter parameter : parameters) {
            Object obj = null;
            if (arguments.get(parameter.getArgumentIndex()) == null) {
                if (parameter.hasDefault()) {
                    obj = parameter.parse(parameter.getDefault());
                } else {
                    wasSuccess = false;
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

        return new ParsedResult(objects.toArray(), wasSuccess);
    }

    public class ParsedResult {
        private Object[] objects;
        private boolean successful;

        public ParsedResult(Object[] objects, boolean successful) {
            this.objects = objects;
            this.successful = successful;
        }

        public boolean isSuccessful() {
            return successful;
        }

        public Object[] getObjects() {
            return objects;
        }
    }

}
