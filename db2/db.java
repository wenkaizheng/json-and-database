package database.db2;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class db {
	
	public static Connection conn ;
	public static Statement stmt ;

	public db() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		    String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	        String url = "jdbc:derby:";
	        Class.forName(driver).newInstance();
	        conn= DriverManager.getConnection(url + "weaponsZheng;create=true");
	        //DatabaseMetaData metas;
	       // metas=conn.getMetaData();
	        conn.setAutoCommit(true);
	        stmt = conn.createStatement();
	       
	       
	}

	public static String search(String name) {
		String inf = null;
		//String sql = "USE wuqiku";
		try {
			//stmt.executeUpdate(sql);
			String sql = "select * from Registration";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String weaponName = rs.getString("name");
				// System.out.println()
				if (weaponName.equals(name)) {
					System.out.println("we found this weapon");
					inf = new String("");
					inf += String.format(
							"The name is %s\nand description is %s\n" + "and accuracy is %d\n" + "and demage is %d\n"
									+ "and price is %d\n"+"and secondsPerShot is %f",
							name, rs.getString("de"), rs.getInt("acc"), rs.getInt("damage"), 
							rs.getInt("price"),rs.getDouble("persecond"));
							
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
		
		String name = w.name;
		int dam = w.damage;
		double shot = w.persecond;
		int acc = w.accuracy;
		String des = w.des;
		int bp =w.price;
		
		if (des.contains("'")) {
			// he does't like it -> he does 't like it
			des = des.replace("'", "''");
		}
		String sql = String.format("INSERT INTO Registration VALUES (%d, '%s', %d, %f, %d, '%s',%d)", num, name, dam, shot, acc,
				des,bp);
		System.out.println(sql);
		
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

	public int create() {

		try {
		    
			System.out.println("Creating database...");
			File file =new File("beta.txt");

		     // check if the database is already create
		    if(!file.exists()){
		       file.createNewFile();
		     }
		    else  return 1;

			

			String column = "CREATE TABLE REGISTRATION " + "(id INTEGER not NULL, " + " name VARCHAR(255), "
					+ " damage INTEGER, " + " persecond DOUBLE, " + " acc INTEGER, " + " de VARCHAR(1024), "
					+" price INTEGER ,"+ " PRIMARY KEY ( id ))";
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