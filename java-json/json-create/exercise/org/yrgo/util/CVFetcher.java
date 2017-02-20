package org.yrgo.util;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import org.yrgo.database.Database;
import org.yrgo.domain.JobExperience;

public class CVFetcher{
  private static final String DB_FILE="cv.db";
  private static final String JOB_QUERY="select * from job_experience order by start_date;";
  Database db;
  public CVFetcher(){
    db = new Database(DB_FILE);
  }
  public List<JobExperience>getJobs(){
    List<JobExperience> list = new ArrayList<>();
    try{
      ResultSet rs = db.query(JOB_QUERY);
      JobExperience job = null;
      while(rs.next()){
        job=new JobExperience(rs.getString("employer"),
                              rs.getString("title"),
                              rs.getString("description"),
                              rs.getString("start_date"),
                              rs.getString("end_date"));
        list.add(job);
      }
    }catch(SQLException e){
      System.err.println("Fatal: couldn't fetch jobs: " + e.getMessage());      
    }
    //System.out.println(list);
    return list;
  }
}
