package Forms;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
	
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/forms";
	String user = "root";
	String pass = "";
	
	Connection con = null;
	
	public ConnectionClass() {
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			
			System.out.println("Connection has been established");
			
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		ConnectionClass c1 = new ConnectionClass();
	}

}
