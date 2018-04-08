package servlets;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Courses extends HttpServlet {

  @Override
  public void init() throws ServletException {
    try {
      Class.forName("org.sqlite.JDBC");
    } catch(ClassNotFoundException cnfe) {
      System.err.println("Could not load driver: "+cnfe.getMessage());
    }
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    response.setCharacterEncoding(UTF_8.name());
    PrintWriter out = response.getWriter();

    try {
      Connection con = DriverManager.getConnection("jdbc:sqlite:courses.db");
      Statement stm  = con.createStatement();
      ResultSet rs   = stm.executeQuery("SELECT * FROM courses");

      StringBuilder sb = new StringBuilder("<!DOCTYPE html>\n")
        .append("<html>\n")
        .append("<head>\n")
        .append("  <title>Hello world servlet!</title>\n")
        .append("</head> \n")
        .append("<body>\n")
        .append("<ul>\n");

      while (rs.next()) {
        sb.append("<li>")
          .append(rs.getString("course_code"))
          .append(" - ")
          .append(rs.getString("course_name"))
          .append("</li>\n");
      }

      sb.append("</ul>\n")
        .append("</body>\n")
        .append("</html>\n");

      out.println(sb.toString());

    } catch (SQLException sqle) {
      out.println("<em style=\"color: red;\">Database error: " +
                  sqle.getMessage() +
                  "</em>");
    }
    out.close();
  }
}
