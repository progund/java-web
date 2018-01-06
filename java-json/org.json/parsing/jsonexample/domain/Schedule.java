package jsonexample.domain;

public class Schedule {
  private String date;
  private Substitute substitute;
  private School school;

  public Schedule(String date, Substitute substitute, School school) {
    this.date = date;
    this.substitute = substitute;
    this.school = school;
  }
  
  public String date() {
    return date;
  }

  public Substitute substitute() {
    return substitute;
  }

  public School school() {
    return school;
  }

  public String toString() {
    return date + " " + substitute + " " + school;
  }
}
