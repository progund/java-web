import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BolagetParser{

  static final String PRODUCT = "artikel";
  static final String NAME = "Namn";
  static final String NAME2 = "Namn2";
  static final String ALCOHOL = "Alkoholhalt";
  static final String PRICE = "Prisinklmoms";
  static final String VOLUME = "Volymiml";
  static final String DROPPED = "Utgått";
  
  /* Builds a DOM from the XML document
   * and loops over the <artikel> nodes and
   * gets a few values from each <artikel>.
   */
  public static void main(String[] args){
    try {
      List<Product> products = new ArrayList<>();
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse("sortiment.xml");
      //NodeList nodeList = document.getDocumentElement().getChildNodes();
      NodeList nodeList = document.getDocumentElement().getElementsByTagName(PRODUCT);
      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element elem = (Element) node;
          String name = elem.getElementsByTagName(NAME).item(0).getTextContent();
          name += " " + elem.getElementsByTagName(NAME2).item(0).getTextContent();
          name = name.trim();
          String alcohol = elem.getElementsByTagName(ALCOHOL).item(0).getTextContent();
          String price = elem.getElementsByTagName(PRICE).item(0).getTextContent();
          String volume = elem.getElementsByTagName(VOLUME).item(0).getTextContent();
          boolean dropped = "1".equals(elem.getElementsByTagName(DROPPED).item(0).getTextContent());
          if (!dropped) {
            products.add(new Product(name,
                                     Double.parseDouble(alcohol.substring(0, alcohol.length()-1)),
                                     Double.parseDouble(price),
                                     (int)Double.parseDouble(volume)));
            //System.out.println(name + ", " + alcohol + ", " + volume + " ml, " + price + " SEK");
          }
        }
      }
      System.out.println(products.size() + " products loaded.");
      Comparator<Product> reversedAlcoholComparator = Comparator.comparing(Product::alcohol).reversed();
      Collections.sort(products, reversedAlcoholComparator);
      for (int i = 0; i < 30; i++) {
        System.out.println(products.get(i));
      }
    }catch(SAXException|IOException|ParserConfigurationException e){
      System.err.println("Error parsing XML: " + e.getMessage());
    }    
  }
}
