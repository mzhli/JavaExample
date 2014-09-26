package me.mzhli.javaexample;

import java.io.PrintStream;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/** 
* @ClassName: XmlPrinter
* @Description: A utility class to print W3C dom object to pretty format
*/ 
public class XmlPrinter {
	public static final short OUTPUT_MODE_TREE 	= 1;
	public static final short OUTPUT_MODE_XML 	= 2;
	
	private static final String LAST_BRANCH 	= "`-- ";
	private static final String MID_BRANCH 		= "|-- ";
	private static final String MID_BRANCH_INDENT 	= "|   ";
	private static final String LAST_BRANCH_INDENT 	= "    ";
	
	
	private short outputMode = OUTPUT_MODE_TREE;
	
	
	/** 
	* @Title: setOutputMode
	* @Description: Set the output mode
	* @param mode Mode of output
	*/ 
	public void setOutputMode(short mode) {
		outputMode = mode;
	}
	
	/** 
	* @Title: print
	* @Description: Print the xml document to stdout
	* @param doc xml document object to print
	*/ 
	public void print(Document doc) {
		this.print(System.out, doc);
	}
	
	/** 
	* @Title: print
	* @Description: Print the XML document object to output stream
	* @param ps	Output stream
	* @param doc XML document object to print
	*/ 
	public void print(PrintStream ps, Document doc) {
		switch (outputMode) {
		case OUTPUT_MODE_TREE:
			printAsTree(ps, doc);
			break;
		case OUTPUT_MODE_XML:
			//TBD
			break;
		default:
			break;
		}
	}
	
	/** 
	* @Title: printAsTree
	* @Description: Print the XML document as a simple tree structure
	* @param ps Output stream
	* @param doc XML document object to print
	*/ 
	protected static void printAsTree(PrintStream ps, Document doc) {
		Element root = doc.getDocumentElement();
		ps.print(root.getTagName());
		if (root.hasAttributes()) {
			printAttributes(ps, root);
		} else {
			ps.print("\n");
		}
		
		NodeList children = root.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Element child = (Element)children.item(i);
			if (i != children.getLength() - 1) {
				printSubTree(ps, child, "", false);
			} else {
				printSubTree(ps, child, "", true);
			}
		}
	}
	
	/** 
	* @Title: printSubTree
	* @Description: Print element nodes as sub tree
	* @param ps Output stream
	* @param subtree Element node to print
	* @param indent The indent string preceding every branch of sub tree
	* @param isLastBranch Tell if the element is the last child or branch of parent
	*/ 
	private static void printSubTree(PrintStream ps, Element subtree, String indent, boolean isLastBranch) {
		String subindent = null;
		if (!isLastBranch) {
			ps.print(indent + MID_BRANCH + subtree.getTagName());
			subindent = indent + MID_BRANCH_INDENT;
		} else {
			ps.print(indent + LAST_BRANCH + subtree.getTagName());
			subindent = indent + LAST_BRANCH_INDENT;
		}
		
		if (subtree.hasAttributes()) {
			printAttributes(ps, subtree);
		} else {
			Node firstChild = subtree.getFirstChild();
			if (firstChild == null || firstChild.getNodeType() != Node.TEXT_NODE) {
				ps.print("\n");
			}
		}
		
		NodeList children = subtree.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeType() == Node.TEXT_NODE) {
				ps.print(": \"" + child.getNodeValue() + "\"\n");
			} else {
				if (i != children.getLength() - 1) {
					printSubTree(ps, (Element)child, subindent, false);
				} else {
					printSubTree(ps, (Element)child, subindent, true);
				}
			}
		}
	}
	
	/** 
	* @Title: printAttributes
	* @Description: Print element's attributes to output stream
	* @param ps Output stream
	* @param elem Target element to print
	*/ 
	private static void printAttributes(PrintStream ps, Element elem) {
		ps.print("[");
		NamedNodeMap attrs = elem.getAttributes();
		for (int i = 0; i < attrs.getLength(); i++) {
			if (i > 0) {
				ps.print(",");
			}
			Attr attr = (Attr)attrs.item(i);
			ps.print(attr.getName() + "=" + attr.getValue());
		}
		ps.print("]\n");
	}

}
