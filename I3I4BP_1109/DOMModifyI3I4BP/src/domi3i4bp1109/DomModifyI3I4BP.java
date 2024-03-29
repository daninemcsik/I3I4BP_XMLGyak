package domi3i4bp1109;

import java.io.File;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;





public class DomModifyI3I4BP {
	public static void main(String argv[]) {

	      try {
	         File inputFile = new File("carsI3I4BP.xml");
	         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	         org.w3c.dom.Document doc = docBuilder.parse(inputFile);
	         Node cars = doc.getFirstChild();
	         Node supercar = doc.getElementsByTagName("supercars").item(0);
	         

	         NamedNodeMap attr = supercar.getAttributes();
	         Node nodeAttr = attr.getNamedItem("company");
	         nodeAttr.setTextContent("Lamborigini");

	         NodeList list = supercar.getChildNodes();
	         
	         for (int temp = 0; temp < list.getLength(); temp++) {
	            Node node = list.item(temp);
	            if (node.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) node;
	               if ("carname".equals(eElement.getNodeName())) {
	                  if("Ferrari 101".equals(eElement.getTextContent())) {
	                     eElement.setTextContent("Lamborigini 001");
	                  }
	                  if("Ferrari 202".equals(eElement.getTextContent()))
	                     eElement.setTextContent("Lamborigini 002");
	               }
	            }
	         }
	         NodeList childNodes = cars.getChildNodes();
	         
	         for(int count = 0; count < childNodes.getLength(); count++) {
	            Node node = childNodes.item(count);
	            
	            if("luxurycars".equals(node.getNodeName()))
	               cars.removeChild(node);
	         }

	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         System.out.println("-----------Modified File-----------");
	         StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
}
