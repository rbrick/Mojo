package me.rbrickis.mojo.utils.reflection;

import java.lang.reflect.Field;

public class SafeField<T> {

    private Field field;

    private SafeClass<?> owner;

    public SafeField(SafeClass<?> owner, String name) {
        this.owner = owner;
        try {
            this.field = this.owner.getClazz().getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    public T get() {
        try {
            field.setAccessible(true);
            return (T) field.get(owner.get());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Field getField() {
        field.setAccessible(true);
        return field;
    }

    public SafeClass<?> getOwner() {
        return owner;
    }
}
