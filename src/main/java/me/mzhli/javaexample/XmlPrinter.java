package me.mzhli.javaexample;

import java.io.PrintStream;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author mzhli
 *
 */
public class XmlPrinter {
	public static final short OUTPUT_MODE_TREE 	= 1;
	public static final short OUTPUT_MODE_XML 	= 2;
	
	private static final String LAST_BRANCH 	= "`-- ";
	private static final String MID_BRANCH 		= "|-- ";
	private static final String MID_BRANCH_INDENT 	= "|   ";
	private static final String LAST_BRANCH_INDENT 	= "    ";
	
	
	private short outputMode = OUTPUT_MODE_TREE;
	
	public XmlPrinter() {
	}
	
	public void setOutputMode(short mode) {
		outputMode = mode;
	}
	
	public void print(Document doc) {
		this.print(System.out, doc);
	}
	
	public void print(PrintStream ps, Document doc) {
		switch (outputMode) {
		case OUTPUT_MODE_TREE:
			printInTree(ps, doc);
			break;
		case OUTPUT_MODE_XML:
			//TBD
			break;
		default:
			break;
		}
	}
	
	protected static void printInTree(PrintStream ps, Document doc) {
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
