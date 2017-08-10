import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class BolagetXPathParser {

  static final String PRODUCT = "artikel";
  static final String NAME = "Namn";
  static final String NAME2 = "Namn2";
  static final String ALCOHOL = "Alkoholhalt";
  static final String PRICE = "Prisinklmoms";
  static final String VOLUME = "Volymiml";
  static final String DROPPED = "Utgått";

  public static void main(String[] args) throws Exception {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder;
    Document doc = null;

    XPathExpression expr = null;
    builder = factory.newDocumentBuilder();
    doc = builder.parse("sortiment.xml");

    XPathFactory xFactory = XPathFactory.newInstance();
    XPath xpath = xFactory.newXPath();
    expr = xpath.compile("artiklar/artikel/*"+
                         "[(substring(../Alkoholhalt, 1, string-length(../Alkoholhalt)-1) > 59.00)"+
                         "and (self::Namn or self::Prisinklmoms or self::Alkoholhalt "+
                         "or self::Namn2 or self::Volymiml)]");
    
    Object result = expr.evaluate(doc, XPathConstants.NODESET);
    NodeList nodes = (NodeList) result;

    String name = null;
    String alcohol = null;
    String price = null;
    String volume = null;
    List<Product> products = new ArrayList<>();
    for (int i=0; i<nodes.getLength();i++){
      if (NAME.equals(nodes.item(i).getNodeName())) {
        name = nodes.item(i).getTextContent();
        continue;
      } else if (PRICE.equals(nodes.item(i).getNodeName())) {
        price = nodes.item(i).getTextContent();
        continue;
      } else if (VOLUME.equals(nodes.item(i).getNodeName())) {
        volume = nodes.item(i).getTextContent();
        continue;
      } else if (NAME2.equals(nodes.item(i).getNodeName())) {
        name += " " + nodes.item(i).getTextContent();
        name = name.trim();
        continue;
      } else if (ALCOHOL.equals(nodes.item(i).getNodeName())) {
        alcohol = nodes.item(i).getTextContent();
      }
      products.add(new Product(name,
                               Double.parseDouble(alcohol.substring(0, alcohol.length()-1)),
                               Double.parseDouble(price),
                               (int)Double.parseDouble(volume)));
      //System.out.println(name + " " + alcohol + " " + price + " sek");
    }
    Comparator<Product> alcoholReversed = Comparator.comparing(Product::alcohol).reversed();
    Collections.sort(products, alcoholReversed);
    System.out.println("Sorted by alcolhol, descending, with alcohol above 59%:");
    for (Product product : products) {
      System.out.println(product);
    }
  }
  
}
