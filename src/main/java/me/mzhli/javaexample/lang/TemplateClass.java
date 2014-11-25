package me.mzhli.javaexample.lang;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TemplateClass<T extends Comparable<T>> {

	private T val;
	private T[] arr;  // This will be "Comparable[] arr;" after erasure
	
	@SuppressWarnings("unchecked")
	public TemplateClass(Class<T> cls) throws Exception {
		// You cannot directly use type T as the class name to call T.class.newInstance()
		// Type T will be erased and replaced by Object. For creating new instance of T,
		// the caller needs to get the class object of type T by himself and pass in.
		val = cls.newInstance();
		arr = (T[])Array.newInstance(cls, 5);
	}
	
	// This will be "public Comparable getVal()" after erasure
	public T getVal() {
		return val;
	}
	
	// This will be "public Comparable[] getArray()" after erasure
	public T[] getArray() {
		return arr;
	}
	
	public void setVal(T newVal) {
		if (val.compareTo(newVal) < 0) // Assign only if new value is larger
			val = newVal;
	}
	
	public void setArray(T[] newArray) {
		System.arraycopy(newArray, 0, arr, 0, Math.min(newArray.length, arr.length));
	}

	public static void main(String[] args) throws Exception {
		TemplateClass<String> t = new TemplateClass<String>(String.class);
		System.out.printf("Old Value is '%s'\n", t.getVal());
		t.setVal("abc");
		System.out.printf("#1 New Value is '%s'\n", t.getVal());
		t.setVal("AAA");
		System.out.printf("#2 New Value is '%s'\n", t.getVal());
		t.setVal("bcd");
		System.out.printf("#3 New Value is '%s'\n", t.getVal());
		
		System.out.printf("Old Array is '%s'\n", Arrays.toString(t.getArray()));
		System.out.printf("Component Type is '%s'\n", t.getArray().getClass().getComponentType().getCanonicalName());
		t.setArray(new String[]{"a", "b", "c", "d", "e"});
		System.out.printf("#1 New Array Value is '%s'\n", Arrays.toString(t.getArray()));
		t.setArray(new String[]{"A", "B", "C", "D", "E"});
		System.out.printf("#2 New Array Value is '%s'\n", Arrays.toString(t.getArray()));
		t.setArray(new String[]{"X", "Y", "Z"});
		System.out.printf("#3 New Array Value is '%s'\n", Arrays.toString(t.getArray()));		
	}

}
