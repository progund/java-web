A few small examples parsing the sortiment.xml from Systembolaget.

The first using Java's API (javax.xml etc) and a DOM and parses out a few values from a few fields from each product and builds a ```List<Product>```, sorts the list and prints the 30 first elements from the list.

The second using Java's API (javax.xml.xpath) and XPath, to filter out
nodes matching a criteria.

The third uses Java's API (javax.xml.stream) and StaX and parses the whole sortiment.xml
and prints a few fields from each ```<artikel>``` to std out.

The XML file used in these examples comes from the Swedish alcohol monopoly, [Systembolaget](https://www.systembolaget.se/api/).

An excerpt from the file is shown below.

```XML
<?xml version="1.0" encoding="utf-8"?>
<artiklar xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <skapad-tid>2017-08-10 05:04</skapad-tid>
  <info>
    <meddelande>Läs användarvillkoren på www.systembolaget.se. Nyheter efter 2016-11-03: Nytt fält: AssortmentText som förklarar med ord vad sortimentet är</meddelande>
  </info>
  <artikel>
    <nr>101</nr>
    <Artikelid>1</Artikelid>
    <Varnummer>1</Varnummer>
    <Namn>Renat</Namn>
    <Namn2/>
    <Prisinklmoms>209.00</Prisinklmoms>
    <Volymiml>700.00</Volymiml>
    <PrisPerLiter>298.57</PrisPerLiter>
    <Saljstart>1993-10-01</Saljstart>
    <Utgått>0</Utgått>
    <Varugrupp>Okryddad sprit</Varugrupp>
    <Typ/>
    <Stil/>
    <Forpackning>Flaska</Forpackning>
    <Forslutning/>
    <Ursprung/>
    <Ursprunglandnamn>Sverige</Ursprunglandnamn>
    <Producent>Pernod Ricard</Producent>
    <Leverantor>Pernod Ricard Sweden AB</Leverantor>
    <Argang/>
    <Provadargang/>
    <Alkoholhalt>37.50%</Alkoholhalt>
    <Sortiment>FS</Sortiment>
    <SortimentText>Ordinarie sortiment</SortimentText>
    <Ekologisk>0</Ekologisk>
    <Etiskt>0</Etiskt>
    <Koscher>0</Koscher>
    <RavarorBeskrivning>Säd.</RavarorBeskrivning>
  </artikel>
  <artikel>
    <nr>7599701</nr>
    <Artikelid>1000005</Artikelid>
    <Varnummer>75997</Varnummer>
    <Namn>Motzenbäcker Marie</Namn>
    <Namn2>Riesling Weissburgunder Spätlese Trocken</Namn2>
    <Prisinklmoms>139.00</Prisinklmoms>
    <Volymiml>750.00</Volymiml>
    <PrisPerLiter>185.33</PrisPerLiter>
    <Saljstart>2015-09-01</Saljstart>
    <Utgått>0</Utgått>
    <Varugrupp>Vitt vin</Varugrupp>
    <Typ/>
    <Stil/>
    <Forpackning>Flaska</Forpackning>
    <Forslutning/>
    <Ursprung>Pfalz</Ursprung>
    <Ursprunglandnamn>Tyskland</Ursprunglandnamn>
    <Producent>Menger-Krug</Producent>
    <Leverantor>Terrific Wines AB</Leverantor>
    <Argang>2014</Argang>
    <Provadargang/>
    <Alkoholhalt>12.00%</Alkoholhalt>
    <Sortiment>BS</Sortiment>
    <SortimentText>Övrigt sortiment</SortimentText>
    <Ekologisk>0</Ekologisk>
    <Etiskt>0</Etiskt>
    <Koscher>0</Koscher>
  </artikel>
  <artikel>
    <nr>7548901</nr>
    <Artikelid>1000008</Artikelid>
    <Varnummer>75489</Varnummer>
    <Namn>Valtellina Superiore</Namn>
    <Namn2>Sassella Riserva</Namn2>
    <Prisinklmoms>324.00</Prisinklmoms>
    <Volymiml>750.00</Volymiml>
    <PrisPerLiter>432.00</PrisPerLiter>
    <Saljstart>2015-09-01</Saljstart>
    <Utgått>0</Utgått>
    <Varugrupp>Rött vin</Varugrupp>
    <Typ/>
    <Stil/>
    <Forpackning>Flaska</Forpackning>
    <Forslutning/>
    <Ursprung>Lombardiet</Ursprung>
    <Ursprunglandnamn>Italien</Ursprunglandnamn>
    <Producent>Arpepe</Producent>
    <Leverantor>Vinoliv Import AB</Leverantor>
    <Argang>2011</Argang>
    <Provadargang/>
    <Alkoholhalt>13.50%</Alkoholhalt>
    <Sortiment>BS</Sortiment>
    <SortimentText>Övrigt sortiment</SortimentText>
    <Ekologisk>0</Ekologisk>
    <Etiskt>0</Etiskt>
    <Koscher>0</Koscher>
  </artikel>
</artiklar>
```
