package me.mzhli.javaexample;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class XmlReader {
	private DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
	
	public XmlReader() {
		builderFactory.setValidating(true);
		builderFactory.setIgnoringElementContentWhitespace(true);
		builderFactory.setIgnoringComments(true);
	}
	
	public Document parse(String filePath) {
		Document doc = null;
		try {
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			doc = builder.parse(new File(filePath));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return doc;
	}
	

}
