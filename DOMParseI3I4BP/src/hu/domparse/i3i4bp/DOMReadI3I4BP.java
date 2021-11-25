package hu.domparse.i3i4bp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;



public class DOMReadI3I4BP {

		public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			try {
				
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = (Document) db.parse(new File("src\\XMLI3I4BP.xml"));
				doc.getDocumentElement().normalize();
				
				System.out.println("Gyökérelem :" + doc.getDocumentElement().getNodeName());		//gyökérelem meghatározása
				System.out.println();
				
				NodeList konyv = doc.getElementsByTagName("Könyv");		//Könyv elemek elérése
				for(int i = 0; i < konyv.getLength(); i++) {
					Node node = konyv.item(i);
					if(node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						
						String id = element.getAttribute("kkod");
						
						String datum = element.getElementsByTagName("kiadási_dátum").item(0).getTextContent();		//Könyv elem belsõ tulajdonságainak elérése
						String cim = element.getElementsByTagName("cím").item(0).getTextContent();					 
						String kategoria = element.getElementsByTagName("kategória").item(0).getTextContent();		 
						
						System.out.println("Jelenlegi elem :" + node.getNodeName());	//Ezek kiíratása
		                System.out.println("Könyv kód : " + id);
		                System.out.println("Dátum : " + datum);
		                System.out.println("Cím : " + cim);
		                System.out.println("Kategória : " + kategoria);
		                System.out.println();
		                
		                NodeList szerzo = doc.getElementsByTagName("Szerzõ");		//Szerzõ elem elérése
		                Node Sznode = szerzo.item(i);
						if(Sznode.getNodeType() == Node.ELEMENT_NODE) {
							Element SZelement = (Element) Sznode;
							
							String sid = SZelement.getAttribute("szkod");	
							String szElerhetoseg = SZelement.getElementsByTagName("szElérhetõség").item(0).getTextContent();	//Szerzõ elem belsõ 
							String szNev = SZelement.getElementsByTagName("szNév").item(0).getTextContent();					//tulajdonságainak elérése
							String szEletkor = SZelement.getElementsByTagName("szÉletkor").item(0).getTextContent();
								
							System.out.println("Szerzõ kódja : " + sid);
							System.out.println("Szerzõ elérhetõsége : " + szElerhetoseg);		//Ezek kiíratása
				            System.out.println("Szerzõ neve : " + szNev);
				            System.out.println("Szerzõ életkora : " + szEletkor);
				            System.out.println();

		                
		                }
						
						NodeList kiado = doc.getElementsByTagName("Kiadó");		//Kiadó elem elérése
		                Node Knode = kiado.item(i);
						if(Knode.getNodeType() == Node.ELEMENT_NODE) {
							Element Kelement = (Element) Knode;
							
							String kikod = Kelement.getAttribute("kikod");	
							String kiVaros = Kelement.getElementsByTagName("kiVáros").item(0).getTextContent();	//Kiadó elem belsõ tulajdonságainak elérése
							String kiCim = Kelement.getElementsByTagName("kiCím").item(0).getTextContent();
							String kiNev = Kelement.getElementsByTagName("kiNév").item(0).getTextContent();
								
							System.out.println("Kiadó kódja : " + kikod);
							System.out.println("Kiadó városa : " + kiVaros);	//Ezek kiíratása
				            System.out.println("Kiadó címe : " + kiCim);
				            System.out.println("Kiadó neve : " + kiNev);
				            System.out.println();	                
		                }
						
						NodeList kbolt = doc.getElementsByTagName("Könyvesbolt");	//Könyvesbolt elem elérése
		                Node Kbnode = kbolt.item(i);
						if(Kbnode.getNodeType() == Node.ELEMENT_NODE) {
							Element KBelement = (Element) Kbnode;
							
							String kbszam = KBelement.getAttribute("telefonszam");	
							String kbVaros = KBelement.getElementsByTagName("kbVáros").item(0).getTextContent();	//Könyvesbolt elem belsõ 
							String kbCim = KBelement.getElementsByTagName("kbCím").item(0).getTextContent();		//tulajdonságainak elérése
							String kbNev = KBelement.getElementsByTagName("kbNév").item(0).getTextContent();
								
							System.out.println("Könyvesbolt telefonszáma : " + kbszam);
							System.out.println("Könyvesbolt városa : " + kbVaros);
				            System.out.println("Könyvesbolt címe : " + kbCim);	//Ezek kiíratása
				            System.out.println("Könyvesbolt neve : " + kbNev);
				            System.out.println();	                
		                }
						
						NodeList vasarlo = doc.getElementsByTagName("Vásárló"); //Vásárló elem elérése
		                Node Vnode = vasarlo.item(i);
						if(Vnode.getNodeType() == Node.ELEMENT_NODE) {
							Element Velement = (Element) Vnode;
							
							String vNev = Velement.getElementsByTagName("vNév").item(0).getTextContent();	//Vásárló elem belsõ tulajdonságainak elérése
							String vEletkor = Velement.getElementsByTagName("vÉletkor").item(0).getTextContent();
								
							System.out.println("Vásárló neve : " + vNev);
							System.out.println("Vásárló életkora : " + vEletkor); //Vásárló kiíratása

							NodeList lakcim = doc.getElementsByTagName("lakcím");	//Vásárló elem lakcím összetett elemének elérése
			                Node lnode = lakcim.item(i);
							if(lnode.getNodeType() == Node.ELEMENT_NODE) {
								Element lelement = (Element) lnode;
								
								String vVaros = Velement.getElementsByTagName("vVáros").item(0).getTextContent(); //lakcím elem belsõ tulajdonságainak elérése
								String vUtca = Velement.getElementsByTagName("vUtca").item(0).getTextContent();
								String vHazszam = Velement.getElementsByTagName("vHázszám").item(0).getTextContent();
									
								System.out.println("Vásárló városa : " + vVaros);
								System.out.println("Vásárló utcája : " + vUtca);
								System.out.println("Vásárló házszáma : " + vHazszam); //Ezek kiíratása
								System.out.println();
							}
							
						}
		                System.out.println("-----------------------------------------------------------------");
					}
				}
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
}
