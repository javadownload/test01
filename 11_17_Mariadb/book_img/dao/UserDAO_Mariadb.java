package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.BookVO;
import vo.UserVO;

public class UserDAO_Mariadb {
     
	public UserVO login(UserVO user) {
		String sql ="select * from user where id=? and password=?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserVO vo = null;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getId());
			ps.setString(2, user.getPassword());
			
			rs = ps.executeQuery();
			//결과값 처리 
			while(rs.next()) {
				vo = new UserVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password"));
				vo.setRole(rs.getString("role"));
			}
		}catch (Exception e) {
		     e.printStackTrace();	
		}finally {
			JDBCUtil.close(con, ps, rs);
		}
		return vo;
	}
	public  List<UserVO> userList() {
		List<UserVO> list = new ArrayList<UserVO>();
		String sql ="select * from user";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				UserVO vo = new UserVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password"));
				vo.setRole(rs.getString("role"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			System.out.println("error"+e);
		}finally {
			JDBCUtil.close(con, ps, rs);
		}
		
		return list;
	}
	public void userAdd(UserVO vo) {
		String sql =
		"insert into user (id,password,name) values (?,?,?)";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅 
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPassword());
			ps.setString(3, vo.getName());
			
			//실행
			//ps.executeQuery();
			row = ps.executeUpdate();
			
			//결과값 처리 
			if(row == 0) {
				throw new Exception("등록실패");
			}
		}catch (Exception e) {
		     e.printStackTrace();	
		}finally {
			JDBCUtil.close(con, ps, rs);
		}
	}
	
	public void userDelete(String id) {
		
		String sql ="delete from user where id = ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅 
			ps.setString(1, id);
			//실행
			//ps.executeQuery();
			row = ps.executeUpdate();
			
			//결과값 처리 
			
		}catch (Exception e) {
		     e.printStackTrace();	
		}finally {
			JDBCUtil.close(con, ps, rs);
		}
		
		
	}
	public void userUpdate(UserVO vo) {
		
		String sql = "update user set password = ? where id = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅 
			ps.setString(1, vo.getPassword());
			ps.setString(2, vo.getId());
			
			
			//실행
			//ps.executeQuery();
			ps.executeUpdate();
			
			//결과값 처리 
			
		}catch (Exception e) {
		     e.printStackTrace();	
		}finally {
			JDBCUtil.close(con, ps, rs);
		}
		
	}
	
	public UserVO getUser(String id) {
		String sql ="select * from user where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserVO vo = null;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			//결과값 처리 
			while(rs.next()) {
				vo = new UserVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password"));
				vo.setRole(rs.getString("role"));
			}
		}catch (Exception e) {
		     e.printStackTrace();	
		}finally {
			JDBCUtil.close(con, ps, rs);
		}
		return vo;
	}
}
