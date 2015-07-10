package me.rbrickis.mojo.utils.reflection;

public class SafeClass<T> {

    private Class<T> clazz;

    private T object;

    public SafeClass(T object) {
        this.clazz = (Class<T>) object.getClass();
        this.object = object;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public T get() {
        return object;
    }

    public SafeMethod getMethod(String name, Object... parameters) {
        return new SafeMethod(this, name, parameters);
    }

    public <ST> SafeField<ST> getField(String name) {
          return new SafeField<ST>(this, name);
    }


}
