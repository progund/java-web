package student.servlets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class UtfFilter implements Filter {

  private String encoding = "";
  public void doFilter(ServletRequest request,
                       ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    response.setCharacterEncoding(encoding);
    filterChain.doFilter(request, response);
    System.out.println("Filter is running, setting char.encoding. to "+encoding);
  }

  public void init(FilterConfig filterConfig) throws ServletException {
    String encodingParam = filterConfig.getInitParameter("encoding");
    if (encodingParam != null) {
      encoding = encodingParam;
    }
  }

  public void destroy() {
    // nothing todo
  }
}
