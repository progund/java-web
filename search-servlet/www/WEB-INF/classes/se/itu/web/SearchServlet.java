package se.itu.web;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.*; // IOException, BufferedReader, File...
import java.nio.file.*; //Files, Paths;


public class SearchServlet extends HttpServlet {

  private static String webroot;

  public void init() {
    webroot = getServletContext().getRealPath("");
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    request.setCharacterEncoding(UTF_8.name());
    response.setContentType("text/html;charset="+UTF_8.name());
    PrintWriter out =
      new PrintWriter(new OutputStreamWriter(response.getOutputStream(),
                                             UTF_8), true);
    out.println("<!DOCTYPE html>");
    out.println("<html lang=\"en\">");
    out.println("<head><title>SearchResults</title></head>");
    out.println("<body>");
    out.println("<h1>Results</h1>");

    String searchWord = request.getParameter("search_word");    
    String results = getSearchResultsHtml(searchWord);
    if (results.length() == 0) {
      results = "No matches for " + searchWord;
    }
    
    out.println(results);
    out.println("</body>");
    out.println("</html>");
  }

  private String getSearchResultsHtml(String searchWord) {

    if (searchWord == null || searchWord.equals("")) {
      return "No search word given";
    }

    Path root = Paths.get(webroot);
    StringBuilder results = new StringBuilder();

    try (DirectoryStream<Path> dir =
         Files.newDirectoryStream(root, "*.html")) {
      for (Path file : dir){
        if (Files.lines(file).anyMatch(line -> line.toLowerCase()
                                       .contains(searchWord.toLowerCase()))) {
                       results.append("<a href=\"");
                       results.append(file.getFileName());
                       results.append("\">");
                       results.append(file.getFileName());
                       results.append("</a><br>\n");
        }
      }
    } catch (Exception e) {
      System.err.println("error: " + e.getMessage());
    }
    
    return results.toString();
  }
}
