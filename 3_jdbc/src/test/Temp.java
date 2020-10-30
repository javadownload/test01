package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.JDBCUtil;

public class Temp {
	public static void main(String[] args) {
		String sql = " ";
		Connection con = null;
		//Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			//? 세팅 작업
			
			rs = ps.executeQuery();  //select
			
			result = ps.executeUpdate(); //insert update delete
			
			//결과값핸들링
			
		} catch (Exception e) {
          e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
		
		
	}
}





