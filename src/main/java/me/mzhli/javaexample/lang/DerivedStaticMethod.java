package me.mzhli.javaexample.lang;

public class DerivedStaticMethod {
	
	/**
	 * The base class which has one static method
	 */
	static class A {
		public static void staticMethod() {
			System.out.println("Calling A.staticMethod");
		}
	}

	/**
	 * A derived class from A which redefine the staticMethod
	 */
	static class B extends A {
		public static void staticMethod() {
			System.out.println("Calling B.staticMethod");
		}
		
		public void callStaticMethod() {
			staticMethod();
		}
	}

	/**
	 * A derived class from A which is empty
	 */
	static class C extends A {
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		// Case 1: There is no polymorphism for static method
		A case1 = new B();
		case1.staticMethod();
		// Case 2: Static method can be inherited by derived class
		C case2 = new C();
		case2.staticMethod();
		// Case 3: Static method of base class will be hidden if it is redefined in derived class
		B case3 = new B();
		case3.callStaticMethod();
		
	}

}
