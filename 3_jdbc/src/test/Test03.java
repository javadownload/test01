package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import util.JDBCUtil;

public class Test03 {
	public static void main(String[] args) {
		String sql = 
		"select ename,job,hiredate,deptno "+
		"from emp "+
		//"where deptno="+args[0]+" or deptno ="+args[1];
         //"where deptno="+args[0];
         "where ename ='"+args[0]+"'";

		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			con = JDBCUtil.getConnection();
			st = con.createStatement();
			
			rs = st.executeQuery(sql);  //select
			
			//결과값핸들링
			while(rs.next()) {
				System.out.print(rs.getString("ename")+"\t");
				System.out.print(rs.getString("job")+"\t");
				System.out.print(rs.getInt("deptno")+"\t");
				System.out.print(rs.getDate("hiredate")+"\n");
			}
			
		} catch (Exception e) {
          e.printStackTrace();
		} finally {
			JDBCUtil.close(con, st, rs);
		}
		
		
	}
}





