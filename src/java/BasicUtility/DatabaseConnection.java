package BasicUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DatabaseConnection {
 
	public Connection conn = null;
 
	public DatabaseConnection(String url, String user_name, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
 
			this.conn = DriverManager.getConnection(url, user_name, password);
 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}