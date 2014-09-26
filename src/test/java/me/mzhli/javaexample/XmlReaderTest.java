package me.mzhli.javaexample;

import org.w3c.dom.Document;

import junit.framework.TestCase;


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
