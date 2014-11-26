package me.mzhli.javaexample.lang;

import java.lang.reflect.Array;
import java.util.Arrays;

// Here use wildcard template parameter， otherwise the class like MyName cannot match it
public class TemplateClass<T extends Comparable<? super T>> {

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
		TemplateClass<MyName> t = new TemplateClass<MyName>(MyName.class);
		System.out.printf("Old Value is '%s'\n", t.getVal());
		t.setVal(new MyName("abc"));
		System.out.printf("#1 New Value is '%s'\n", t.getVal());
		t.setVal(new MyName("AAA"));
		System.out.printf("#2 New Value is '%s'\n", t.getVal());
		t.setVal(new MyName("bcd"));
		System.out.printf("#3 New Value is '%s'\n", t.getVal());
		
		System.out.printf("Old Array is '%s'\n", Arrays.toString(t.getArray()));
		System.out.printf("Component Type is '%s'\n", t.getArray().getClass().getComponentType().getCanonicalName());
		t.setArray(new MyName[]{new MyName("a"), new MyName("b"), new MyName("c"), new MyName("d"), new MyName("e")});
		System.out.printf("#1 New Array Value is '%s'\n", Arrays.toString(t.getArray()));
		t.setArray(new MyName[]{new MyName("A"), new MyName("B"), new MyName("C"), new MyName("D"), new MyName("E")});
		System.out.printf("#2 New Array Value is '%s'\n", Arrays.toString(t.getArray()));
		t.setArray(new MyName[]{new MyName("X"), new MyName("Y"), new MyName("Z")});
		System.out.printf("#3 New Array Value is '%s'\n", Arrays.toString(t.getArray()));		
	}

}

// The class MyString implements the interface Comparable<MyString>
class MyString implements Comparable<MyString> {

	private String strVal;
	
	public MyString(String strVal) {
		super();
		this.strVal = strVal;
	}

	@Override
	public int compareTo(MyString o) {
		return strVal.compareTo(o.strVal);
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return strVal.toString();
	}
	
}

// The class MyName inherits MyString, which implies it 
// implement Comparable<MyString> other than Comparable<MyName>
class MyName extends MyString {
	
	public MyName() {
		super("");
	}

	public MyName(String name) {
		super(name);
	}

	/*
	 * @see me.mzhli.javaexample.lang.MyString#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}
