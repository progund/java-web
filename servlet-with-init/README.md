# A servlet with an `init()` method

This example shows how one could use the `init()` method of a servlet. The method in the example reads an init parameter (set in `web.xml`) for a language and sets the instance variable `language` to this value and prints some debug information.

## How to run the example
```bash
$ javac -cp winstone.jar www/WEB-INF/classes/TimeServlet.java
$ java -jar winstone.jar --webroot=www
```

Access the servlet via [localhost:8080/time](http://localhost:8080/time) .

## What the code does
The `init()` method reads a config parameter (set in web.xml) and assigns the value to an instance variable:
```java
language = config.getInitParameter("language");
```

In the `doGet()` method, we check whether the variable is set to `SW` in which case we initialize a `ZonedDateTime` instance variable to be of the time zone "Europe/Paris". Otherwise we initialize it to be of the time zone "America/New_York".

The point of this stupid and simple servlet is to show what you can do inside the init method, how to use init parameters in the web.xml and to show that the init method is called only once by the container.

## Read more
[Java API doc for Servlet.init()](https://javaee.github.io/javaee-spec/javadocs/javax/servlet/Servlet.html#init-javax.servlet.ServletConfig-)
