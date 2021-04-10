package application.database;

import java.sql.*;
import java.util.ArrayList;

public class Database {

	private java.sql.Connection conn;
	private String username, password, ipAdress, database;
	private DatabaseQueries dbq;

	/**
	 * Create database object associated with certain database specified here.
	 * 
	 * @param ipAdress
	 * @param database
	 * @param username
	 * @param password
	 */

	public Database(String ipAdress, String database, String username, String password) {
		this.username = username;
		this.password = password;
		this.ipAdress = ipAdress;
		this.database = database;
		dbq = new DatabaseQueries(this);
//		conn = null;
	}

	/**
	 * Checks connection to database.
	 * 
	 * @return true if successful connect to database
	 * @throws ClassNotFoundException
	 */

	public boolean openConnection() throws ClassNotFoundException {
		System.out.println("Connecting to database...");
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + ipAdress + "/" + database + "?allowPublicKeyRetrieval=true&useSSL=false", username,
					password);
			// ?useSSL=false&characterEncoding=utf8&profileSQL=true

		} catch (SQLException e) {
			System.out.println("Failed to connect to database: SQLException.");
			e.printStackTrace();
			return false;
		}
		System.out.println("Connected to database!");
		return true;
	}

	/**
	 * Close connection to database
	 */
	public void closeConnection() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn = null;
		System.err.println("Database connection closed.");
	}

	/**
	 * 
	 * @return true if connection is established
	 */
	public boolean isConnected() {
		return conn != null;
	}

	/**
	 * Execute query
	 * 
	 * @param query to send to database
	 * @return result or exception
	 * @throws SQLException
	 */
	public String executeQuery(String query) throws SQLException {
		Statement stmt = null;
		StringBuilder sb = new StringBuilder();
		try {
			openConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int nbrOfColumns = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= nbrOfColumns; i++) {
					sb.append(rs.getString(i));
				}
			}
			return sb.toString();
		} catch (ClassNotFoundException e) {
			return "SQLException";
		} finally {
			closeConnection();
		}
	}

	public String executeQueryNoOpenNoClose(String query) throws ClassNotFoundException {
		Statement stmt = null;
		StringBuilder sb = new StringBuilder();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int nbrOfColumns = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= nbrOfColumns; i++) {
					sb.append(rs.getString(i));
				}
			}
			return sb.toString();
		} catch (SQLException e) {
			return "SQLException";
		}
	}

	public String updateQuery(String query) throws ClassNotFoundException {
		Statement stmt = null;
		System.out.println("Running updateQuery in DB with query:" + query);
		try {
			openConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println("SQLException in DB.updateQuery" + e.getMessage());
		} finally {
			closeConnection();
		}
		return "hej";
	}

	/**
	 * 
	 * Läser in en SQL-syntax och returnerar en arraylist-matris.
	 * 
	 * 1. header 2. data 3. data
	 * 
	 * Exempel: 1. custId, custName, phonenumber, adress 2. 12345, Johan Karlsson,
	 * 0735045479, Norra Kustvägen 52 3. 51341, Henrik Larsson, 046291111,
	 * Talltitevägen 11
	 * 
	 * @param SQL-SYNTAX
	 * @return ArrayList<ArrayList<String>> tableMatrix
	 */

	public ArrayList<ArrayList<String>> getTable(String condition) {
		ArrayList<ArrayList<String>> tableMatrix = new ArrayList<ArrayList<String>>();
		ArrayList<String> colNames = new ArrayList<String>();
		Statement stmt = null;
		try {
			openConnection();
			// SQL FOR SELECTING ALL OF CUSTOMER
			String query = condition;
			// ResultS
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			/**********************************
			 * TABLE COLUMN ADDED DYNAMICALLY *
			 **********************************/
			// Get number of columns
			int nbrOfCols = rs.getMetaData().getColumnCount();
			// Add Column titles to array
			for (int i = 1; i <= nbrOfCols; i++) {
				colNames.add(rs.getMetaData().getColumnLabel(i));
			}
			System.out.print(colNames.toString() + "\n");
			tableMatrix.add(colNames);
			ArrayList<String> colValues = new ArrayList<String>();
			int row = 1;
			while (rs.next()) {
				System.out.println("\nRow number " + row);
				System.out.print(colValues.toString());
				colValues.clear();
				for (int col = 1; col <= nbrOfCols; col++) {
					System.out.println(rs.getString(col));
					colValues.add(rs.getString(col));
				}
				tableMatrix.add(new ArrayList<String>(colValues));
				row++;
			}
			System.out.println("Array print: \n " + tableMatrix.toString());
			System.out.println("Retrieved table successfully");
			return tableMatrix;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
			return null;
		} finally {
			closeConnection();
		}
	}

	public String[] getColumn(String col, String table) {
		Statement stmt = null;
		ArrayList<String> items = new ArrayList<String>();
		try {
			openConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT " + col + " FROM " + table);
			int nbrOfColumns = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= nbrOfColumns; i++) {
					items.add(rs.getString(i));
				}
			}
			System.out.println("Column   ->" + items.toString());
			
			String[] array = new String[items.size()];
			
			for (int i = 0; i < items.size(); i++) {
				array[i] = items.get(i);
			}
			return array;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection();
		}
	}
	
	public DatabaseQueries getDatabaseQueries() {
		return dbq;
	}
}