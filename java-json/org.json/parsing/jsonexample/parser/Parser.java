package jsonexample.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

import jsonexample.domain.Substitute;
import jsonexample.domain.School;
import jsonexample.domain.Schedule;

public class Parser {
  private static final String JSON_FILE = "2018-01-17.json";

  private static String JSONfromFile() throws IOException {
    StringBuilder sb = new StringBuilder();
    String ls = System.getProperty("line.separator");
    BufferedReader reader = new BufferedReader(new FileReader (JSON_FILE));
    String line;
    while((line = reader.readLine()) != null) {
      sb.append(line);
      sb.append(ls);
    }
    reader.close();
    return sb.toString();
  }

  public static List<Schedule> parse() {
    List<Schedule> schedules = new ArrayList<>();
    try {
      JSONTokener jt = new JSONTokener(JSONfromFile());
      JSONArray scheduleArray = new JSONArray(jt);
      for(int i=0;i<scheduleArray.length();i++){
        JSONObject schedule = scheduleArray.getJSONObject(i);
        String date = schedule.getString("date");
        JSONObject substituteJSON = schedule.getJSONObject("substitute");
        JSONObject schoolJSON = schedule.getJSONObject("school");
        Substitute substitute = new Substitute(substituteJSON.getString("name"));
        School school = new School(schoolJSON.getString("school_name"),
                                   schoolJSON.getString("address"));
        Schedule currentSchedule = new Schedule(date, substitute, school);
        schedules.add(currentSchedule);
      }
    } catch (IOException|JSONException e) {
      System.err.println("Parse error: " + e.getMessage());
      e.printStackTrace();
    }
    return schedules;
  }
}
