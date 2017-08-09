import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XmlPullExample {
  public static void main(String[] args) {
    try {
      XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
      XmlPullParser parser = factory.newPullParser();
      parser.setInput(new FileReader("users.xml"));
      parser.nextTag();
      parser.require(XmlPullParser.START_TAG, null, "users");
      while(parser.nextTag() == XmlPullParser.START_TAG) {
        // Make sure we found a <user> tag
        parser.require(XmlPullParser.START_TAG, null, "user");
        // Find next tag, <name> (inside the <user>tag)
        parser.nextTag();
        parser.require(XmlPullParser.START_TAG, null, "name");
        System.out.println("name: "+ parser.nextText());
        parser.require(XmlPullParser.END_TAG, null, "name");

        // Find next tag <email>
        parser.nextTag();
        // Make sure we found <email> start tag
        parser.require(XmlPullParser.START_TAG, null, "email");
        System.out.println("email: "+ parser.nextText());
        parser.require(XmlPullParser.END_TAG, null, "email");

        // Find next tag <username>
        parser.nextTag();
        parser.require(XmlPullParser.START_TAG, null, "username");
        System.out.println("username: "+ parser.nextText());
        parser.require(XmlPullParser.END_TAG, null, "username");

        // Next tag should be the end tag of </user>
        parser.nextTag();
        parser.require(XmlPullParser.END_TAG, null, "user");        
      }
      // No more start tags found inside <users>, so make sure we are at the end
      parser.require(XmlPullParser.END_TAG, null, "users");
    } catch (XmlPullParserException e) {
      e.printStackTrace();
    } catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
}
