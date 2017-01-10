import org.json.*;
import java.io.*;

public class OrgJsonMain{
  public static void main(String[] args){
    try{
      String json = getJsonAsString(args[0]);
      JSONTokener jt = new JSONTokener(json);
      JSONObject jo = new JSONObject(jt);
      JSONArray ja = jo.getJSONArray("students");
      for(int i = 0;i<ja.length();i++){
        jo=ja.getJSONObject(i);
        System.out.println("Name: " + jo.getString("studentName"));
        System.out.println("ID: "   + jo.getInt("studentID"));
      }
    }catch(IOException ioe){
      System.err.println("Couldn't read file: " + ioe.getMessage());
    }catch(JSONException jsone){
      System.err.println("Couldn't parse JSON: " + jsone.getMessage());
    }
  }
  private static String getJsonAsString(String file) throws IOException{
    StringBuilder sb = new StringBuilder();
    String        ls = System.getProperty("line.separator");
    BufferedReader reader = new BufferedReader(new FileReader (file));
    String line;
    while((line = reader.readLine()) != null) {
      sb.append(line);
      sb.append(ls);
    }
    reader.close();
    return sb.toString();
  }
}
