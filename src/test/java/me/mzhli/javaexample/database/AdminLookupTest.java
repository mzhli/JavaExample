package me.mzhli.javaexample.database;

import java.util.ArrayList;

import me.mzhli.javaexample.database.AdminLookup.AdminInfo;
import junit.framework.TestCase;

public class AdminLookupTest extends TestCase {

	public void testGetAdminByCity() {
		System.out.printf("==========================================\n");
		
		// Usa > ca > sunny*
		ArrayList<AdminInfo> result = lookup.getAdminByCity("Usa", "ca", "sunny*");
		System.out.printf("Total count: %d\n", result.size());
		for (AdminInfo admin : result) {
			System.out.printf("-- '%s' > '%s' > '%s'\n", admin.getCountry(), 
													     admin.getState(), 
													     admin.getCity());
		}
		System.out.printf("==========================================\n");
		assertFalse(result.isEmpty());
		
		// * > * > sunny*
		result = lookup.getAdminByCity("*", "*", "sunny*");
		System.out.printf("Total count: %d\n", result.size());
		for (AdminInfo admin : result) {
			System.out.printf("-- '%s' > '%s' > '%s'\n", admin.getCountry(), 
													     admin.getState(), 
													     admin.getCity());
		}
		System.out.printf("==========================================\n");
		assertFalse(result.isEmpty());
		
		// ??? > ?? > sunny*  
		result = lookup.getAdminByCity("???", "??", "sunny*");
		System.out.printf("Total count: %d\n", result.size());
		for (AdminInfo admin : result) {
			System.out.printf("-- '%s' > '%s' > '%s'\n", admin.getCountry(), 
													     admin.getState(), 
													     admin.getCity());
		}
		System.out.printf("==========================================\n");
		assertFalse(result.isEmpty());
		
		// * > * > New*  
		result = lookup.getAdminByCity("*", "*", "*New*");
		System.out.printf("Total count: %d\n", result.size());
		for (AdminInfo admin : result) {
			System.out.printf("-- '%s' > '%s' > '%s'\n", admin.getCountry(), 
													     admin.getState(), 
													     admin.getCity());
		}
		System.out.printf("==========================================\n");
		assertFalse(result.isEmpty());
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		final String TEST_DB_URL = "//localhost:3306/test";
		final String DB_USER_NAME = "mzhli";
		final String DB_PASSWORD = "";
		lookup = new AdminLookup(TEST_DB_URL, DB_USER_NAME, DB_PASSWORD);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		lookup.close();
		super.tearDown();
	}

	private AdminLookup lookup;
}
