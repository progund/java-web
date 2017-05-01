package student.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

public class DataServlet extends HttpServlet{
  public void init() throws ServletException{
    // Do initiation here...
  }
  public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    StringBuilder page = new StringBuilder(253);
    String format = request.getParameter("format");
    if(format!=null && format.equals("json")){
      response.setContentType("application/json");
      writeJSON(page);
    }else if(format!=null && format.equals("csv")){
      response.setContentType("text/csv");
      writeCSV(page);
    }else if(format!=null && format.equals("xml")){
      response.setContentType("application/xml");
      writeXML(page);
    }else if(format==null){
      response.setContentType("text/html");
      page.append("<html><body><h1>Missing parameter: format</h1></body></html>");
    }else{
      response.setContentType("text/html");
      page.append("<html><body><h1>Unknown format: "+format+"</h1></body></html>");
    }
    PrintWriter out = response.getWriter();
    out.println(page);
    out.flush();
  }
  private void writeXML(StringBuilder page){
    page.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n")
      .append("<address>\n")
      .append(" <first-name>Hanky</first-name>\n")
      .append(" <last-name>Sandycleavage</last-name>\n")
      .append(" <age>65</age>\n")
      .append(" <street-address>Skidrow 88</street-address>\n")
      .append(" <state>VGR</state>\n")
      .append(" <phone-numbers>\n")
      .append("  <mobile>0700123321</mobile>\n")
      .append("  <home>031-90 51 06</home>\n")
      .append(" </phone-numbers>\n")
      .append("</address>\n");
  }
  private void writeJSON(StringBuilder page){
    StringWriter writer = new StringWriter();
    JsonWriter jWriter = Json.createWriter(writer);
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
    page.append(writer.toString());
  }
  private void writeCSV(StringBuilder page){
    page.append("firstName,lastName,age,streetAddress,State,postalCode,Mobile,Home\n");
    page.append("Hanky,Sandycleavage,65,Skidrow 88,VGR,66613,0700123321,031-90 51 06");
  }
  public void destroy(){
    // cleanup etc here...
  }
}
