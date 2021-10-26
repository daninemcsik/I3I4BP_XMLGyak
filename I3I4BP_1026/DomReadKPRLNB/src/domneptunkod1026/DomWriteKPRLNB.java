package domneptunkod1026;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.tools.sjavac.Transformer;


	public class DomWriteKPRLNB {
		public DomWriteKPRLNB() {}
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		
		Element root = doc.createElementNS("domkprlnb", "users");
		doc.appendChild(root);
		root.appendChild(createUser(doc, "1", "Pal", "Kiss", "programmer"));
		root.appendChild(createUser(doc, "2", "Pal", "Kiss", "programmer"));
		root.appendChild(createUser(doc, "3", "Pal", "Kiss", "programmer"));
			
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		transf.setOutputProperty(OutputKeys.ENCODING,  "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT,  "yes");
		transf.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");
		
		DOMSource source = new DOMSource(doc);
		
		File myFile = new File("users1.xml");
		
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		
		transf.transform(source,  console);
		transf.transform(source,  file);
	}
	
	private static Node createUser(Document doc, String id, String firstName, String lastName, String profession) {
		Element user = doc.createElement("user");
		
		user.setAttribute("id", id);
		user.appendChild(createUserElement(doc,"firstname", firstName));
		user.appendChild(createUserElement(doc,"firstname", lastName));
		user.appendChild(createUserElement(doc,"firstname", profession));
		
		return user;
	}
	
	private static Node createUserElement(Document doc, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
}