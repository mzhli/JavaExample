package me.mzhli.javaexample.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminLookup {

	/**
	 * Constructor accepts database URL as only parameter
	 * @param dbURL	the database URL
	 * @throws SQLException if any database operation fails
	 */
	public AdminLookup(String dbURL, String username, String password) throws SQLException {
		String connURL = "jdbc:mysql:" + dbURL;
		conn = DriverManager.getConnection(connURL, username, password);
	}
	
	/**
	 * Get all admin records belonging to target country
	 * @param country the abbr. name of country like 'USA', case-insensitive
	 * @return array list containing the result records
	 */
	public ArrayList<AdminInfo> getAdminByCountry(String country) {
		return getAdmin(country, "", "");
	}
	
	/**
	 * Get all admin records belonging to target state
	 * @param country the abbr. name of country like 'USA', case-insensitive
	 * @param state the abbr. name of state like 'CA' for California, case-insensitive
	 * @return array list containing the result records
	 */
	public ArrayList<AdminInfo> getAdminByState(String country, String state) {
		return getAdmin(country, state, "");
	}
	
	/**
	 * Get all admin records of target city
	 * @param country the abbr. name of country like 'USA', case-insensitive
	 * @param state the abbr. name of state like 'CA' for California, case-insensitive
	 * @param city the abbr. name of city like 'Sunnyvale', case-insensitive
	 * @return array list containing the result records
	 */
	public ArrayList<AdminInfo> getAdminByCity(String country, String state, String city) {
		return getAdmin(country, state, city);
	}
	
	/**
	 * Get admin info records from database based on parameters
	 * @return array list containing the matched records
	 */
	protected ArrayList<AdminInfo> getAdmin(String country, String state, String city) {
		ArrayList<AdminInfo> result = new ArrayList<AdminInfo>();
		try {
			// Get statement
			Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			try {
				// Get result set
				String strSQL = sqlSelect(country, state, city);
				System.out.printf("[DEBUG] SQL='%s'\n", strSQL);
				ResultSet rs = stmt.executeQuery(strSQL);
				try {
					// Iterate all records
					while (rs.next()) {
						result.add(new AdminInfo(rs.getString(FIELD_INDEX_COUNTRY),
												 rs.getString(FIELD_INDEX_STATE),
												 rs.getString(FIELD_INDEX_CITY)));
					}
				} finally {
					rs.close();
				}
			} finally {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}	
	
	private static String sqlSelect(String country, String state, String city) {
		StringBuilder sb = new StringBuilder();
		// BEGIN
		sb.append("SELECT ");
		sb.append(FIELD_NAME_COUNTRY);
		sb.append(", ");
		sb.append(FIELD_NAME_STATE);
		sb.append(", ");
		sb.append(FIELD_NAME_CITY);
		sb.append(" FROM ");
		sb.append(ADMIN_TABLE_NAME);
		// Organize conditions
		ArrayList<String> conditions = new ArrayList<String>();
		if (country.length() > 0) {
			conditions.add(composeCondition(FIELD_NAME_COUNTRY, country));
		}
		if (state.length() > 0) {
			conditions.add(composeCondition(FIELD_NAME_STATE, state));
		}
		if (city.length() > 0) {
			conditions.add(composeCondition(FIELD_NAME_CITY, city));
		}
		
		if (! conditions.isEmpty()) {
			sb.append(" WHERE ");
			sb.append(conditions.get(0));
			for (int i = 1; i < conditions.size(); i++) {
				sb.append(" AND ");
				sb.append(conditions.get(i));
			}
		}
		
		sb.append(" ORDER BY ");
		sb.append(FIELD_NAME_COUNTRY);
		sb.append(",");
		sb.append(FIELD_NAME_STATE);
		sb.append(",");
		sb.append(FIELD_NAME_CITY);
		sb.append(" ASC");
		// FINISH
		sb.append(";");
		return sb.toString();
	}
	
	private static String composeCondition(String fieldName, String valuePattern) {
		// Normalized value pattern
		String newValPattern = valuePattern.replace('*', '%').replace('?', '_').toUpperCase();
		StringBuilder sb = new StringBuilder();
		sb.append(fieldName);
		sb.append(" LIKE \"");
		sb.append(newValPattern);
		sb.append("\"");
		return sb.toString();
	}
	
	/**
	 * Close connection to database
	 */
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Connection conn; 
	
	private static final String ADMIN_TABLE_NAME = "admin";
	
	private static final String FIELD_NAME_COUNTRY = "Country";
	private static final String FIELD_NAME_STATE = "State";
	private static final String FIELD_NAME_CITY = "City";
	
	private static int FIELD_INDEX_COUNTRY = 1;
	private static int FIELD_INDEX_STATE = 2;
	private static int FIELD_INDEX_CITY = 3;
	
	/**
	 * Representation of admin information 
	 */
	public static class AdminInfo {
		public AdminInfo(String country, String state, String city) {
			this.country = country;
			this.state = state;
			this.city = city;
		}
		
		/**
		 * @return the country
		 */
		public String getCountry() {
			return country;
		}
		
		/**
		 * @return the state
		 */
		public String getState() {
			return state;
		}
		
		/**
		 * @return the city
		 */
		public String getCity() {
			return city;
		}

		private String country;
		private String state;
		private String city;
	}
	
	
	public static void main(String[] args) {
		final String TEST_DB_URL = "//localhost:3306/test";
		final String DB_USER_NAME = "mzhli";
		final String DB_PASSWORD = "";
		try {
			AdminLookup lookup = new AdminLookup(TEST_DB_URL, DB_USER_NAME, DB_PASSWORD);
			try {
				ArrayList<AdminInfo> result = null;
				// Case 1: All records of country 'USA'
				result = lookup.getAdminByCountry("Usa");
				System.out.printf("==========================================\n");
				System.out.printf("Case 1 result:\n");
				System.out.printf("Total count: %d\n", result.size());
//				for (AdminInfo admin : result) {
//					System.out.printf("-- '%s' > '%s' > '%s'\n", admin.getCountry(), 
//															     admin.getState(), 
//															     admin.getCity());
//				}
				
				// Case 2: All records of state 'CA'
				System.out.printf("==========================================\n");
				result = lookup.getAdminByState("Usa", "nv");
				System.out.printf("Case 2 result:\n");
				System.out.printf("Total count: %d\n", result.size());
//				for (AdminInfo admin : result) {
//					System.out.printf("-- '%s' > '%s' > '%s'\n", admin.getCountry(), 
//															     admin.getState(), 
//															     admin.getCity());
//				}
				
				// Case 3: All records of city 'Sunnyvale'
				System.out.printf("==========================================\n");
				result = lookup.getAdminByCity("Usa", "ca", "sunny");
				System.out.printf("Case 3 result:\n");
				System.out.printf("Total count: %d\n", result.size());
				for (AdminInfo admin : result) {
					System.out.printf("-- '%s' > '%s' > '%s'\n", admin.getCountry(), 
															     admin.getState(), 
															     admin.getCity());
				}
			} finally {
				lookup.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
