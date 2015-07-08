package me.rbrickis.mojo.command;

import me.rbrickis.mojo.parametric.Parameter;

import java.util.List;

public class UsageBuilder {

    private List<Parameter> parameters;

    public UsageBuilder(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public String build() {
        StringBuilder builder = new StringBuilder();
        for (Parameter parameter : parameters) {
             boolean isContinuous = parameter.hasText();
             boolean isOptional = parameter.hasDefault();
             String pattern = (isOptional ? "[%s]" : "<%s>");
             String name = (isContinuous ? parameter.getName() + "..." : parameter.getName());
             builder.append(String.format(pattern, name)).append(' ');
        }
        return builder.toString();
    }


}
