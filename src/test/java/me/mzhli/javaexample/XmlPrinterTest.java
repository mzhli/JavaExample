package me.mzhli.javaexample;

import org.w3c.dom.Document;

import junit.framework.TestCase;


public class XmlPrinterTest extends TestCase {
	
	public XmlPrinterTest(String testname) {
		super(testname);
	}
	
	public void testPrint() {
		XmlReader reader = new XmlReader();
		Document doc = reader.parse("testfile/sample.xml");
		assertNotNull(doc);
		XmlPrinter printer = new XmlPrinter();
		printer.print(doc);
	}
}
