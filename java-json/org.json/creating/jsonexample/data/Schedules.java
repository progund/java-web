package jsonexample.data;

import java.util.ArrayList;
import java.util.List;

import jsonexample.domain.Schedule;
import jsonexample.domain.Substitute;
import jsonexample.domain.School;

public class Schedules {
  public static List<Schedule> getSchedules() {
    List<Schedule> schedules = new ArrayList<>();
    Substitute rikard = new Substitute("Rikard");
    Substitute henrik = new Substitute("Henrik");
    Substitute anders = new Substitute("Anders");
    School yrgo = new School("Yrgo", "Lärdomsgatan 3 402 72 GÖTEBORG");
    School angered = new School("Angeredsgymnasiet", "Grepgatan 9 424 65 Angered");
    String date = "2018-01-17 08:00:00";
    Schedule schedule1 = new Schedule(date, rikard, yrgo);
    Schedule schedule2 = new Schedule(date, henrik, yrgo);
    Schedule schedule3 = new Schedule(date, anders, angered);
    schedules.add(schedule1);
    schedules.add(schedule2);
    schedules.add(schedule3);
    return schedules;
  }
}
