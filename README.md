This branch is currently deprecated. Please see the development branch for the most up to date version
=====

# Mojo
Mojo is a command framework for Java.


```java
  @Command(name = "example")
  public void example(CommandContext<Actor> ctx, @Bind("age") Integer i) {
       ctx.sender().setAge(i);
       ctx.respond("You are " + ctx.sender().getAge() + " year(s) old");
  }
```
