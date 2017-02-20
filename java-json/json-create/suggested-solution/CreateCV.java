import java.io.File;
import java.util.List;
import java.io.*;

import org.json.*;

import org.yrgo.util.CVFetcher;
import org.yrgo.domain.JobExperience;

public class CreateCV{
  public static void main(String[] args){
    try{
      PrintWriter writer = new PrintWriter("cv.json");
      /* Create a CVFetcher here! */
      CVFetcher fetcher = new CVFetcher();
      JSONObject jo       = new JSONObject();
      /* Loop over each JobExperience you get from the fetcher here */

      for(JobExperience jobExp : fetcher.getJobs()){
        JSONObject duration = new JSONObject();
        JSONObject jsonJob  = new JSONObject();
        jsonJob.put("employer", jobExp.employer());
        duration.put("start"   , jobExp.start()); 
        duration.put("end"   , jobExp.end()); 
        jsonJob.put("duration", duration);
        jsonJob.put("title", jobExp.title());
        jsonJob.put("description"   , jobExp.description()); 
        jo.accumulate("jobs", jsonJob);
      }
      writer.println(jo.toString(2));
      writer.close();
      
    }catch(Exception e){
      System.err.println("Error creating Json: " + e.getMessage());
      e.printStackTrace();
      System.exit(1);
    }
  }
}
