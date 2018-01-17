package org.henrikard.student.main;

import org.henrikard.student.storage.StudentStorageFactory;
import org.henrikard.student.storage.StudentStorage;
import org.henrikard.student.storage.StorageException;
import org.henrikard.student.domain.Student;
import org.henrikard.student.formats.Formatter;
import org.henrikard.student.formats.FormatterFactory;
import org.henrikard.student.formats.FormatNotSupportedException;

import java.util.List;

public class Main {
  
  public static void main(String[] args) {
        
    if (args.length != 1) {
      System.err.println("No format argument found.");
      System.exit(1);
    }
    
    try {
      String format = args[0];
      Formatter formatter = FormatterFactory.getFormatter(format); 
      StudentStorage storage = StudentStorageFactory.getStorage();
      List<Student> students = storage.getAllStudents();
      formatter.loadFromList(students);
      System.out.println("All students as " +
                         formatter.getContentType() + ":");
      System.out.println(formatter.getDocument());
    } catch (FormatNotSupportedException fnse) {
      System.err.println(fnse.getMessage());
    } catch(StorageException se) {
      System.err.println("Error accessing the storage: " 
                         + se.getMessage());
    }
  }
}
