package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;



public class ServiceUtil {

	private static Connection con;
		
			
		public static Connection getConnection() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "MINKY", "1234");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return con;
		}
		
		
	
		
		
}
