
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class DBServlet extends HttpServlet {

  public void init() throws ServletException {
    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException cnfe) {
      System.err.println("Could not load driver: " + cnfe.getMessage());
    }
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    response.setCharacterEncoding(UTF_8.name());
    PrintWriter out = response.getWriter();

    out.println("<html><head><title>Example servlet</title></head>");
    out.println("<body>");

    try {

      Connection con = DriverManager.getConnection("jdbc:sqlite:students.db");
      Statement stm = con.createStatement();
      ResultSet rs = stm.executeQuery("SELECT name FROM students");

      while (rs.next()) {
        out.print(rs.getString("name"));
        out.println("<br />");
      }

    } catch (SQLException sqle) {
      out.println("Database error: " + sqle.getMessage());
    }
    
    out.println("</body>");
    out.println("</html>");
    out.close();    
  }
}
