package me.rbrickis.test;

public class Actor {

    private String name;

    private int age;

    public Actor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sendMessage(String message) {
        System.out.println("To " + getName() + " -> " + message);
    }
}
