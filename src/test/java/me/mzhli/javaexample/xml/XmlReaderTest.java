package me.mzhli.javaexample.xml;

import junit.framework.TestCase;

import org.w3c.dom.Document;


public class XmlReaderTest extends TestCase {
	public XmlReaderTest(String testName) {
		super(testName);
	}

	public void testParse() {
		String filePath = "testfile/sample.xml";
		XmlReader xmlReader = new XmlReader();
		Document doc = xmlReader.parse(filePath);
		assertNotNull(doc);

//		XmlPrinter printer = new XmlPrinter();
//		printer.print(doc);
	}
}
