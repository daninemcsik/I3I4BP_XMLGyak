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
				Node konyv = doc.getElementsByTagName("K�nyv").item(0);	//els� k�nyv elem el�r�se
				
				NamedNodeMap attr =  konyv.getAttributes();	//els� k�nyv elem tulajdons�g�nak lek�rdez�se
				Node nodeAttr = attr.getNamedItem("kkod"); //kkod tulajdons�g megkeres�se
				nodeAttr.setTextContent("444");	//tulajdons�g �rt�k�nek megv�ltoztat�sa
				
				NodeList list = konyv.getChildNodes();	//k�nyv elem gyerekelemeinek el�r�se
				
				for(int i = 0; i < list.getLength(); i++) { //iter�ci� elemek k�z�tt
					Node node = list.item(i);
					if(node.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) node;
						if("c�m".equals(eElement.getNodeName())) {	//c�m megegyezik-e az �ppen vizsg�lt elem nev�vel? Ha igen akkor...
							if("Senki sem az, akinek l�tszik".equals(eElement.getTextContent())) {	//...megn�zi, hogy a c�m elem tartalma is 
																									//megegyezik-e az �ppen vizsg�lt elem tartalm�val? Ha igen...
								eElement.setTextContent("Mindenki az, akinek l�tszik");	//...akkor megv�ltoztatja erre.
							}
						}
						if("kateg�ria".equals(eElement.getNodeName())) {//kateg�ria megegyezik-e az �ppen vizsg�lt elem nev�vel? Ha igen akkor...
							if("Irodalom".equals(eElement.getTextContent())) { 	//...megn�zi, hogy a kateg�ria elem tartalma is
																				// megegyezik-e az �ppen vizsg�lt elem tartalm�val? Ha igen...
								eElement.setTextContent("T�rt�nelem"); //...akkor megv�ltoztatja erre.
							}
						}
					}
				}
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();	//transformer l�trehoz�sa, ami lehet�v� teszi majd az eredm�ny k�l�nf�le ki�rat�s�t
				DOMSource source = new DOMSource(doc); //transzform�ci�s 'source tree' sz�m�ra egy t�rol� DOM fak�nt
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); 			//elvileg ennek a k�t sornak k�ne a kimenetet utf-8 karakterk�szlettel 
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");	//l�trehoznia, de nem m�k�dik. Konzolba nem �rja ki rendesen a dolgokat, 
																						//de egy�bk�nt ki lehet olvasni, hogy a megfelel� dolgok ker�ltek v�ltoztat�sra,
																						// csak az �kezetes bet�k helyett random karakterek szerepelnek
				StreamResult consoleResult = new StreamResult(System.out);		//transzform�ci� eredm�ny�nek t�rol�ja, xml, html, sima sz�veg, vagy egy�b m�don
				transformer.transform(source,consoleResult);	//a forr�st (source) belerakja az eredm�ny t�rol�ba (consoleResult)
				
				
			}catch(Exception e) {	//hibakezel�s, ha csak Exception-t adunk meg, akkor minden hib�t kezel, ami nem saj�t hiba, mivel ez az �sszes hiba ebb�l ered.
				e.printStackTrace();
			}
		}
	}
