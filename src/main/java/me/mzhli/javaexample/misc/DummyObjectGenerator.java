package me.mzhli.javaexample.misc;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * A class which constructs a random integer from 0 to 1000
 */
class RandomInteger {
	
	public RandomInteger() {
		val = rnd.nextInt(1000);
	}
	
	public int getVal() {
		return val;
	}
	
	private int val;
	private static final Random rnd = new Random(17);
	
}

/**
 * A generic class which can be used to generate arbitrary number of objects of type for experiment purpose
 * @param <T> The type of object to be generated
 */
public class DummyObjectGenerator<T> {

	public DummyObjectGenerator(Class<T> cls) throws Exception {
		super();
		if (cls == null) {
			throw new Exception("Class of type cannot be empty");
		}
		this.cls = cls;
	}
	
	public ArrayList<? super T> generateObjects(int num) {
		ArrayList<T> out = new ArrayList<T>();
		generateObjects(num, out);
		return out;
	}
	
	public void generateObjects(int num, ArrayList<? super T> out) {
		out.clear();
		try {
			while (num > 0) {
				out.add(cls.newInstance());
				num--;
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	private Class<T> cls;

	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) {
		final int NUMBER_OF_OBJECTS = (1 << 24);
		try {
			Thread.sleep(1000);
			DummyObjectGenerator<RandomInteger> gen = new DummyObjectGenerator<RandomInteger>(RandomInteger.class);
			System.out.printf("Generating %d objects of %s...\n", NUMBER_OF_OBJECTS, RandomInteger.class.getName());
			@SuppressWarnings("unused")
			ArrayList<RandomInteger> objects = (ArrayList<RandomInteger>)gen.generateObjects(NUMBER_OF_OBJECTS);
			System.out.printf("Finished!\n");
			Scanner sc = new Scanner(System.in);
			sc.nextLine();
			sc.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
