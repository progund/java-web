package org.yrgo.database;
import java.sql.*;

public class Database{
  private Connection con;
  static{
    try{
      Class.forName("org.sqlite.JDBC");
    }catch(ClassNotFoundException e){
      System.err.println("Couldn't load sqlite driver: " + e.getMessage());
    }
  }
  public Database(String db){
    try{
      con = DriverManager.getConnection("jdbc:sqlite:"+db);
    }catch(SQLException e){
      System.err.println("Fatal: couldn't get connection: " + e.getMessage());
      throw new RuntimeException("Couldn't connect", e);
    }
  }
  public ResultSet query(String sql){
    ResultSet rs = null;
    try{
      rs = con.createStatement().executeQuery(sql);
    }catch(SQLException e){
      System.err.println("Error: couldn't execute query " +
                         sql + " " + e.getMessage());
    }
    return rs;
  }
}
