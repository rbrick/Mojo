package me.rbrickis.mojo.utils;

public final class PrimitiveUtils {

    public static Object toPrimitiveNumber(Number number) {
        if (number instanceof Byte) {
            return number.byteValue();
        } else if (number instanceof Long) {
            return number.longValue();
        } else if (number instanceof Short) {
            return number.shortValue();
        } else if (number instanceof Integer) {
            return number.intValue();
        } else if (number instanceof Float) {
            return number.floatValue();
        } else if (number instanceof Double) {
            return number.doubleValue();
        }
        return 0;
    }

    public static Object toPrimitiveBoolean(Boolean booleans) {
        return booleans.booleanValue();
    }
}
