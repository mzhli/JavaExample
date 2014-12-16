package me.mzhli.javaexample.lang;

import java.net.URL;
import java.net.URLClassLoader;

public class InnerClass {

	public InnerClass() {
	}

	public static void main(String[] args) {
		InnerClass inCls = new InnerClass();
		URLClassLoader cl = (URLClassLoader)inCls.getClass().getClassLoader();
		for (URL url : cl.getURLs()) {
			System.out.println(url.toString());
		}
	}

}
