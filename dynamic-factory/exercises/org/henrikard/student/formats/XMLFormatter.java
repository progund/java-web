package org.henrikard.student.formats;

import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.parsers.ParserConfigurationException; 
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource; 
import javax.xml.transform.stream.StreamResult; 

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.henrikard.student.domain.Student;

class XMLFormatter implements Formatter {

  private static XMLFormatter instance;

  static {
    instance = new XMLFormatter();
    FormatterFactory.registerFormatter("xml", instance);
  }

  private String document;

  private XMLFormatter() {

  }

  public void loadFromList(List<Student> students) {
    try {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("STUDENTS");
      doc.appendChild(rootElement);

      for (Student stud : students ) {
        Element student = doc.createElement("STUDENT");
        student.setAttribute("id", stud.id()+"");
        Element name    = doc.createElement("NAME");
        name.appendChild(doc.createTextNode(stud.name()));
        student.appendChild(name);
        rootElement.appendChild(student);
      }
      
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer
        .setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      DOMSource source = new DOMSource(doc);
      StringWriter sw = new StringWriter();
      StreamResult result = new StreamResult(sw);
      transformer.transform(source, result);
      this.document = sw.toString();
    } catch (ParserConfigurationException pce) {
      pce.printStackTrace();
    } catch (TransformerException tfe) {
      tfe.printStackTrace();
    }

  }
  
  public String getDocument() {
    return document;
  }
  
  public String getContentType() {
    return "application/xml";
  }
}
