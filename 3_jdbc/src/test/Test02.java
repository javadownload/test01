package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import util.JDBCUtil;

public class Test02 {
	public static void main(String[] args) {
		
		String sql = 
		"select deptno,ename,sal,comm, sal+nvl(comm,0) as 총급여  "+
		"from EMP  "+
		"order by 총급여  ";
		
		System.out.println("JDBC TEST");

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int result = 0;
		try {
            con = JDBCUtil.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
            	System.out.print(rs.getString("deptno")+"\t");
            	System.out.print(rs.getString("ename")+"\t");
            	System.out.print(rs.getString("sal")+"\t");
            	System.out.print(rs.getString("comm")+"\t");
            	System.out.print(rs.getString("총급여")+"\n");
            }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JDBCUtil.close(con, st, rs);
		}
		System.out.println(" END ");
	}
}
