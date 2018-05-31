package com.bxcodes.java.tenhouapi;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
		//TenhouClient client = TenhouClient.getClient();
		//client.sendString("<HELO name=\"NoName\" tid=\"f0\" sx=\"M\" />\0");
		
		File f = new File("e1.mjv.xml");
		System.out.println(f.exists());
		
		Document doc = stringToDocument(f);
		
		Node n = doc.getFirstChild().getFirstChild();
		
		while(n != null) {
			System.out.print(n.getNodeName());
			
			for(int i = 0; i < n.getAttributes().getLength(); i++) {
				System.out.print(" " + n.getAttributes().item(i).getNodeName() + ":" + n.getAttributes().item(i).getNodeValue());
			}
			
			System.out.println();
			n = n.getNextSibling();
		}
		
		//printDocument(doc, System.out);
	}
	
	public static Document stringToDocument(File f)
			throws SAXException, ParserConfigurationException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		
		return builder.parse(new InputSource(new FileReader(f)));
	}
	
	public static void printDocument(Document doc, OutputStream out) throws IOException, TransformerException {
	    TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer = tf.newTransformer();
	    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	    transformer.transform(new DOMSource(doc), 
	         new StreamResult(new OutputStreamWriter(out, "UTF-8")));
	}

}
