package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test01 {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		//String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String url = "jdbc:oracle:thin:@70.12.113.220:1521:xe";
		String user = "SCOTT";
		String pw = "TIGER";
		
		String sql = "select * from dept ";
		
		System.out.println("JDBC TEST");

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int result = 0;
		try {
			// 1. Driver클래스를 로딩
			Class.forName(driver);
			// 2. 로딩된 Driver클래스를 이용해서 Connection요청(url, user, pwd)
            con = DriverManager.getConnection(url,user,pw);
			// 3. 생성된Connection으로부터 Statement생성
            st = con.createStatement();
			// 4. 생성된 Statement를 이용해서 sql수행(execute, executeUpdate, executeQuery)
            rs = st.executeQuery(sql);
			// 5. 결과 처리(ResultSet, int)
            while(rs.next()) {
            	System.out.print(rs.getString("deptno")+"\t");
            	System.out.print(rs.getString("dname")+"\t");
            	System.out.print(rs.getString("loc")+"\n");
            }

		} catch (Exception e) {
			// 6. SQLException 처리(try, catch, finally)
			System.out.println(e.getMessage());
		} finally {
			// 7. 자원정리(connection, statement, resultset)
			try {
			   if(rs != null) rs.close();
			   if(st != null) st.close();
			   if(con != null) con.close();
			   
			}catch (Exception e) {
               System.out.println(e.getMessage());
			}
		}

		System.out.println(" END ");
	}
}
