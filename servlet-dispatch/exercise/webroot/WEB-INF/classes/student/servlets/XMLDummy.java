package student.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.IOException;
import java.util.Date;

public class XMLDummy extends HttpServlet{
    private String format;
    private static final String E_FORMAT_PARAM_MISSING = "Missing parameter format";
    private static final String E_FORMAT_NOT_SUPPORTED = "Missing parameter format";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	              throws ServletException, IOException{
	String when = "" + new Date();
	System.out.println(when + " doGet() in: " + this.getClass().getName());
	response.setContentType("application/xml;charset=UTF-8");
	PrintWriter out = 
	    new PrintWriter(new OutputStreamWriter(response.getOutputStream()
						   , UTF_8), true);
	out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
	out.println("<MESSAGE>"+this.getClass().getName()+"</MESSAGE>");
	out.close();	    
    }
}
