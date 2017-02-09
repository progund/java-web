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
      CVFetcher fetcher = new CVFetcher();
      for(JobExperience job : fetcher.getJobs()){
        Element jobElement = doc.createElement("JOB");
        Element employer   = doc.createElement("EMPLOYER");
        employer.setAttribute("end",   job.end());
        employer.setAttribute("start", job.start());
        employer.appendChild(doc.createTextNode(job.employer()));
        jobElement.appendChild(employer);
        Element title = doc.createElement("TITLE");
        title.appendChild(doc.createTextNode(job.title()));
        jobElement.appendChild(title);
        Element description = doc.createElement("DESCRIPTION");
        description.appendChild(doc.createTextNode(job.description()));
        jobElement.appendChild(description);
        rootElement.appendChild(jobElement);
      }
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
