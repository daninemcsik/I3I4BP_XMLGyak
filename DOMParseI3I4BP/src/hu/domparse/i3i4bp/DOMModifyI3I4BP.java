package hu.domparse.i3i4bp;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DOMModifyI3I4BP {
		
		public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
			try {
				
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = (Document) db.parse(new File("src\\XMLI3I4BP.xml"));
				Node konyv = doc.getElementsByTagName("Könyv").item(0);	//elsõ könyv elem elérése
				
				NamedNodeMap attr =  konyv.getAttributes();	//elsõ könyv elem tulajdonságának lekérdezése
				Node nodeAttr = attr.getNamedItem("kkod"); //kkod tulajdonság megkeresése
				nodeAttr.setTextContent("444");	//tulajdonság értékének megváltoztatása
				
				NodeList list = konyv.getChildNodes();	//könyv elem gyerekelemeinek elérése
				
				for(int i = 0; i < list.getLength(); i++) { //iteráció elemek között
					Node node = list.item(i);
					if(node.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) node;
						if("cím".equals(eElement.getNodeName())) {	//cím megegyezik-e az éppen vizsgált elem nevével? Ha igen akkor...
							if("Senki sem az, akinek látszik".equals(eElement.getTextContent())) {	//...megnézi, hogy a cím elem tartalma is 
																									//megegyezik-e az éppen vizsgált elem tartalmával? Ha igen...
								eElement.setTextContent("Mindenki az, akinek látszik");	//...akkor megváltoztatja erre.
							}
						}
						if("kategória".equals(eElement.getNodeName())) {//kategória megegyezik-e az éppen vizsgált elem nevével? Ha igen akkor...
							if("Irodalom".equals(eElement.getTextContent())) { 	//...megnézi, hogy a kategória elem tartalma is
																				// megegyezik-e az éppen vizsgált elem tartalmával? Ha igen...
								eElement.setTextContent("Történelem"); //...akkor megváltoztatja erre.
							}
						}
					}
				}
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();	//transformer létrehozása, ami lehetõvé teszi majd az eredmény különféle kiíratását
				DOMSource source = new DOMSource(doc); //transzformációs 'source tree' számára egy tároló DOM faként
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); 			//elvileg ennek a két sornak kéne a kimenetet utf-8 karakterkészlettel 
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");	//létrehoznia, de nem mûködik. Konzolba nem írja ki rendesen a dolgokat, 
																						//de egyébként ki lehet olvasni, hogy a megfelelõ dolgok kerültek változtatásra,
																						// csak az ékezetes betûk helyett random karakterek szerepelnek
				StreamResult consoleResult = new StreamResult(System.out);		//transzformáció eredményének tárolója, xml, html, sima szöveg, vagy egyéb módon
				transformer.transform(source,consoleResult);	//a forrást (source) belerakja az eredmény tárolóba (consoleResult)
				
				
			}catch(Exception e) {	//hibakezelés, ha csak Exception-t adunk meg, akkor minden hibát kezel, ami nem saját hiba, mivel ez az összes hiba ebbõl ered.
				e.printStackTrace();
			}
		}
	}
