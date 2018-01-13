package student.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.*;

public class DataServlet extends HttpServlet {

  public void init() throws ServletException {
    // Do initiation here...
  }
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    StringBuilder page = new StringBuilder(253);
    String format = request.getParameter("format");
    if (format != null && format.equals("json")) {
      response.setContentType("application/json");
      writeJSON(page);
    } else if (format != null && format.equals("csv")) {
      response.setContentType("text/csv");
      writeCSV(page);
    } else if (format != null && format.equals("xml")) {
      response.setContentType("application/xml");
      writeXML(page);
    } else if (format == null) {
      response.setContentType("text/html");
      page
        .append("<html><body><h1>Missing parameter: format</h1></body></html>");
    } else {
      response.setContentType("text/html");
      page.append("<html><body><h1>Unknown format: " + format + "</h1></body></html>");
    }
    PrintWriter out = response.getWriter();
    out.println(page);
    out.close();
  }

  private void writeXML(StringBuilder page) {
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
  
  private void writeJSON(StringBuilder page) {
    
    JSONObject jo = new JSONObject();
      jo.put("firstName","Hanky")
        .put("lastName","Sandycleavage")
        .put("age",65)
        .put("streetAddress","Skidrow 88")
        .put("State","VGR")
        .put("postalCode","66613")
        .accumulate("phoneNumbers", new JSONObject().put("Mobile", "0700123321"))
        .accumulate("phoneNumbers", new JSONObject().put("Home", "031-90 51 06"));
      /*
      .put("phoneNumbers", Json.createArrayBuilder()
           .add(Json.createObjectBuilder()
                .add("Mobile", "0700123321"))
           .add(Json.createObjectBuilder()
                .add("Home", "031-90 51 06")))
      .build();
    jWriter.writeObject(jo);
    jWriter.close();
      */
    page.append(jo.toString(2));
  }
  
  private void writeCSV(StringBuilder page) {
    page.append("firstName,lastName,age,streetAddress,State,postalCode,Mobile,Home\n");
    page.append("Hanky,Sandycleavage,65,Skidrow 88,VGR,66613,0700123321,031-90 51 06");
  }
  
  public void destroy() {
    // cleanup etc here...
  }
}
