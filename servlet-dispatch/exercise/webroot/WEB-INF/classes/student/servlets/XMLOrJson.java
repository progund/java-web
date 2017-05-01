package student.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Date;

public class XMLOrJson extends HttpServlet{
    private String format;
    private static final String E_FORMAT_PARAM_MISSING = "Missing parameter format";
    private static final String E_FORMAT_NOT_SUPPORTED = "Format not supported";
    private static final String E_MSG_FORMAT_NOT_SUPPORTED = "Unknown format";
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	              throws ServletException, IOException{
	// Log who's getting this request:
	String when = "" + new Date();
	System.out.println(when + " doGet() in: " + this.getClass().getName());
	System.out.flush();
	format = request.getParameter("format");
	if(format == null){
	    error(E_FORMAT_PARAM_MISSING, response);
	    System.out.println(when + " Missing parameter format.");
	}else if(format.equals("xml")){
	    System.out.println(when + "Dispatching to xmldummy servlet...");
            // Dispatch to XMLDummy here!
	}else if (format.equals("json")){
	    when = "" + new Date();
	    System.out.println("Dispatching to jsondummy servlet...");
            // Dispatch to JsonDummy here!
	}else{
	    when = "" + new Date();
	    System.out.println(when + " " + E_MSG_FORMAT_NOT_SUPPORTED);
	    error(E_FORMAT_NOT_SUPPORTED, response);
	}
    }
    private void error(String msg, HttpServletResponse response)throws IOException{
	response.setContentType("text/plain");
	response.setStatus(SC_BAD_REQUEST);
	PrintWriter out = response.getWriter();
	out.println(msg);
	out.close();	    
    }
}
