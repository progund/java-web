package student.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

public class XMLOrJson extends HttpServlet {
  
  private String format;
  private static final String E_FORMAT_PARAM_MISSING =
    "Missing parameter format";
  private static final String E_FORMAT_NOT_SUPPORTED =
    "Format not supported";
  private static final String E_MSG_FORMAT_NOT_SUPPORTED =
    "Unknown format";

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    
    // Log who's getting this request:
    String when = "" + new Date();
    System.out.println(when + " doGet() in: " + this.getClass().getName());
    System.out.flush();

    format = request.getParameter("format");
    if (format == null) {
      error(E_FORMAT_PARAM_MISSING, response);
      System.out.println(when + " Missing parameter format.");
    } else if (format.equals("xml")) {
      System.out.println(when + "Dispatching to xmldummy servlet...");
      request.getRequestDispatcher("xmldummy")
        .forward(request, response); // dispatch
    } else if (format.equals("json")) {
      when = "" + new Date();
      System.out.println("Dispatching to jsondummy servlet...");
      request.getRequestDispatcher("jsondummy")
        .forward(request, response); // dispatch
    } else {
      when = "" + new Date();
      System.out.println(when + " " + E_MSG_FORMAT_NOT_SUPPORTED);
      error(E_FORMAT_NOT_SUPPORTED, response);
    }
  }
  
  private void error(String msg, HttpServletResponse response)
    throws IOException {

    response.setContentType("text/plain");
    response.setStatus(SC_BAD_REQUEST);
    response.setCharacterEncoding(UTF_8.name());
    
    PrintWriter out = response.getWriter();
    out.println(msg);
    out.close();	    
  }
}
