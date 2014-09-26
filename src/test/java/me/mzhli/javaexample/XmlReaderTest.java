package me.mzhli.javaexample;

import static java.lang.System.out;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import static org.w3c.dom.Node.*;

import org.w3c.dom.NodeList;

import junit.framework.TestCase;
import me.mzhli.javaexample.XmlReader;

public class XmlReaderTest extends TestCase {
	public XmlReaderTest(String testName) {
		super(testName);
	}

//	public static Test suite() {
//		return new TestSuite(XmlReaderTest.class);
//	}

	public void testParse() {
		String filePath = "testfile/sample.xml";
		XmlReader xmlReader = new XmlReader();
		Document doc = xmlReader.parse(filePath);
		assertNotNull(doc);
		
		Element menu = doc.getDocumentElement();
		out.println(menu.getNodeName());
		
		NodeList children = menu.getChildNodes();
		Element salads = (Element)children.item(0);
		out.println("|--" + salads.getTagName());
		NodeList saladlist = salads.getElementsByTagName("Salad");
		for (int iSalad = 0; iSalad < saladlist.getLength(); iSalad++) {
			Element salad = (Element)saladlist.item(iSalad);
			out.print("   |--" + salad.getTagName() + "[");
			String val = salad.getAttribute("id");
			NamedNodeMap attrs = salad.getAttributes();
			for (int iAttr = 0; iAttr < attrs.getLength(); iAttr++) {
				Attr attr = (Attr)attrs.item(iAttr);
				if (iAttr > 0) {
					out.print(",");
				}
				out.print(attr.getName() + "=" + attr.getValue());
			}
			out.println("]");
		}

		//Element soups = (Element)children.item(1);
		
//		Node salads = menu.getFirstChild();
//		assertNotNull(salads);
//		System.out.print("-" + salads.getLocalName());
//		assertEquals("Salads", salads.getNodeName());
//		try {
//			System.out.println("[id=" + salads.getAttributes().getNamedItem("id").getNodeValue());
//			assertEquals("SAL-1", salads.getAttributes().getNamedItem("id").getNodeValue());
//		} catch (DOMException e) {
//			e.printStackTrace();
//		}
	}
}
