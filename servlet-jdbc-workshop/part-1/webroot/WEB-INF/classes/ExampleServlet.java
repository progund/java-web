import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ExampleServlet extends HttpServlet {
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    PrintWriter out =
      new PrintWriter(new OutputStreamWriter(response.getOutputStream(),
                                             UTF_8), true);

    response.setContentType("text/html;charset=" + UTF_8);
    out.println("<html><head><title>Example servlet</title></head>");
    out.println("<body><p>Hello from a servlet!</body>");
    out.println("</html>");

    out.close();    
  }
}
