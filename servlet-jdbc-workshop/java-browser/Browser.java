import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.*;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class Browser {

  static {
    try {
      // Ignore the lines below - it's a fix for Rikard's computer. Hell Dell!
            javax.swing.UIManager
              .setLookAndFeel((javax.swing.LookAndFeel)Class
                              .forName("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
                              .newInstance());
    } catch (Exception ignore) {}
  }

  /* Components and controls */
  private JFrame browser;
  private JEditorPane content;
  private JScrollPane contentScrollPane;
  private static final String SERVLET_URL = "http://localhost:8080/database-servlet";
  private JButton fetchButton;
  private JPanel buttonPanel;
  private JTextField addressField;
  
  public Browser() {
    initComponents();
    layoutComponents();
    addListeners();
    show();
  }

  public void initComponents() {
    addressField = new JTextField("http://wiki.juneday.se");
    browser = new JFrame("Student browswer 2000");
    browser.setLayout(new BorderLayout());
    browser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    browser.setSize(new Dimension(900,500));
    content = new JEditorPane();
    content.setEditable(false);
    content.setContentType("text/html");
    fetchButton = new JButton("GO");
    buttonPanel = new JPanel();
    contentScrollPane = new JScrollPane(content);
    contentScrollPane.setPreferredSize(new Dimension(700, 500));
    contentScrollPane.setMinimumSize(new Dimension(10, 10));
    contentScrollPane
      .setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    contentScrollPane
      .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
  }

  public void layoutComponents() {
    /* Add the content panel */
    browser.add(contentScrollPane, BorderLayout.CENTER);
    /* Add the button to the panel and the panel to the frame */
    buttonPanel.add(addressField);
    buttonPanel.add(fetchButton);
    browser.add(buttonPanel, BorderLayout.NORTH);
  }

  public void addListeners() {
    ActionListener fetchListener = e -> {
      content.setText(fetchHtml());
      content.setCaretPosition(0);
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            contentScrollPane.getHorizontalScrollBar().setValue(0);
          }
        });
    };
    fetchButton.addActionListener(fetchListener);
    addressField.addActionListener(fetchListener);
    fetchButton.doClick();
    content.addHyperlinkListener(new HyperlinkListener() {
        public void hyperlinkUpdate(HyperlinkEvent hyperlinkEvent) {
          HyperlinkEvent.EventType type = hyperlinkEvent.getEventType();
          final URL url = hyperlinkEvent.getURL();
          if (type == HyperlinkEvent.EventType.ENTERED) {
            System.out.println("Hovering URL: " + url);
          } else if (type == HyperlinkEvent.EventType.ACTIVATED) {
            System.out.println("Activated URL: " + url);

            // Save the old document
            Document doc = content.getDocument();
            try {
              content.setPage(url);
            } catch (IOException ioException) {
              System.out.println("Error following link, Invalid link: " + url);
              content.setDocument(doc);
            }
          }
        }
      });
  }

  private String fetchHtml() {

    content.setText(" fetching...");
    StringBuilder response = new StringBuilder();
    
    try {
      URL url = new URL(addressField.getText());
      ((HTMLDocument)content.getDocument()).setBase(url);
      HttpURLConnection connection =
        (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      String location = connection.getHeaderField("Location");
      if (location != null) {
        url = new URL(location);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        addressField.setText(location);
      }
    BufferedReader in =
      new BufferedReader
      (new InputStreamReader(connection.getInputStream()));

    String line;
    while ((line = in.readLine()) != null) {
      response.append(line);
    }
    
    in.close();
      
    } catch (IOException e) {
      System.err.println("Error reading response from server: " + e.getMessage());
      response.append("<html><body>Error fetching students from servlet:<br>\n");
      response.append("<div style=\"color: red;\">");
      response.append(e.getMessage());
      response.append("</div>\n</body></html>");
    }
    return response.toString();
  }
  
  public void show() {
    browser.setVisible(true);
  }
}
