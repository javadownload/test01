package test;

import java.sql.Connection;

import util.JDBCUtil;

public class Test01 {
	public static void main(String[] args) {
		
		Connection con = JDBCUtil.getConnection();
		
        System.out.println(con);
        
        JDBCUtil.close(null, null, con);
        
	}
}
