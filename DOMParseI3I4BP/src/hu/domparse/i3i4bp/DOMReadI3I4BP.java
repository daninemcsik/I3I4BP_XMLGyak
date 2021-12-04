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
				
				System.out.println("Gy�k�relem :" + doc.getDocumentElement().getNodeName());		//gy�k�relem meghat�roz�sa
				System.out.println();
				
				NodeList konyv = doc.getElementsByTagName("K�nyv");		//K�nyv elemek el�r�se
				for(int i = 0; i < konyv.getLength(); i++) {
					Node node = konyv.item(i);
					if(node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						
						String id = element.getAttribute("kkod");
						
						String datum = element.getElementsByTagName("kiad�si_d�tum").item(0).getTextContent();		//K�nyv elem bels� tulajdons�gainak el�r�se
						String cim = element.getElementsByTagName("c�m").item(0).getTextContent();					 
						String kategoria = element.getElementsByTagName("kateg�ria").item(0).getTextContent();		 
						
						System.out.println("Jelenlegi elem :" + node.getNodeName());	//Ezek ki�rat�sa
		                System.out.println("K�nyv k�d : " + id);
		                System.out.println("D�tum : " + datum);
		                System.out.println("C�m : " + cim);
		                System.out.println("Kateg�ria : " + kategoria);
		                System.out.println();
		                
		                NodeList szerzo = doc.getElementsByTagName("Szerz�");		//Szerz� elem el�r�se
		                Node Sznode = szerzo.item(i);
						if(Sznode.getNodeType() == Node.ELEMENT_NODE) {
							Element SZelement = (Element) Sznode;
							
							String sid = SZelement.getAttribute("szkod");	
							String szElerhetoseg = SZelement.getElementsByTagName("szEl�rhet�s�g").item(0).getTextContent();	//Szerz� elem bels� 
							String szNev = SZelement.getElementsByTagName("szN�v").item(0).getTextContent();					//tulajdons�gainak el�r�se
							String szEletkor = SZelement.getElementsByTagName("sz�letkor").item(0).getTextContent();
								
							System.out.println("Szerz� k�dja : " + sid);
							System.out.println("Szerz� el�rhet�s�ge : " + szElerhetoseg);		//Ezek ki�rat�sa
				            System.out.println("Szerz� neve : " + szNev);
				            System.out.println("Szerz� �letkora : " + szEletkor);
				            System.out.println();

		                
		                }
						
						NodeList kiado = doc.getElementsByTagName("Kiad�");		//Kiad� elem el�r�se
		                Node Knode = kiado.item(i);
						if(Knode.getNodeType() == Node.ELEMENT_NODE) {
							Element Kelement = (Element) Knode;
							
							String kikod = Kelement.getAttribute("kikod");
							String kkod = Kelement.getElementsByTagName("kkod").item(0).getTextContent();
							String kiVaros = Kelement.getElementsByTagName("kiV�ros").item(0).getTextContent();	//Kiad� elem bels� tulajdons�gainak el�r�se
							String kiCim = Kelement.getElementsByTagName("kiC�m").item(0).getTextContent();
							String kiNev = Kelement.getElementsByTagName("kiN�v").item(0).getTextContent();
								
							System.out.println("Kiad� k�dja : " + kikod);
							System.out.println("Kiadott k�nyv k�dja : " + kkod);
							System.out.println("Kiad� v�rosa : " + kiVaros);	//Ezek ki�rat�sa
				            System.out.println("Kiad� c�me : " + kiCim);
				            System.out.println("Kiad� neve : " + kiNev);
				            System.out.println();	                
		                }
						
						NodeList kbolt = doc.getElementsByTagName("K�nyvesbolt");	//K�nyvesbolt elem el�r�se
		                Node Kbnode = kbolt.item(i);
						if(Kbnode.getNodeType() == Node.ELEMENT_NODE) {
							Element KBelement = (Element) Kbnode;
							
							String kbkod = KBelement.getAttribute("kbkod");	
							String kbVaros = KBelement.getElementsByTagName("kbV�ros").item(0).getTextContent();	//K�nyvesbolt elem bels� 
							String kbCim = KBelement.getElementsByTagName("kbC�m").item(0).getTextContent();		//tulajdons�gainak el�r�se
							String kbNev = KBelement.getElementsByTagName("kbN�v").item(0).getTextContent();
								
							System.out.println("K�nyvesbolt k�dja : " + kbkod);
							System.out.println("K�nyvesbolt v�rosa : " + kbVaros);
				            System.out.println("K�nyvesbolt c�me : " + kbCim);	//Ezek ki�rat�sa
				            System.out.println("K�nyvesbolt neve : " + kbNev);
				            System.out.println();	                
		                }
						
						NodeList tszam = doc.getElementsByTagName("Telefonsz�m");	//Telefonsz�m elem el�r�se
		                Node tsznode = tszam.item(i);
						if(tsznode.getNodeType() == Node.ELEMENT_NODE) {
							Element TSZelement = (Element) tsznode;
							
							String kbkod = TSZelement.getElementsByTagName("kbkod").item(0).getTextContent();					//Telefonsz�m elem bels� 
							String telefonszam = TSZelement.getElementsByTagName("telefonszam").item(0).getTextContent();		//tulajdons�gainak el�r�se

				            System.out.println("K�nyvesbolt k�dja : " + kbkod);	//Ezek ki�rat�sa
				            System.out.println("K�nyvesbolt telefonsz�m : " + telefonszam);
				            System.out.println();	                
		                }
						
						
						NodeList beszerez = doc.getElementsByTagName("Beszerez");	//Beszerez kapcsolat-elem el�r�se
		                Node Besznode = beszerez.item(i);
						if(Besznode.getNodeType() == Node.ELEMENT_NODE) {
							Element Beszelement = (Element) Besznode;
							
							String kkod = Beszelement.getElementsByTagName("kkod").item(0).getTextContent();
							String kbkod = Beszelement.getElementsByTagName("kbkod").item(0).getTextContent();
							String bId�pont = Beszelement.getElementsByTagName("bId�pont").item(0).getTextContent();	//Beszerez kapcsolat-elem bels� 
							String bDarab = Beszelement.getElementsByTagName("bDarab").item(0).getTextContent();		//tulajdons�gainak el�r�se
							String b�r = Beszelement.getElementsByTagName("b�r").item(0).getTextContent();
							String bK�lts�g = Beszelement.getElementsByTagName("bK�lts�g").item(0).getTextContent();	
							
							System.out.println("Beszerzett k�nyv k�dja : " + kkod);
							System.out.println("Beszerz� k�nyvesbolt k�dja : " + kbkod);
							System.out.println("Beszerz�s id�pontja : " + bId�pont);//Ezek ki�rat�sa
							System.out.println("Beszerzett darabsz�m : " + bDarab);
							System.out.println("Beszerz�si �r : " + b�r);
							System.out.println("Beszerz�si �ssz k�lts�g : " + bK�lts�g);
				            System.out.println();	                
		                }
						
						NodeList elad�s = doc.getElementsByTagName("Elad�s");	//Elad�s kapcsolat-elem el�r�se
		                Node Eladnode = elad�s.item(i);
						if(Eladnode.getNodeType() == Node.ELEMENT_NODE) {
							Element Eladelement = (Element) Eladnode;
							
							String kkod = Eladelement.getElementsByTagName("kkod").item(0).getTextContent();
							String kbkod = Eladelement.getElementsByTagName("kbkod").item(0).getTextContent();
							String eId�pont = Eladelement.getElementsByTagName("eId�pont").item(0).getTextContent();	//Elad�s kapcsolat-elem bels� 
							String eDarab = Eladelement.getElementsByTagName("eDarab").item(0).getTextContent();		//tulajdons�gainak el�r�se
							String e�r = Eladelement.getElementsByTagName("e�r").item(0).getTextContent();
							
							System.out.println("Eladott k�nyv k�dja : " + kkod);
							System.out.println("Elad� k�nyvesbolt k�dja : " + kbkod);
							System.out.println("Elad�s id�pontja : " + eId�pont);//Ezek ki�rat�sa
							System.out.println("Eladott darabsz�m : " + eDarab);
							System.out.println("Elad�si �r : " + e�r);
				            System.out.println();	                
		                }
						
						NodeList vasarol = doc.getElementsByTagName("V�s�rol");	//V�s�rol kapcsolat-elem el�r�se
		                Node vsnode = vasarol.item(i);
						if(vsnode.getNodeType() == Node.ELEMENT_NODE) {
							Element VSelement = (Element) vsnode;
							
							String kbkod = VSelement.getElementsByTagName("kbkod").item(0).getTextContent();					//V�s�rol kapcsolat-elem bels� 
							String vkod = VSelement.getElementsByTagName("vkod").item(0).getTextContent();						//tulajdons�gainak el�r�se

				            System.out.println("K�nyvesbolt k�dja : " + kbkod);	//Ezek ki�rat�sa
				            System.out.println("V�s�rl� k�dja : " + vkod);
				            System.out.println();	                
		                }
						
						
						
						NodeList vasarlo = doc.getElementsByTagName("V�s�rl�"); //V�s�rl� elem el�r�se
		                Node Vnode = vasarlo.item(i);
						if(Vnode.getNodeType() == Node.ELEMENT_NODE) {
							Element Velement = (Element) Vnode;
							
							String vNev = Velement.getElementsByTagName("vN�v").item(0).getTextContent();	//V�s�rl� elem bels� tulajdons�gainak el�r�se
							String vEletkor = Velement.getElementsByTagName("v�letkor").item(0).getTextContent();
								
							System.out.println("V�s�rl� neve : " + vNev);
							System.out.println("V�s�rl� �letkora : " + vEletkor); //V�s�rl� ki�rat�sa

							NodeList lakcim = doc.getElementsByTagName("lakc�m");	//V�s�rl� elem lakc�m �sszetett elem�nek el�r�se
			                Node lnode = lakcim.item(i);
							if(lnode.getNodeType() == Node.ELEMENT_NODE) {
								Element lelement = (Element) lnode;
								
								String vVaros = Velement.getElementsByTagName("vV�ros").item(0).getTextContent(); //lakc�m elem bels� tulajdons�gainak el�r�se
								String vUtca = Velement.getElementsByTagName("vUtca").item(0).getTextContent();
								String vHazszam = Velement.getElementsByTagName("vH�zsz�m").item(0).getTextContent();
									
								System.out.println("V�s�rl� v�rosa : " + vVaros);
								System.out.println("V�s�rl� utc�ja : " + vUtca);
								System.out.println("V�s�rl� h�zsz�ma : " + vHazszam); //Ezek ki�rat�sa
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
