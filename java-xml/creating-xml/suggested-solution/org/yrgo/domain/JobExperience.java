package org.yrgo.domain;

public class JobExperience{
  private String start;
  private String end;
  private String employer;
  private String description;
  private String title;
  public JobExperience(String employer, String title, String description, String start, String end){
    this.employer=employer;
    this.title=title;
    this.description=description;
    this.start=start;
    this.end=end;
  }
  public String employer(){ return employer; }
  public String title(){ return title; }
  public String description(){ return description; }
  public String start(){ return start; }
  public String end(){ return end; }
  @Override
  public String toString(){
    return new StringBuilder(employer)
      .append(" | ").append(title)
      .append(" | ").append(description)
      .append(" | ").append(start)
      .append(" | ").append(end)
      .toString();
  }
}
