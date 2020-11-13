package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {

	public static Connection getConnection() {
		Connection con = null;
		
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("c:/lib/dbinfo.txt"));
			
			String driver = p.getProperty("_driver");
			String url = p.getProperty("_url");
			String user = p.getProperty("_user");
			String pw = p.getProperty("_pw");
			
			Class.forName(driver);
			
			con = DriverManager.getConnection(url,user,pw);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}

	public static void close(Connection con , Statement st , ResultSet rs ) {
	
		try {
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}





