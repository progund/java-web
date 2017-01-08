package util;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import org.json.*;

/*
 * Using org.JSON from:
 * android-json.jar
 */


public class JSONParser{
  private String jsonFile;
  public JSONParser(String jsonFile){
    this.jsonFile = jsonFile;
  }
  public void parseAndPrint()throws IOException{
    StringBuilder sb = new StringBuilder();
    String        ls = System.getProperty("line.separator");
    BufferedReader reader = new BufferedReader(new FileReader (jsonFile));
    String line;
    while((line = reader.readLine()) != null) {
      sb.append(line);
      sb.append(ls);
    }
    reader.close();
    
    
    try{
      JSONTokener jt = new JSONTokener(sb.toString());
      JSONObject jo = new JSONObject(jt);
      System.out.println("First name: " + jo.getString("firstName"));
      System.out.println("Last name: " + jo.getString("lastName"));
      System.out.println("Age: " + jo.getInt("age"));
      System.out.println("Street address: " + jo.getString("streetAddress"));
      System.out.println("City: " + jo.getString("city"));
      System.out.println("State: " + jo.getString("state"));
      System.out.println("Postal code: " + jo.getString("postalCode"));
      JSONArray arr = jo.getJSONArray("phoneNumbers");
      System.out.println("Phone numbers:");
      for(int i=0;i<arr.length();i++){
        jo = arr.getJSONObject(i);
        if(jo.has("Mobile") && !jo.isNull("Mobile")){
          System.out.println("Mobile: " + jo.get("Mobile"));
        }
        if(jo.has("Home") && !jo.isNull("Home")){
          System.out.println("Home: " + jo.get("Home"));
        }
      }
    }catch(JSONException e){
      System.err.println("Couldn't parse: " + e.getMessage());
    }
  }
}
/*
  {
    "firstName": "Duke",
    "lastName": "Java",
    "age": 18,
    "streetAddress": "100 Internet Dr",
    "city": "JavaTown",
    "state": "JA",
    "postalCode": "12345",
    "phoneNumbers": [
      {
        "Mobile": "111-111-1111"
      },
      {
         "Home": "222-222-2222"
      }
    ]
  }
*/
