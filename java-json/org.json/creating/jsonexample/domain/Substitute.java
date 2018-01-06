package jsonexample.domain;

public class Substitute {
  private String name;

  public Substitute(String name) {
    this.name = name;
  }

  public String name() {
    return name;
  }

  public String toString() {
    return name;
  }
}
