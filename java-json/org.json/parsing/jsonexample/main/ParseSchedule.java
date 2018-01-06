package jsonexample.main;

import java.util.List;

import jsonexample.domain.Schedule;
import jsonexample.parser.Parser;

public class ParseSchedule {
  public static void main(String[] args) {
    List<Schedule> schedules = Parser.parse();
    schedules.stream().forEach(System.out::println);
  }
}
