import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class BolagetStaXParser {

  static final String PRODUCT = "artikel";
  static final String NAME = "Namn";
  static final String NAME2 = "Namn2";
  static final String ALCOHOL = "Alkoholhalt";
  static final String PRICE = "Prisinklmoms";
  static final String VOLUME = "Volymiml";
  static final String DROPPED = "Utgått";

  public static void main(String[] args) {
    List<Product> products = new ArrayList<>();
    try {
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
      InputStream in = new FileInputStream("sortiment.xml");
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

      String name = null;
      String alcohol = null;
      String volume = null;
      String price = null;
      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();

        if (event.isStartElement()) {
          StartElement startElement = event.asStartElement();
          if (startElement.getName().getLocalPart().equals(NAME)) {
            event = eventReader.nextEvent();
            name = (event.asCharacters().getData());
            continue;
          }
        }
        if (event.isStartElement()) {
          StartElement startElement = event.asStartElement();
          if (startElement.getName().getLocalPart().equals(NAME2)) {
            event = eventReader.nextEvent();
            // <namn2> is sometimes empty: <namn2/>
            if (event.isCharacters()) {
              name += " " + (event.asCharacters().getData());
              name = name.trim();
            }
            continue;
          }
        }
        if (event.isStartElement()) {
          StartElement startElement = event.asStartElement();
          if (startElement.getName().getLocalPart().equals(ALCOHOL)) {
            event = eventReader.nextEvent();
            alcohol = (event.asCharacters().getData());
            continue;
          }
        }
        if (event.isStartElement()) {
          StartElement startElement = event.asStartElement();
          if (startElement.getName().getLocalPart().equals(VOLUME)) {
            event = eventReader.nextEvent();
            volume = (event.asCharacters().getData());
            continue;
          }
        }
        if (event.isStartElement()) {
          StartElement startElement = event.asStartElement();
          if (startElement.getName().getLocalPart().equals(PRICE)) {
            event = eventReader.nextEvent();
            price = (event.asCharacters().getData());
            continue;
          }
        }
        if (event.isEndElement()) {
          EndElement endElement = event.asEndElement();
          if (endElement.getName().getLocalPart().equals(PRODUCT)) {
            System.out.println(name + " " + price + " sek " + alcohol + " " + volume + " ml");
          }
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (XMLStreamException e) {
      e.printStackTrace();
    }
    // Print the list
  }
  
}
