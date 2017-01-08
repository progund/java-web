package main;

/*
 * Using org.json from
 * android-json.jar
 *
 * Compile and run (assuming you have put the jar file in "lib"
 * and the json file in current directory):
 * $ javac -cp .:lib/javax.json.jar main/JSONExample.java
 * $ java -cp .:lib/javax.json.jar main.JSONExample data.json
 *
 * Where "data.json" is a valid json file.
 *
 * (on windows, class path becomes -cp ".;lib/javax.json.jar")
 *
 */


/* Import our JSON-parser from util: */
import util.JSONParser;
import util.JSONProducer;

public class JSONExample{
  public static void main(String[] args){
    String jsonFile=null;
    if(args.length!=0){
      jsonFile=args[0];
      try{
        new JSONParser(jsonFile).parseAndPrint();
      }catch(java.io.IOException fnfe){
        System.err.println("File not found: " + jsonFile);
      }
    }else{
      usage();
    }
        
    System.out.println("Creating json file...");
    JSONProducer jProd = new JSONProducer("test.json");
    try{
      jProd.createAndWrite();	    
    }catch(java.io.IOException ioe){
      System.err.println("Error writing file: " + ioe.getMessage());
    }
        
  }
  private static void usage(){
    System.err.println("USAGE: java JSONExample <file>");
    System.err.println(" where <file> is a valid json file");
  }
}
