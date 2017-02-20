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

      JSONObject jo       = new JSONObject(); // the JSON object
      JSONObject duration = new JSONObject(); // an element
      /* Loop over each JobExperience you get from the fetcher here */

      /* for each JobExperience, create a JSONObject called jsonJob
         Add the following to the jsonJob object:
         * put "employer" and the data to jsonJob
         * Set the following elements to the duration object:
         ** "start"
         ** "end"
         * put "duration" and the duration object to jsonJob
         * put "title" to jsonJob
         * put "description" to jsonJob
         Now, accumulate the jsonJob to the root jo object:
         jo.accumulate - use "jobs" and the jsonJob object as args

         Do this in every iteration of the loop.
         Example data for one loop iteration:
    {
      "employer": "ITHS",
      "duration": {
        "start": "2017-01-01 00:00:00",
        "end": "2017-06-30 00:00:00"
      },
      "title": "Database teacher",
      "description": "Teacher for a Database course"
    },

      */
      // After the loop, write the file, use 2 indentation steps
      writer.println(jo.toString(2));
      writer.close();
    }catch(Exception e){
      System.err.println("Error creating Json: " + e.getMessage());
      e.printStackTrace();
      System.exit(1);
    }
  }
}
