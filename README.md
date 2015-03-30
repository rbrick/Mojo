# Mojo
annotated command system, Zeus 2.0 pretty much

```java
  @Command(name = "example")
  public void example(CommandContext<Actor> ctx, @Bind("age") Integer i) {
       ctx.sender().setAge(i);
       ctx.respond("You are " + ctx.sender().getAge() + " year(s) old");
  }
```
