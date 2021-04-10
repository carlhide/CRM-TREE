package application.database;

import java.sql.SQLException;

public class DatabaseQueries {

	private Database db;

	public final String COMPANY_SYDANTENN = "dc235";
	public final String COMPANY_CONNECTTV = "dc235";

	public final String HDD = "dc235";
	public final String REMOTE = "dc235";
	public final String DILOG_DC_230 = "dc235";
	public final String DILOG_DC_235 = "dc235";
	public final String DILOG_DC_250 = "dc235";
	public final String DILOG_DC_280 = "dc235";
	public final String DILOG_DIP_701 = "dc235";

	public final String ERRAND_SUPPORT = "dc235";
	public final String ERRAND_SALES = "dc235";
	public final String ERRAND_SUBSCRIPTION = "dc235";
	public final String ERRAND_PAYMENT = "dc235";
	public final String ERRAND_OTHERS = "dc235";
	

	public DatabaseQueries(Database db) {
		this.db = db;

	}

	/*
	 * UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE
	 * condition;
	 */

	public void alterValue(String table, String column1, String value1, String condition) throws SQLException {

		String query = "UPDATE " + table + "\n" + "	SET " + column1 + " = " + value1 + "\n" + "	WHERE " + condition
				+ ";";

		db.executeQuery(query);
	}

	public String getCondition(String column, String table, String condition) {

		String condReturn = null;

		String query = "SELECT " + column + "FROM " + table + "WHERE '" + condition + "'";

		try {
			condReturn = db.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return condReturn;
	}

	public boolean checkLogin(String username, String password) throws SQLException {
		String query = "SELECT Password FROM users WHERE Username = '" + username + "';";
		String dbPassword = "none retrieved from database";

		dbPassword = db.executeQuery(query);
		System.out.println(dbPassword + password);

		if (dbPassword.equals(password)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean updatePassword(String accountName, String accountPassword) {
		System.out.println("Running update password with AccountName = " + accountName + " and accountPassword = "
				+ accountPassword);
		try {
			db.updateQuery("UPDATE Accounts SET AccountPassword = '" + accountPassword + "' WHERE AccountName = '"
					+ accountName + "';");
			return true;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addRow(String table, String[] columns, String[] values) {
		if (columns.length != values.length) {
			System.out.println("Values not equal to columns");
			return false;
		}
		StringBuilder columnsString = new StringBuilder();
		StringBuilder valuesString = new StringBuilder();
		String SQLQuery;
		for (String s : columns) {
			if (s.equals(columns[columns.length - 1])) {
				columnsString.append(s);
			} else {
				columnsString.append(s + ", ");
			}
		}
		for (String s : values) {
			if (s.equals(values[values.length - 1])) {
				valuesString.append("'" + s + "'");
			} else {
				valuesString.append("'" + s + "'" + ", ");
			}
		}
		System.out.println("Adding row with query: " + "INSERT INTO " + table + "(" + columnsString.toString() + ")"
				+ " VALUES (" + valuesString.toString() + ")");
		SQLQuery = "INSERT INTO " + table + "(" + columnsString.toString() + ")" + " VALUES (" + valuesString.toString()
				+ ")";
		try {
			db.updateQuery(SQLQuery);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addRow(String table, String column, String value) {

		String SQLQuery = "INSERT INTO " + table + "(" + column + ")" + " VALUES (" + value + ")";
		try {
			db.updateQuery(SQLQuery);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void sendQuery(String query) {
		try {
			db.updateQuery(query);
		} catch (ClassNotFoundException e) {
			System.out.println("Exception in sendQuery");
			e.printStackTrace();
		}
	}

	public String getItems(String query) {
		String condReturn = null;
		try {
			condReturn = db.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return condReturn;
	}

	/**
	 * 
	 * @param table  to alter. ex: lager
	 * @param column to increment. ex: antal
	 * @param amount to increment with
	 * @param        condition. ex: [Artikel = 'DILOG']
	 */

	public void incrementRow(String table, String column, String amount, String condition) {
		String query = "UPDATE " + table + " SET " + column + " = " + column + " + " + amount + " WHERE " + condition
				+ ";";
		try {
			db.updateQuery(query);
		} catch (ClassNotFoundException e) {
			System.out.println("Exception in sendQuery");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param table  to alter. ex: lager
	 * @param column to decrement. ex: antal
	 * @param amount to decrement with
	 * @param        condition. ex: [Artikel = 'DILOG']
	 */

	public void decrementRow(String table, String column, String amount, String condition) {
		String query = "UPDATE " + table + " SET " + column + " = " + column + " - " + amount + "  WHERE " + condition
				+ ";";
		try {
			db.updateQuery(query);
		} catch (ClassNotFoundException e) {
			System.out.println("Exception in sendQuery");
			e.printStackTrace();
		}
	}

	public boolean rowExists(String table, String column, String condition) {
		String query = "SELECT COUNT(1) FROM " + table + " WHERE " + condition;
		try {
			String s = db.executeQuery(query);
			if(s.equals("0")) {
				return false;
			}else {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Exception in sendQuery");
			e.printStackTrace();
			return false;
		}
	}
	
	public Table getTable(String condition) {
		return new Table(db, condition);
	}

}
