package student.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class JsonDummy extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
	
    String when = "" + new Date();
    System.out.println(when + " doGet() in: " + this.getClass().getName());

    response.setContentType("application/json");
    response.setCharacterEncoding(UTF_8.name());
    PrintWriter out = response.getWriter();
    
    StringBuilder json = new StringBuilder();
    json.append("{\"json-reply-from\":\"")
      .append(this.getClass().getName())
      .append("\"}");
    out.println(json);
    //response.setContentLength(json.length()+1);
    out.close();	    
  }
}
