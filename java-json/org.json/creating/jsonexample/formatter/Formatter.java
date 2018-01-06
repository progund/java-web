package jsonexample.formatter;

import java.util.List;

import org.json.*;

import jsonexample.domain.Schedule;
import jsonexample.domain.School;
import jsonexample.domain.Substitute;

public class Formatter {

  private static final String JSON_FILE = "2018-01-17.json";

  private static JSONObject JSONSchedule(Schedule schedule) {
    JSONObject JSONSchedule = new JSONObject();
    JSONSchedule.put("date", schedule.date());
    JSONObject JSONSubstitute = new JSONObject();
    JSONSubstitute.put("name", schedule.substitute().name());
    JSONSchedule.put("substitute", JSONSubstitute);
    JSONObject JSONSchool = new JSONObject();
    JSONSchool.put("school_name", schedule.school().name());
    JSONSchool.put("address", schedule.school().address());
    JSONSchedule.put("school", JSONSchool);
    return JSONSchedule;
  }
  
  public static String format(List<Schedule> schedules) {

    JSONArray JSON = new JSONArray();
    for (Schedule schedule : schedules) {
      JSON.put(JSONSchedule(schedule));
    }
    return JSON.toString(2);
  }
}
