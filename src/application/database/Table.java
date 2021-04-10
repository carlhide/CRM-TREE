package application.database;

import java.util.ArrayList;

public class Table {
	
	public final static String USERS_TABLE = "SELECT * FROM `CRM-TRE`.users;";


	private ArrayList<ArrayList<String>> tableMatrix;
	// private Database db;

	public Table(Database db, String condition) {
		// this.db = db;
		tableMatrix = db.getTable(condition);
	}

	public String getItem(int rowIndex, int colIndex) {
		return tableMatrix.get(rowIndex).get(colIndex);
	}

	public int getNbrOfRows() {
		return tableMatrix.size();
	}

	public int getNbrOfCols() {
		return tableMatrix.get(0).size();
	}

	public ArrayList<String> getHeader() {
		System.out.println("Table.getHeader(): " + tableMatrix.get(0).toString());
		return tableMatrix.get(0);
	}

	public ArrayList<ArrayList<String>> getTableData() {
		ArrayList<ArrayList<String>> temp = tableMatrix;
		temp.remove(0);
		return temp;
	}

	public ArrayList<ArrayList<String>> getTable() {
		return tableMatrix;
	}

	public boolean isEmpty() {
		return tableMatrix.isEmpty();
	}

	public String matrixToString() {
		StringBuilder sb = new StringBuilder();
		for (ArrayList<String> row : tableMatrix) {
			for (String col : row) {
				sb.append(col + ", ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
