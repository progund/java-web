import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Small and rather lame example of a servlet to be used via Ajax
 * to give suggestions based on the letters of a name the user types
 * in to a text field.
 *
 * <p>In this small example, the names are hard coded into an array of String.
 */
public class HintServlet extends HttpServlet {
  private String message;
  private String[] names = {
    "Anna",
    "Beata",
    "Bengt",
    "Cecilia",
    "Christian",
    "Delilah",
    "David",
    "Elsa",
    "Edwin",
    "Fia",
    "Fred",
    "Gina",
    "George",
    "Hedvig",
    "Henrik",
    "Isabella",
    "Ingvar",
    "Joanna",
    "Johan",
    "Klara",
    "Keith"
  };
  
  public void init() throws ServletException {
    // Do initiation here...
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/plain");
    PrintWriter out = response.getWriter();
    StringBuilder page = new StringBuilder(253);
    String searchParam = request.getParameter("q");
    if (searchParam == null) {
      out.close();
      return;
    }
    String hint = getHint(searchParam.toLowerCase());
    page.append(hint);
    out.println(page);
    response.setContentLength(page.length() + 1);
  }

  /* Ported from PHP from the code found here:
   * http://www.w3schools.com/ajax/ajax_php.asp
   */
  private String getHint(String searchParam) {
    if (searchParam == null || searchParam.equals("")) {
      return "";
    }
    String hint = "";
    int length  = searchParam.length();
    for (String name : names) {
      if (name.toLowerCase().startsWith(searchParam)) {
        hint = hint.equals("") ? name : hint + ", " + name;
      }
    }
    return hint.equals("") ? "No suggestion" : hint;
  }
  
  public void destroy() {
    // cleanup etc here...
  }
}
