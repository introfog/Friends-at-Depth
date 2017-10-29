package com.game.addition.parsers;

import com.game.GameSystem;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URLDecoder;

public abstract class ParseBasis{
	protected static Document getDocument (String fromIDEA, String fromDesktop){
		Document document;
		try{
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance ().newDocumentBuilder ();
			
			
			URLDecoder decoder = new URLDecoder ();
			StringBuilder path = new StringBuilder (decoder.decode (ParseLevel.class.getProtectionDomain ().getCodeSource ().getLocation ().getPath ()));
			int index = path.lastIndexOf (GameSystem.NAME_JAR_ARCHIVE);
			if (index == -1){
				document = documentBuilder.parse (fromIDEA);
			}
			else{
				path.delete (index, path.length ());
				path.append (fromDesktop);
				document = documentBuilder.parse (path.toString ());
			}
			return document;
			
		}
		catch (SAXException | IOException | ParserConfigurationException ex){
			ex.printStackTrace (System.out);
			return null;
		}
	}
}
