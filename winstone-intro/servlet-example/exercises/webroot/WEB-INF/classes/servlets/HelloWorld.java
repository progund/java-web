package servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.IOException;

public class HelloWorld extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    response.setCharacterEncoding(UTF_8.name());
    PrintWriter out = response.getWriter();

    StringBuilder sb = new StringBuilder("<!DOCTYPE html>\n")
      .append("<html>\n")
      .append("<head>\n")
      .append("  <title>Hello world servlet!</title>\n")
      .append("</head> \n")
      .append("<body>\n")
      .append("<p style=\"color: red;\">\n")
      .append("Hello World!\n")
      .append("</p>\n")
      .append("</body>\n")
      .append("</html>\n");
    out.println(sb.toString());
    out.close();
  }
}
