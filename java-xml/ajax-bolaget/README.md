Small example of using AJAX in a web page to read the Systembolaget
XML file asynchronously and build an HTML table from some of the nodes
in the XML file.

Start a web server and open bolaget.html in a browser on localhost.

If you have php installed, you may use the script included in this
directory ```start_php_web_server.sh``` which starts a web server
with this directory as the web root on localhost:8000

Navigate to http://localhost:8000/bolaget.html in order to test
the AJAX call included in the HTML page ```bolaget.html```.

The XML file was obtained from https://www.systembolaget.se/api/ .