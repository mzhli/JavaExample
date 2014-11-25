package me.mzhli.javaexample.lang;

public class TemplateClass<T extends Comparable<T>> {

	private T val;
	
	public TemplateClass(Class<T> cls) throws Exception {
		val = cls.newInstance();
	}
	
	public T getVal() {
		return val;
	}
	
	public void setVal(T newVal) {
		if (val.compareTo(newVal) < 0) // Assign only if new value is larger
			val = newVal;
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
	}

}
