package jsonexample.main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import jsonexample.domain.Schedule;
import jsonexample.formatter.Formatter;
import jsonexample.data.Schedules;

public class CreateSchedule {
  public static void main(String[] args) {
    List<Schedule> schedules = Schedules.getSchedules();
    String JSON = Formatter.format(schedules);
    //System.out.println(JSON);
    writeFile(JSON);
  }

  public static void writeFile(String JSON) {
    try {
      Path jsonFile = Paths.get("2018-01-17.json");
      Files.write(jsonFile, JSON.getBytes(StandardCharsets.UTF_8));
    } catch (IOException ioe) {
      System.err.println("Exception writing JSON file: " + ioe.getMessage());
    }
  }
}
