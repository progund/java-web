import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.DOMException;
import java.io.File;
import java.util.List;

import org.yrgo.util.CVFetcher;
import org.yrgo.domain.JobExperience;

public class CreateCV{
  public static void main(String[] args){
    try{
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("CV");
      doc.appendChild(rootElement);
      /* Create a CVFetcher here! */

      /* Loop over each JobExperience you get from the fetcher here */

      /* for each JobExperience, create an Element with name JOB,
         which will contain three child elements:
         * EMPLOYER (with attributes start and end - the dates)
           with a TextNode with the name of the employer
         * TITLE (with a TextNode with the title text)
         * DESCRIPTION (with a TextNode with the description text)
         Add the three children in order to the JOB element.
         Add the JOB element to the rootElement.

         Do this in every iteration of the loop.

         Each iteration will thus create a child node to the rootElement
         of this form:
         <JOB>
           <EMPLOYER end="2016-02-28 00:00:00" start="2016-01-01 00:00:00">
             ITHS
           </EMPLOYER>
           <TITLE>Database teacher</TITLE>
           <DESCRIPTION>Teacher for a Database course</DESCRIPTION>
         </JOB>
      */
      
      TransformerFactory transformerFactory = TransformerFactory.
        newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer
        .setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
                           "2");
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File("CV.xml"));
      transformer.transform(source, result);
    }catch(Exception e){
      System.err.println("Error creating xml: " + e.getMessage());
      e.printStackTrace();
      System.exit(1);
    }
  }
}
