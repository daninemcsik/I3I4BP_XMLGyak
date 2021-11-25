package hu.domparse.i3i4bp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryI3I4BP {
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = (Document) db.parse(new File("src\\XMLI3I4BP.xml"));
			doc.getDocumentElement().normalize();
			        
	        System.out.println("Könyvek címe, amik irodalm kategóriába sorolhatók:");
	        listLiteratureBookNames(doc);
	        System.out.println("-----------------------------------------------------------------\n");	//kiíratás
	        listKerryFisherBookBuyersNamesAndAges(doc);
	        System.out.println("-----------------------------------------------------------------\n");
	        listShopPhonenumberAddressAndName(doc);
	        System.out.println("-----------------------------------------------------------------\n");
	        listPublisherAndWriterNames(doc);
	        System.out.println("-----------------------------------------------------------------\n");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//40-83 sor Szerzõ, Kiadó, Könyvesbolt, Vásárló elem elérése kódismétlés elkerülése érdekében.
	static Element getSzerzoElement(Document doc, int i) {
		NodeList nodelist = doc.getElementsByTagName("Szerzõ");
        Node node = nodelist.item(i);
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			return element;
		}else {
			return null;
		}
	}
	
	static Element getKiadoElement(Document doc, int i) {
		NodeList nodelist = doc.getElementsByTagName("Kiadó");
        Node node = nodelist.item(i);
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			return element;
		}else {
			return null;
		}
	}
	
	static Element getKonyvesboltElement(Document doc, int i) {
		NodeList nodelist = doc.getElementsByTagName("Könyvesbolt");
        Node node = nodelist.item(i);
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			return element;
		}else {
			return null;
		}
	}
	
	static Element getVasarloElement(Document doc, int i) {
		NodeList nodelist = doc.getElementsByTagName("Vásárló");
        Node node = nodelist.item(i);
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			return element;
		}else {
			return null;
		}
	}
	
	static void listLiteratureBookNames(Document doc) {	//Irodalom kategóriájú könyvek nevének lekérdezése.
		NodeList konyv = doc.getElementsByTagName("Könyv");
        for(int i = 0; i < konyv.getLength(); i++) {
        	Node node = konyv.item(i);
        	if(node.getNodeType() == Node.ELEMENT_NODE) {
        		Element element = (Element) node;
        		//kiíratás
        		if((element.getElementsByTagName("kategória").item(0).getTextContent()).equalsIgnoreCase("Irodalom")) {
        			System.out.println("	" + element.getElementsByTagName("cím").item(0).getTextContent());
        		}
        	}
        }
	}
	
	static void listKerryFisherBookBuyersNamesAndAges(Document doc) { //Kerry Fisher könyveket vásárló emberek nevének és korának lekérdezése.
		NodeList konyv = doc.getElementsByTagName("Könyv");
		System.out.println("Kerry Fisher könyveit õk vették meg:");
        for(int i = 0; i < konyv.getLength(); i++) {
        	Node node = konyv.item(i);
        	if(node.getNodeType() == Node.ELEMENT_NODE) {
        		Element szerzo = getSzerzoElement(doc, i);
        		Element vasarlo = getVasarloElement(doc, i);
        		//kiíratás
        		if((szerzo.getElementsByTagName("szNév").item(0).getTextContent()).equalsIgnoreCase("Kerry Fisher")) {
        			System.out.println("	Vásárló neve: " + vasarlo.getElementsByTagName("vNév").item(0).getTextContent()
        					+ ", " + vasarlo.getElementsByTagName("vÉletkor").item(0).getTextContent() + " éves."
        					);
        		}
				
        		
        	}
        }
	}
	
	static void listShopPhonenumberAddressAndName(Document doc) { //Lekérdezés, hogy Ken Follett melyik könyveit hol adták el, ezek a helyek adatai.
		NodeList konyv = doc.getElementsByTagName("Könyv");
		System.out.println("Ken Follett alábbi könyveit adták el: ");
        for(int i = 0; i < konyv.getLength(); i++) {
        	Node node = konyv.item(i);
        	if(node.getNodeType() == Node.ELEMENT_NODE) {	
        		Element kbolt = getKonyvesboltElement(doc, i);
        		Element szerzo = getSzerzoElement(doc,i);
        		Element element =(Element) node;
        		//kiíratás
        		if((szerzo.getElementsByTagName("szNév").item(0).getTextContent()).equalsIgnoreCase("Ken Follett")){
        			System.out.println("	Könyv címe: " + element.getElementsByTagName("cím").item(0).getTextContent() + "\nAz alábbi könyvesboltba:");
        			System.out.println("	Neve: " + kbolt.getElementsByTagName("kbNév").item(0).getTextContent() + "\n	Címe: " +
        			kbolt.getElementsByTagName("kbVáros").item(0).getTextContent() + ", " + kbolt.getElementsByTagName("kbCím").item(0).getTextContent()
        			+ "\n	Telefonszáma: " + kbolt.getAttribute("telefonszam"));
        			System.out.println();
        			
        		}
				
        		
        	}
        }
	}
	
	static boolean isInList(ArrayList<String> a, String str) {	//Megnézi, hogy egy név benne van-e már az arraylist-ben, ismétlõdés elkerülése érdekében.
		boolean state = false;									//Ez csak azért szükséges bele, mert úgy írtam meg az xml dokumentumot, hogy egy szerzõhöz egy 
																//kiadó tartozik, tehát nem lehet 2 különbözõ kiadó egy szerzõhöz.
		for(int i = 0; i < a.size(); i++) {						//Így 5 sort ír ki, szerzõ neve és a hozzá vett kiadó. Ha nem lenne ez benne, akkor mindent 2x írna ki.
			if(a.get(i).equalsIgnoreCase(str)) {				//Ha egy szerzõ több kiadóhoz is tartozna, akkor másképp kéne csinálni.
				state = true;
				break;
			}else {
				state = false;
			}
		}
		return state;
	}
	
	static void listPublisherAndWriterNames(Document doc) {	//Lekérdezés, ami megadja, hogy melyik író melyik kiadónál publikálta könyveit.
		NodeList konyv = doc.getElementsByTagName("Könyv");
		System.out.println("Melyik író melyik kiadónál publikálta könyveit:");
		ArrayList<String> publisherList = new ArrayList<String>();
        for(int i = 0; i < konyv.getLength(); i++) {
        	Node node = konyv.item(i);
        	if(node.getNodeType() == Node.ELEMENT_NODE) {
        		Element kiado = getKiadoElement(doc, i);
        		Element szerzo = getSzerzoElement(doc,i);
        		String sznev = szerzo.getElementsByTagName("szNév").item(0).getTextContent();
        		if(isInList(publisherList, sznev) == false) {
        			publisherList.add(sznev);
        			System.out.println("	" + sznev + " a " + 
            				kiado.getElementsByTagName("kiNév").item(0).getTextContent() + " kiadónál publikálta könyveit.");		//kiíratás
    				
        		}
        	}
        }
	}
	
	
}
