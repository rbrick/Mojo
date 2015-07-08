package me.rbrickis.mojo.command;

import me.rbrickis.mojo.Arguments;
import me.rbrickis.mojo.annotations.Command;
import me.rbrickis.mojo.parametric.MethodParser;
import me.rbrickis.mojo.parametric.Parameter;
import me.rbrickis.mojo.parametric.ParametricParser;
import me.rbrickis.mojo.parametric.ParametricRegistry;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command
 */
public class CommandHolder {

    private String name;

    private String[] aliases;

    private ParametricRegistry registry;

    private Method method;

    private Class<?> senderType;

    private List<Parameter> parameters;

    private String usage;

    private String description;

    public CommandHolder(Method method, ParametricRegistry registry, Class<?> senderType) {

        Command command = method.getDeclaredAnnotation(Command.class);
        if (command.aliases().length == 0) {
            throw new IllegalArgumentException("Must have at least one alias!");
        } else {
            name = command.aliases()[0];
            List<String> laliases = new ArrayList<>();
            for (int i = 1; i < command.aliases().length; i++) {
                laliases.add(command.aliases()[i]);
            }
            this.aliases = laliases.toArray(new String[]{});
        }
        this.registry = registry;
        this.method = method;
        this.senderType = senderType;
        this.method.setAccessible(true);
        MethodParser parser = new MethodParser(method, registry);
        this.parameters = parser.parse();
        this.description = command.desc();
    }

    public String getName() {
        return name;
    }

    public String[] getAliases() {
        return aliases;
    }


    public boolean isAnnotationPresent(Class<? extends Annotation> annotation) {
        boolean found = false;
        for (Annotation anno : method.getAnnotations()) {
            if (anno.annotationType() == annotation) {
                found = true;
            }
        }
        return found;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        if (usage == null) usage = getName() + " " + new UsageBuilder(parameters).build();
        return usage;
    }

    public boolean call(Object sender, Arguments arguments) {
        if (senderType == null) {
            throw new IllegalArgumentException("Sender type is null!");
        }
        if (!senderType.isAssignableFrom(sender.getClass())) {
            throw new IllegalArgumentException("Object provided is not assignable from the sender provided");
        }
        ParametricParser pParser = new ParametricParser(parameters);
        ParametricParser.ParsedResult parsedObj = pParser.parse(senderType.cast(sender), arguments);
        boolean isStatic = Modifier.isStatic(method.getModifiers());

        if (!parsedObj.isSuccessful()) {
            return false;
        }

        if (isStatic) {
            try {
                method.invoke(null, parsedObj.getObjects());
                return true;
            } catch (IllegalAccessException | InvocationTargetException e) {
                return false;
            }
        } else {
            try {
                Object instance = method.getDeclaringClass().newInstance();
                method.invoke(instance, parsedObj.getObjects());
                return true;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                return false;
            }
        }
    }

}
