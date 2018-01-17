package org.ux;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class NextBus extends HttpServlet {

  private String message;
  private Map<String, Map<String,String>>timeTable;

  public void init() throws ServletException {
    // Do initiation here...
    timeTable = new HashMap<String, Map<String,String>>();
    loadTimeTable();
  }
  
  private void loadTimeTable() {
    Map<String,String> direction1 = new HashMap<String,String>();
    Map<String,String> direction2 = new HashMap<String,String>();

    for (int i = 0; i<20; i++) {
      direction1.put(""+i, ""+(i+3));
      direction2.put(""+i, ""+(i+2));
    }
    
    timeTable.put("1", direction1);
    timeTable.put("2", direction2);
  }
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    response.setContentType("text/plain");
    PrintWriter out = response.getWriter();
    StringBuilder page = new StringBuilder(253);
    String stopID = request.getParameter("stop_id");
    String direction = request.getParameter("direction");
    getTime(stopID, direction, page);
    out.println(page);
    out.close();
  }
  
  private void getTime(String stopID, String direction, StringBuilder page) {
    Map<String,String> dirMap = timeTable.get(direction);
    
    if (dirMap == null) {
      page.append("Unknown direction: "+direction+
                  ". Please use 1 or 2");	    
    } else {
      String timeToBus = dirMap.get(stopID);

      if (timeToBus == null) {
        page.append("Unknown stop_id: " + stopID + 
                    ".");
      } else {
        page.append(dirMap.get(stopID));
      }
    }
  }
  
  public void destroy() {
    // cleanup etc here...
  }
}
