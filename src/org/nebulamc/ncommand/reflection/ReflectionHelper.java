package org.nebulamc.ncommand.reflection;

import java.lang.reflect.Field;

/**
 * Created by Ryan on 1/30/2015
 * <p/>
 * Project: nCommand
 */
public class ReflectionHelper {

    public static Field getPrivateField(Class<?> clz, String fName) {
         try {
             Field f = clz.getDeclaredField(fName);
             f.setAccessible(true);
             return f;
         } catch (Exception ex) {
             ex.printStackTrace();
             return null;
         }
    }

    public static Field getPublicField(Class<?> clz, String fName) {
        try {
            Field f = clz.getField(fName);
            f.setAccessible(true);
            return f;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
