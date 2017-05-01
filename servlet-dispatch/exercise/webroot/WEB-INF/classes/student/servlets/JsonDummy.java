package student.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Date;
public class JsonDummy extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	              throws ServletException, IOException{
	
	String when = "" + new Date();
	System.out.println(when + " doGet() in: " + this.getClass().getName());

	response.setContentType("application/json");
	PrintWriter out = response.getWriter();
	StringBuilder json = new StringBuilder();
	json.append("{\"json-reply-from\":\"")
	    .append(this.getClass().getName())
	    .append("\"}");
	out.println(json);
	response.setContentLength(json.length()+1);
	out.close();	    
    }
}
