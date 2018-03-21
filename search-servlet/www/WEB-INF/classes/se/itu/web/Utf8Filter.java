package se.itu.web;

import javax.servlet.*;
import javax.servlet.http.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.IOException;

public final class Utf8Filter implements Filter {
  public void doFilter(ServletRequest request,
                       ServletResponse response, FilterChain chain) throws
                         IOException, ServletException {
    String encoding = UTF_8.name();
    request.setCharacterEncoding(encoding);
    chain.doFilter(request, response);
  }

  public void init(FilterConfig filterConfig)
    throws ServletException {
  }

  public void destroy() {
  }
}
