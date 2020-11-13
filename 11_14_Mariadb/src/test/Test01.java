package test;

import java.sql.Connection;

import util.JDBCUtil;

public class Test01 {

	public static void main(String[] args) {
		System.out.println("Connection test");
		Connection con = JDBCUtil.getConnection();
		System.out.println(con);
		JDBCUtil.close(con, null, null); 

	}

}
