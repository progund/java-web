import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.IOException;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class TimeServlet extends HttpServlet {

  private String language;
  private ZonedDateTime dt;

  /*
   * https://javaee.github.io/javaee-spec/javadocs/javax/servlet/Servlet.html#init-javax.servlet.ServletConfig-
   */
  @Override
  public void init(ServletConfig config) throws ServletException {
    System.out.println("This is the init() method.");
    System.out.println("It will be run only once.");
    System.out.println(config.getInitParameter("language"));
    language = config.getInitParameter("language");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    PrintWriter out =
      new PrintWriter(new OutputStreamWriter(response.getOutputStream(),
                                             UTF_8), true);
    
    if (language != null && language.equals("SW")) {
      dt = ZonedDateTime
        .of(LocalDateTime.now(), ZoneId.of("Europe/Paris"));
    } else {
      dt = ZonedDateTime
        .of(LocalDateTime.now(), ZoneId.of("America/New_York"));
    }
    out.println(dt.toString());
    out.close();
  }
}
