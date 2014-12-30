package me.mzhli.javaexample.lang;

import static java.lang.System.out;

import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Reflection {

	public final static int DEFAULT_NUMBER = 10;
	private int number;
	
	public Reflection() {
		number = DEFAULT_NUMBER;
	}
	
	public Reflection(int n) {
		number = n;
	}
	
	public int getNumber() {
		return number;
	}
	
	public int getPower() {
		return number * number;
	}
	
	public void toHex(OutputStream os) {
		new PrintStream(os).print(toHexString());
	}
	
	private String toHexString() {
		return Integer.toHexString(number);
	}

	public static void main(String[] args) {
		Class<Reflection> cls = Reflection.class;
		out.printf("Classname:\n  %s\n", cls.getName());
		out.printf("Fields:\n");
		for (Field field : cls.getDeclaredFields()) {
			if (Modifier.isPublic(field.getModifiers())) {
				out.printf("  +");
			} else {
				out.printf("  -");
			}
			out.printf("%s : %s\n", field.getName(), field.getType().getName());
		}
		out.printf("Methods:\n");
		for (Method m : cls.getDeclaredMethods()) {
			if (Modifier.isPublic(m.getModifiers())) {
				out.printf("  +");
			} else {
				out.printf("  -");
			}
			out.printf("%s(", m.getName());
			Class<?>[] paramTypes = m.getParameterTypes();
			for (int k = 0; k < paramTypes.length; k++) {
				if (k != 0) {
					out.printf(",");
				}
				out.printf("%s", paramTypes[k].getName());
			}
			out.printf(") : %s\n", m.getReturnType().getName());
		}
	}
	
}
