package org.henrikard.student.formats;
import org.henrikard.student.domain.Student;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.JsonWriterFactory;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;


class JsonFormatter implements Formatter{
    private static JsonFormatter instance;
    static{
        instance = new JsonFormatter();
        FormatterFactory.registerFormatter("json", instance);
    }

    private String document;

    private JsonFormatter(){
        
    }
    public void loadFromList(List<Student> list){
        StringBuilder page = new StringBuilder();
        Map<String, Boolean> config = new HashMap<String, Boolean>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        StringWriter writer = new StringWriter();
        JsonWriterFactory jwf = Json.createWriterFactory(config);
        JsonWriter jWriter = jwf.createWriter(writer);
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for( Student student : list ){
            jab.add(Json.createObjectBuilder()
                    .add("studentName", student.name())
                    .add("studentID", student.id()));
        }
        job.add("students", jab.build());
        jWriter.writeObject(job.build());
        jWriter.close();
        page.append(writer.toString());
        this.document = page.toString();
    }
    public String getDocument(){
        return document;
    }
    public String getContentType(){
        return "application/json";
    }
}
