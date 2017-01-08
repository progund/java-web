package util;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class JSONProducer{
  private File jsonFile;
  private PrintWriter writer;
    
  public JSONProducer(String jsonFile){
    this.jsonFile = new File(jsonFile);
  }
    
  // example method - shows how a json obj is created
  public void createAndWrite() throws IOException{
    this.writer=new PrintWriter(jsonFile);
    Map<String, Boolean> config = new HashMap<>();
    config.put(JsonGenerator.PRETTY_PRINTING, true);
    JsonWriterFactory jwf = Json.createWriterFactory(config);
    JsonWriter jWriter = jwf.createWriter(writer);
    JsonObject jo = Json.createObjectBuilder()
      .add("firstName","Hanky")
      .add("lastName","Sandycleavage")
      .add("age",65)
      .add("streetAddress","Skidrow 88")
      .add("State","VGR")
      .add("postalCode","66613")
      .add("phoneNumbers", Json.createArrayBuilder()
           .add(Json.createObjectBuilder()
                .add("Mobile", "0700123321"))
           .add(Json.createObjectBuilder()
                .add("Home", "031-90 51 06")))
      .build();
    jWriter.writeObject(jo);
    jWriter.close();
  }
}
