package util;
import java.io.*;
import org.json.*;
/* Using: android-json.jar (org.json)
 */
public class JSONProducer{
  private File jsonFile;
  private PrintWriter writer;
    
  public JSONProducer(String jsonFile){
    this.jsonFile = new File(jsonFile);
  }
    
  // example method - shows how a json obj is created
  public void createAndWrite() throws IOException{
    try{
      JSONObject jo = new JSONObject();
      jo.put("firstName","Hanky")
        .put("lastName","Sandycleavage")
        .put("age",65)
        .put("streetAddress","Skidrow 88")
        .put("State","VGR")
        .put("postalCode","66613");
      JSONObject mobile = new JSONObject();
      mobile.put("Mobile", "0700123321");
      jo.accumulate("phoneNumbers", mobile);
      JSONObject home = new JSONObject();
      home.put("Home", "031-90 51 06");
      jo.accumulate("phoneNumbers", home);
      String json = jo.toString(2); // indent steps: 2
      // Print the json object to a file
      writer = new PrintWriter(jsonFile);
      writer.println(json);
      writer.close();
    }catch(JSONException e){
      System.err.println("Error creating JSON: " + e.getMessage());
    }
  }
}
