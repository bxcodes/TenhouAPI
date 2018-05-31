package com.bxcodes.java.tenhouapi;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class TenhouLog {
	
	private TenhouLog() {}
	
	public static TenhouLog getLog(File f) {
		
		try {
			TenhouLog l = new TenhouLog();
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new FileReader(f)));
			
			Node n = doc.getFirstChild().getChildNodes().item(2);
			l.playerName[0] = URLDecoder.decode(n.getAttributes().getNamedItem("n0").getNodeValue(), "UTF8");
			l.playerName[1] = URLDecoder.decode(n.getAttributes().getNamedItem("n1").getNodeValue(), "UTF8");
			l.playerName[2] = URLDecoder.decode(n.getAttributes().getNamedItem("n2").getNodeValue(), "UTF8");
			l.playerName[3] = URLDecoder.decode(n.getAttributes().getNamedItem("n3").getNodeValue(), "UTF8");
			
			n = doc.getFirstChild().getChildNodes().item(4);
			
			List<Node> list = null;
			
			while(n != null) {
				if(n.getNodeName().equals("INIT")) {
					if(list != null) l.games.add(list);
					list = new ArrayList<Node>();
				}
				list.add(n);
				n = n.getNextSibling();
			}
			
			if(!l.games.contains(list)) l.games.add(list);
			
			return l;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public List<List<Node>> games = new ArrayList<List<Node>>();
	public String[] playerName = new String[4];
}
