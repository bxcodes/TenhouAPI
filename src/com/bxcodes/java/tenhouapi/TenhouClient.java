package com.bxcodes.java.tenhouapi;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.Socket;

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
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class TenhouClient {

	
	//http://m77.hatenablog.com/entry/2017/05/21/214529
	public static TenhouClient getClient() {
		try {
			TenhouClient client = new TenhouClient();
			return client;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void sendString(String s) throws IOException {
		osw.write(s);
		osw.flush();
	}

	private TenhouClient() throws IOException {
		socket = new Socket("133.242.10.78", 10080);
		reader = new TenhouStreamReader(socket.getInputStream());

		osw = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						handleXML(reader.nextLine());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
				}

			}
		}).start();
	}

	private void handleXML(String msg) {
		System.out.println(msg);
		
		try {
			Document doc = stringToDocument(msg);
			String tag = doc.getChildNodes().item(0).getNodeName();
			
			if(tag.equals("HELO")) {
				String auth = doc.getChildNodes().item(0).getAttributes().getNamedItem("auth").getNodeValue();
				String username = doc.getChildNodes().item(0).getAttributes().getNamedItem("uname").getNodeValue();
				String a = "<AUTH val=\"" + TenhouUtil.decodeAuth(auth) + "\"/>\0";
				System.out.println(a);
				
				this.sendString(a);
				
				//Thread.sleep(700);
				
				this.sendString("<PXR V=\"0\" />\0");
				
				this.sendString("<CHAT text=\"%2Flobby%204000\" />\0");
				
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public static Document stringToDocument(final String xmlSource)
			throws SAXException, ParserConfigurationException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		
		return builder.parse(new InputSource(new StringReader(xmlSource.replaceAll("&", "&amp;"))));
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

	private Socket socket;
	private OutputStreamWriter osw;
	private TenhouStreamReader reader;
}
