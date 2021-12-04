package SaxI3I4BP1019;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxI3I4BP {
	public static void main(String[] args) {

	      try {
	         File inputFile = new File("szemelyekI3I4BP.xml");
	         SAXParserFactory factory = SAXParserFactory.newInstance();
	         SAXParser saxParser = factory.newSAXParser();
	         UserHandler userhandler = new UserHandler();
	         saxParser.parse(inputFile, userhandler);     
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }   
	}
	class UserHandler extends DefaultHandler {
	   boolean bSzemely = false;
	   boolean bSzemelyid = false;
	   boolean bNev = false;
	   boolean bKor = false;
	   boolean bVaros = false;
	   String id = null;

	   @Override
	   public void startElement(String uri, String localName, String qName, Attributes attributes)throws SAXException {
	      
		  if(qName.equalsIgnoreCase("szemelyek")) {
			  System.out.println("szemelyek start");
		  } else if(qName.equalsIgnoreCase("szemely")) {
			  bSzemely = true;
		  } else if (qName.equalsIgnoreCase("szemely_id")) {
	         id = attributes.getValue("id");
	         System.out.println("  szemely, {id:" + id + "} start");
	      } else if (qName.equalsIgnoreCase("nev")) {
	         bNev = true;
	      } else if (qName.equalsIgnoreCase("kor")) {
	         bKor = true;
	      } else if (qName.equalsIgnoreCase("varos")) {
	         bVaros = true;
	      }
	   }

	   @Override
	   public void endElement(String uri, String localName, String qName) throws SAXException {			      
			      if (qName.equalsIgnoreCase("szemely_id")) {

			         if(("sz_01").equals(id) && qName.equalsIgnoreCase("szemely_id")) {
			        	System.out.println("  szemely end");
			         }
			      }
	   }

	   @Override
	   public void characters(char ch[], int start, int length) throws SAXException {

	      if (bNev) {
	         System.out.println("    nev start");
	         System.out.println("      " + new String(ch, start, length));
	         System.out.println("    nev end");
	         bNev = false;
	      } else if (bKor) {
	    	 System.out.println("    kor start");
		     System.out.println("      " + new String(ch, start, length));
		     System.out.println("    kor end");
	         bKor = false;
	      } else if (bVaros) {
	    	 System.out.println("    varos start");
		     System.out.println("      " + new String(ch, start, length));
		     System.out.println("    varos end");
	         bVaros = false;
	      } 
	   }
	}