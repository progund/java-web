package client;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;

public class SmallParser{
  public static void main(String[] args){
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse("users.xml");
      NodeList nodeList = document.getDocumentElement().getChildNodes();
      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element elem = (Element) node;
          // long version
          // String name = elem.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
          // longer version
          // String name = elem.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
          
          String name = elem.getElementsByTagName("name").item(0).getTextContent();
          String email = elem.getElementsByTagName("email").item(0).getTextContent();
          String userName = elem.getElementsByTagName("username").item(0).getTextContent();
          System.out.println("name: " + name + " email: " + email + " userName: " + userName);
        }
      }
    }catch(SAXException|IOException|ParserConfigurationException e){
      System.err.println("Error parsing XML: " + e.getMessage());
    }    
  }
}
