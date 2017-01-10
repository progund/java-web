import java.io.*;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonValue;
import javax.json.JsonObject;
import static javax.json.JsonValue.ValueType.*;
import javax.json.JsonReader;
import javax.json.JsonStructure;

public class JavaxMain{

  public static void main(String[] args){
    try{
      parseAndPrint(args[0]);
    }catch(FileNotFoundException fnfe){
      System.err.println("File not found: " + fnfe.getMessage());
    }
  }
  
  public static void parseAndPrint(String jsonFile)throws FileNotFoundException{
    try(JsonReader reader = Json.createReader( new FileReader(jsonFile) );){      
      JsonStructure jsonStruct = reader.read();
      System.out.println(jsonStruct);
      if(jsonStruct.getValueType().equals(OBJECT)){
        JsonObject jo = (JsonObject) jsonStruct;
        System.out.println("First name: " + jo.getString("firstName"));
        System.out.println("Last name: " + jo.getString("lastName"));
        System.out.println("Age: " + jo.getInt("age"));
        System.out.println("Street address: " + jo.getString("streetAddress", "No st. addr."));
        System.out.println("City: " + jo.getString("city", "No city"));
        System.out.println("State: " + jo.getString("state", "No state"));
        System.out.println("Postal code: " + jo.getString("postalCode", "No postal code"));
        JsonArray arr = jo.getJsonArray("phoneNumbers");
        System.out.println("Phone numbers:");
        for(JsonValue jv : arr){
          /* JsonObject is a Map - check if it has the correct key */
          if( ((JsonObject)jv).keySet().contains("Mobile")){
            String mobile = ((JsonObject)jv).getString("Mobile");
            if(mobile!=null){
              System.out.println(" Mobile: " + mobile);
            }
          }else if(((JsonObject)jv).keySet().contains("Home")){
            String home   = ((JsonObject)jv).getString("Home");
            if(home!=null){
              System.out.println(" Home: " + home);
            }
          }
        }
      }
    }catch(FileNotFoundException fnfe){
      throw new FileNotFoundException("jsonFile");
    }
  }
}
