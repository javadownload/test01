package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	static String user = "hr";
	static String pw = "hr";
	
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pw);
			
		} catch (ClassNotFoundException e) {
             System.out.println("driver 확인");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void close(ResultSet rs , Statement st , Connection con) {
		try {
		   if(rs != null) rs.close();
		   if(st != null) st.close();
		   if(con != null) con.close();
		} catch (Exception e) {
           e.printStackTrace();
		}
	}

}
