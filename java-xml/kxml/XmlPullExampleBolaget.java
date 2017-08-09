import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XmlPullExampleBolaget {
  static final String PRODUCT = "artikel";
  static final String NAME = "Namn";
  static final String NAME2 = "Namn2";
  static final String ALCOHOL = "Alkoholhalt";
  static final String PRICE = "Prisinklmoms";
  static final String VOLUME = "Volymiml";
  static final String TOP_LEVEL = "artiklar";
  public static void main(String[] args) {
    try {
      XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
      XmlPullParser parser = factory.newPullParser();
      parser.setInput(new FileReader("sortiment.xml"));
      parser.nextTag();
      parser.require(XmlPullParser.START_TAG, null, TOP_LEVEL);
      parser.nextTag();
      parser.require(XmlPullParser.START_TAG, null, "skapad-tid"); 
      parser.nextText();
      parser.nextTag();
      parser.nextTag();
      parser.require(XmlPullParser.START_TAG, null, "meddelande"); 
      parser.nextText();
      parser.nextTag();
      parser.nextTag();
      parser.require(XmlPullParser.START_TAG, null, "artikel"); 
      parser.nextTag();
      
      int event = 0;
      String name = "";
      String alcohol = "";
      String price = "";
      String volume = "";
      List<Product> products = new ArrayList<>();
      while((event = parser.next()) != XmlPullParser.END_DOCUMENT) {
        // Ignore and consume all end tags
        if (parser.getEventType() == XmlPullParser.END_TAG) {
          if (PRODUCT.equals(parser.getName())) {
            products.add(new Product(name,
                                     Double.parseDouble(alcohol.substring(0,alcohol.length()-1)),
                                     Double.parseDouble(price),
                                     (int)Double.parseDouble(volume)));
          }
          parser.nextTag();
        }
        if (parser.getEventType() == XmlPullParser.START_TAG
            && NAME.equals(parser.getName())) {
          name=parser.nextText().trim();
          parser.nextTag();
          // The tag after name is name2
          if (parser.getEventType() == XmlPullParser.START_TAG && NAME2.equals(parser.getName())) {
            String name2 = parser.nextText();
            if (name2.length() > 0) {
              name += " ";
              name += name2;
            }
          }
        } else if (parser.getEventType() == XmlPullParser.START_TAG && ALCOHOL.equals(parser.getName())) {
          alcohol = parser.nextText();
        } else if (parser.getEventType() == XmlPullParser.START_TAG && VOLUME.equals(parser.getName())) {
          volume = parser.nextText();
        } else if (parser.getEventType() == XmlPullParser.START_TAG && PRICE.equals(parser.getName())) {
          price = parser.nextText();
        } else if (parser.getEventType() == XmlPullParser.START_TAG && PRODUCT.equals(parser.getName())) {
          // Found another <artikel>, skip it
          parser.nextTag();
        } else if (parser.getEventType() == XmlPullParser.START_TAG) {
          // Ignore all other tags
          //System.out.println("Ignoring: " + parser.getName());
          parser.nextText();
          continue;
        }
      }
      System.out.println("============\nName sort:");
      Collections.sort(products, (p1, p2) -> p1.name().compareTo(p2.name()));
      for (int i = 0; i < 100; i++) {
        System.out.println(products.get(i));
      }
      System.out.println("============\nPrice sort:");
      Collections.sort(products, (p1, p2) -> (int)(p1.price()*100 - p2.price()*100));
      for (int i = 0; i < 100; i++) {
        System.out.println(products.get(i));
      }
      System.out.println("============\nAlco sort:");
      Collections.sort(products, (p1, p2) -> (int)(p1.alcohol()*100 - p2.alcohol()*100));
      for (int i = 0; i < 100; i++) {
        System.out.println(products.get(i));
      }
      Comparator<Product> reversedAlco = Comparator.comparing(Product::alcohol).reversed();
      System.out.println("============\nReversed alco sort:");
      Collections.sort(products, reversedAlco);
      for (int i = 0; i < 100; i++) {
        System.out.println(products.get(i));
      }
      Comparator<Product> nameCaseInsensitive = (p1, p2) ->
        p1.name().toLowerCase().compareTo(p2.name().toLowerCase());
      System.out.println("============\nReversed Caseinsensitive name sort:");
      Collections.sort(products, nameCaseInsensitive.reversed());
      for (int i = 0; i < 100; i++) {
        System.out.println(products.get(i));
      }
      System.out.println("============\nReversed Alcohol, then Caseinsensitive name sort:");
      Collections.sort(products, reversedAlco.thenComparing(nameCaseInsensitive));
      for (int i = 0; i < 100; i++) {
        System.out.println(products.get(i));
      }
      
    } catch (XmlPullParserException e) {
      e.printStackTrace();
    } catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
}
