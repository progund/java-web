A few small examples parsing the sortiment.xml from Systembolaget.

The first using Java's API (javax.xml etc) and a DOM and parses out a few values from a few fields from each product and builds a ```List<Product>```, sorts the list and prints the 30 first elements from the list.

The second using Java's API (javax.xml.xpath) and XPath, to filter out
nodes matching a criteria.

The third uses Java's API (javax.xml.stream) and StaX and parses the whole sortiment.xml
and prints a few fields from each ```<artikel>``` to std out.