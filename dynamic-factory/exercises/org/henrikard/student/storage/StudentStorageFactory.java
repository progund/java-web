package org.henrikard.student.storage;

public class StudentStorageFactory {
  public static StudentStorage getStorage() {
    return new StudentStorageDB();
  }
}
