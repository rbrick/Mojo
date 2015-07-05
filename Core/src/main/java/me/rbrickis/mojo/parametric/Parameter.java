package me.rbrickis.mojo.parametric;

public class Parameter {

    private Class<?> type;
    private String passed;

    public Parameter(Class<?> type, String passed) {
        this.type = type;
        this.passed = passed;
    }


    public Class<?> getType() {
        return type;
    }

    public String getPassed() {
        return passed;
    }

    public Object parse() {
        return null;
    }
}
