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
			        
	        System.out.println("K�nyvek c�me, amik irodalm kateg�ri�ba sorolhat�k:");
	        listLiteratureBookNames(doc);
	        System.out.println("-----------------------------------------------------------------\n");	//ki�rat�s
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
	//40-83 sor Szerz�, Kiad�, K�nyvesbolt, V�s�rl� elem el�r�se k�dism�tl�s elker�l�se �rdek�ben.
	static Element getSzerzoElement(Document doc, int i) {
		NodeList nodelist = doc.getElementsByTagName("Szerz�");
        Node node = nodelist.item(i);
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			return element;
		}else {
			return null;
		}
	}
	
	static Element getKiadoElement(Document doc, int i) {
		NodeList nodelist = doc.getElementsByTagName("Kiad�");
        Node node = nodelist.item(i);
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			return element;
		}else {
			return null;
		}
	}
	
	static Element getKonyvesboltElement(Document doc, int i) {
		NodeList nodelist = doc.getElementsByTagName("K�nyvesbolt");
        Node node = nodelist.item(i);
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			return element;
		}else {
			return null;
		}
	}
	
	static Element getVasarloElement(Document doc, int i) {
		NodeList nodelist = doc.getElementsByTagName("V�s�rl�");
        Node node = nodelist.item(i);
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			return element;
		}else {
			return null;
		}
	}
	
	static void listLiteratureBookNames(Document doc) {	//Irodalom kateg�ri�j� k�nyvek nev�nek lek�rdez�se.
		NodeList konyv = doc.getElementsByTagName("K�nyv");
        for(int i = 0; i < konyv.getLength(); i++) {
        	Node node = konyv.item(i);
        	if(node.getNodeType() == Node.ELEMENT_NODE) {
        		Element element = (Element) node;
        		//ki�rat�s
        		if((element.getElementsByTagName("kateg�ria").item(0).getTextContent()).equalsIgnoreCase("Irodalom")) {
        			System.out.println("	" + element.getElementsByTagName("c�m").item(0).getTextContent());
        		}
        	}
        }
	}
	
	static void listKerryFisherBookBuyersNamesAndAges(Document doc) { //Kerry Fisher k�nyveket v�s�rl� emberek nev�nek �s kor�nak lek�rdez�se.
		NodeList konyv = doc.getElementsByTagName("K�nyv");
		System.out.println("Kerry Fisher k�nyveit �k vett�k meg:");
        for(int i = 0; i < konyv.getLength(); i++) {
        	Node node = konyv.item(i);
        	if(node.getNodeType() == Node.ELEMENT_NODE) {
        		Element szerzo = getSzerzoElement(doc, i);
        		Element vasarlo = getVasarloElement(doc, i);
        		//ki�rat�s
        		if((szerzo.getElementsByTagName("szN�v").item(0).getTextContent()).equalsIgnoreCase("Kerry Fisher")) {
        			System.out.println("	V�s�rl� neve: " + vasarlo.getElementsByTagName("vN�v").item(0).getTextContent()
        					+ ", " + vasarlo.getElementsByTagName("v�letkor").item(0).getTextContent() + " �ves."
        					);
        		}
				
        		
        	}
        }
	}
	
	static void listShopPhonenumberAddressAndName(Document doc) { //Lek�rdez�s, hogy Ken Follett melyik k�nyveit hol adt�k el, ezek a helyek adatai.
		NodeList konyv = doc.getElementsByTagName("K�nyv");
		System.out.println("Ken Follett al�bbi k�nyveit adt�k el: ");
        for(int i = 0; i < konyv.getLength(); i++) {
        	Node node = konyv.item(i);
        	if(node.getNodeType() == Node.ELEMENT_NODE) {	
        		Element kbolt = getKonyvesboltElement(doc, i);
        		Element szerzo = getSzerzoElement(doc,i);
        		Element element =(Element) node;
        		//ki�rat�s
        		if((szerzo.getElementsByTagName("szN�v").item(0).getTextContent()).equalsIgnoreCase("Ken Follett")){
        			System.out.println("	K�nyv c�me: " + element.getElementsByTagName("c�m").item(0).getTextContent() + "\nAz al�bbi k�nyvesboltba:");
        			System.out.println("	Neve: " + kbolt.getElementsByTagName("kbN�v").item(0).getTextContent() + "\n	C�me: " +
        			kbolt.getElementsByTagName("kbV�ros").item(0).getTextContent() + ", " + kbolt.getElementsByTagName("kbC�m").item(0).getTextContent()
        			+ "\n	Telefonsz�ma: " + kbolt.getAttribute("telefonszam"));
        			System.out.println();
        			
        		}
				
        		
        	}
        }
	}
	
	static boolean isInList(ArrayList<String> a, String str) {	//Megn�zi, hogy egy n�v benne van-e m�r az arraylist-ben, ism�tl�d�s elker�l�se �rdek�ben.
		boolean state = false;									//Ez csak az�rt sz�ks�ges bele, mert �gy �rtam meg az xml dokumentumot, hogy egy szerz�h�z egy 
																//kiad� tartozik, teh�t nem lehet 2 k�l�nb�z� kiad� egy szerz�h�z.
		for(int i = 0; i < a.size(); i++) {						//�gy 5 sort �r ki, szerz� neve �s a hozz� vett kiad�. Ha nem lenne ez benne, akkor mindent 2x �rna ki.
			if(a.get(i).equalsIgnoreCase(str)) {				//Ha egy szerz� t�bb kiad�hoz is tartozna, akkor m�sk�pp k�ne csin�lni.
				state = true;
				break;
			}else {
				state = false;
			}
		}
		return state;
	}
	
	static void listPublisherAndWriterNames(Document doc) {	//Lek�rdez�s, ami megadja, hogy melyik �r� melyik kiad�n�l publik�lta k�nyveit.
		NodeList konyv = doc.getElementsByTagName("K�nyv");
		System.out.println("Melyik �r� melyik kiad�n�l publik�lta k�nyveit:");
		ArrayList<String> publisherList = new ArrayList<String>();
        for(int i = 0; i < konyv.getLength(); i++) {
        	Node node = konyv.item(i);
        	if(node.getNodeType() == Node.ELEMENT_NODE) {
        		Element kiado = getKiadoElement(doc, i);
        		Element szerzo = getSzerzoElement(doc,i);
        		String sznev = szerzo.getElementsByTagName("szN�v").item(0).getTextContent();
        		if(isInList(publisherList, sznev) == false) {
        			publisherList.add(sznev);
        			System.out.println("	" + sznev + " a " + 
            				kiado.getElementsByTagName("kiN�v").item(0).getTextContent() + " kiad�n�l publik�lta k�nyveit.");		//ki�rat�s
    				
        		}
        	}
        }
	}
	
	
}
