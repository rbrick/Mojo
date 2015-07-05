package me.rbrickis.mojo;

import me.rbrickis.mojo.parametric.MethodParser;
import me.rbrickis.mojo.parametric.ParametricParser;
import me.rbrickis.mojo.parametric.ParametricRegistry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Mojo {

    public static void main(String... args) {
        try {
            Method testParser = Mojo.class.getMethod("testParser", String.class, int.class, boolean.class);
            MethodParser mParser = new MethodParser(testParser, new ParametricRegistry());
            ParametricParser pParser = new ParametricParser(mParser.parse());
            Object[] objs = pParser.parse();

            testParser.invoke(null, objs);

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public static void testParser(String hello, int favoriteNumber, boolean likesPuppies) {
        System.out.println(hello +
                        ", my favorite number is " + favoriteNumber + " and " + likesPuppies + ", i "
                        + (likesPuppies ? "like" : "do not like") + " puppies"
        );
    }

}
