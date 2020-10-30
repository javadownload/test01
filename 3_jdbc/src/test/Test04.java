package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.JDBCUtil;

public class Test04 {
	public static void main(String[] args) {
		
         //"where ename ='"+args[0]+"'";

		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int result = 0;
		String sql = 
				"select ename,job,hiredate,deptno "+
				"from emp "+
				"where deptno= ? or deptno = ?";
		try {
			con = JDBCUtil.getConnection();
			st = con.prepareStatement(sql);
			st.setInt(1, 10);
			st.setInt(2, 20);
			rs = st.executeQuery();  //select
			
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





