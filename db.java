package database.db1;

import java.sql.*;

public class db {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1/";
	// "jdbc:mysql://127.0.0.1/wineships";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "123456";
	public static Connection conn ;
	public static Statement stmt ;

	public db() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
	}

	public static String search(String name) {
		String inf = null;
		String sql = "USE wuqiku";
		try {
			stmt.executeUpdate(sql);
			sql = "select * from Registration";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String weaponName = rs.getString("name");
				// System.out.println()
				if (weaponName.equals(name)) {
					System.out.println("we found this weapon");
					inf = new String("");
					inf += String.format(
							"The name is %s\nand description is %s\n" + "and accuracy is %d\n" + "and demage is %d\n"
									+ "and secondsPerShot is %f",
							name, rs.getString("de"), rs.getInt("acc"), rs.getInt("damage"), rs.getDouble("persecond"));
					break;
				}
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(inf);
		return inf;
	}

	public void insert(weapon w, int num) {
		/**
		 * String sql = "INSERT INTO Registration " + "VALUES (100, 'Zara', 'Ali', 18)";
		 * stmt.executeUpdate(sql);
		 */
		String name = w.name;
		int dam = w.damage;
		double shot = w.persecond;
		int acc = w.accuracy;
		String des = w.des;
		String sql = "USE wuqiku";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// sql = "INSERT INTO REGISTRATION VALUES (" + "'" + name + "', " + dam + ", " +
		// shot + ", " + acc + ", " + "'"
		// + des + "')" ;
		// INSERT INTO exampleTbl VALUES('he doesn''t work for me')
		if (des.contains("'")) {
			// he does't like it -> he does 't like it
			des = des.replace("'", "''");
		}
		sql = String.format("INSERT INTO Registration VALUES (%d, '%s', %d, %f, %d, '%s')", num, name, dam, shot, acc,
				des);
		System.out.println(sql);
		// if (stmt != null) {
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// }

	}

	public int create() {

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			// conn = DriverManager.getConnection(DB_URL, USER, PASS);
			ResultSet resultSet = conn.getMetaData().getCatalogs();

			// iterate each catalog in the ResultSet

			while (resultSet.next()) {
				// Get the database name, which is at position 1
				String databaseName = resultSet.getString(1);
				System.out.println(databaseName);
				if (databaseName.equals("wuqiku")) {

					System.out.println("This database is already exist");
					resultSet.close();
					return 1;
				}

			}
			resultSet.close();

			// STEP 4: Execute a query
			System.out.println("Creating database...");

			// stmt = conn.createStatement();

			String sql = "CREATE DATABASE wuqiku";
			stmt.executeUpdate(sql);

			sql = "USE wuqiku";
			stmt.executeUpdate(sql);

			// String column =new String("CREATE TABLE 'quotations' ( 'id' int(20) NOT NULL
			// AUTO INCREMENT,'added' datetime DEFAULT NULL,"+
			// "'name' varchar(2000) DEFAULT NULL,'damage' int(11) DEFAULT NULL,'persecond'
			// DOUBLE(100) DEFAULT NULL,"+
			// "'acc' int(11) DEFAULT NULL,'des' varchar(2000) DEFAULT NULL,PRIMARY KEY
			// (`id`))");
			/**
			 * String column = "CREATE TABLE REGISTRATION " + "(id INTEGER not NULL , " + "
			 * added datetime, " + " name VARCHAR(255), " + " damage int(200), " + "
			 * persecond DOUBLE(100), " + " acc int(200), " + " des VARCHAR(255), " + "
			 * PRIMARY KEY ( id ))";
			 **/

			String column = "CREATE TABLE REGISTRATION " + "(id INTEGER not NULL, " + " name VARCHAR(255), "
					+ " damage INTEGER, " + " persecond DOUBLE, " + " acc INTEGER, " + " de VARCHAR(1024), "
					+ " PRIMARY KEY ( id ))";
			stmt.executeUpdate(column);

			System.out.println("Database created successfully...");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		System.out.println("Goodbye!");
		return 0;
	}// end main
}// end JDBCExample