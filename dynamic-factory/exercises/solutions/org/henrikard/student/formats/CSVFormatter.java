package org.henrikard.student.formats;

import org.henrikard.student.domain.Student;

import java.util.List;


public class CSVFormatter implements Formatter {

  private static final String SEPARATOR = ",";
  private static CSVFormatter instance;
  
  static {
        instance = new CSVFormatter();
        FormatterFactory.registerFormatter("csv", instance);
  }
  
  private String document;

  private CSVFormatter() {
    // Prevent direct instantiation
  }
  
  public void loadFromList(List<Student> list) {
    StringBuilder result = new StringBuilder("name,id\n");
    for(Student student : list) {
      result.append(student.name())
        .append(SEPARATOR)
        .append(student.id())
        .append("\n");
    }
    document = result.toString();
  }
  
  public String getDocument() {
    return document;
  }
  
  public String getContentType() {
    return "text/csv";
  }

}
