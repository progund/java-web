package org.henrikard.student.formats;

import java.util.Map;
import java.util.HashMap;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FormatterFactory{

    static{
        try{
            System.err.println("Loading formatters...");
            String formatsXML = System.getProperty("formatsXML");
            System.err.println("Loading formats file: " + formatsXML);
            Properties formats = new Properties();
            formats.loadFromXML(new FileInputStream(formatsXML));
            for(String format : formats.stringPropertyNames()){
                String className = formats.getProperty(format); 
                System.out.println(format + " formatter found, loading class: " + className);
                try{
                    Class.forName(className);
                }catch(ClassNotFoundException cnfe){
                    System.err.println(cnfe.getMessage());
                }
            }
        }catch(FileNotFoundException fne){
            System.err.println(fne.getMessage());
        }catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
    }

    private static Map<String, Formatter> formatters;
    
    static void registerFormatter(String format, Formatter formatter){
        if(formatters == null){
            formatters = new HashMap<String, Formatter>();
        }
        formatters.put(format, formatter);
    }

    public static Formatter getFormatter(String format)
        throws FormatNotSupportedException{
        
        Formatter formatter = formatters.get(format);
        if(formatter == null){
            throw new FormatNotSupportedException(format);
        }
        return formatter;
    }
}
