package me.mzhli.javaexample;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


/** 
* @ClassName: XmlReader
* @Description: A utility class for parsing XML file into W3C dom object
*/ 
public class XmlReader {
	/** 
	* @Fields builderFactory : Instance of build factory
	*/ 
	private DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
	
	/** 
	* @Description: Defualt construction initialize instance properly
	*/ 
	public XmlReader() {
		builderFactory.setValidating(true);
		builderFactory.setIgnoringElementContentWhitespace(true);
		builderFactory.setIgnoringComments(true);
	}
	
	/** 
	* @Title: parse
	* @Description: Parse XML file by path
	* @param filePath File path of target XML file
	* @return XML document object
	*/ 
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
