package jsonexample.domain;

public class School {
  private String name;
  private String address;

  public School(String name, String address) {
    this.name = name;
    this.address = address;
  }

  public String name() {
    return name;
  }

  public String address() {
    return address;
  }

  public String toString() {
    return name + " " + address;
  }
}
