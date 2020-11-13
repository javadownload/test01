package util;

import java.io.FileInputStream;
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
		try {
			Properties p = new Properties();
			p.load(new FileInputStream("c:/lib/dbinfo.txt"));
//			p.load(new FileInputStream("dbinfo.txt"));

			String driver = p.getProperty("_driver");
			String url = p.getProperty("_url");
			String user = p.getProperty("_user");
			String pw = p.getProperty("_pw");

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pw);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void close(Connection con, Statement st, ResultSet rs) {
		// 7. 자원정리(connection, statement, resultset)
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void close(  ResultSet rs,Statement st,Connection con) {
// 7. 자원정리(connection, statement, resultset)
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

/*
 * static String driver = "oracle.jdbc.driver.OracleDriver"; static String url =
 * "jdbc:oracle:thin:@127.0.0.1:1521:xe"; static String user = "hr"; static
 * String pw = "hr";
 */