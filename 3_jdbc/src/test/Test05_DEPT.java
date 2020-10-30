package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.DeptVO;

public class Test05_DEPT {
	public static void main(String[] args) {
		// DeptVO vo = getDept(20);
		// System.out.println(20+" : "+vo);
		DeptVO vo = new DeptVO();
		vo.setDname("OOO");
		vo.setLoc("멀캠");
		vo.setDeptno(10);

		// updateDept(vo);

		// int i = insertDept("OOO", "서울");
		// insertDept(vo);

		deleteDept(13);

		System.out.println("----------------------------");
		List<DeptVO> list = getDeptAll();
		System.out.println(list);

	}

	public static void updateDept(DeptVO vo) {
		String sql = "update dept set loc=? where deptno=?";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 세팅 작업
			ps.setString(1, vo.getLoc());
			ps.setInt(2, vo.getDeptno());

			result = ps.executeUpdate(); // insert update delete

			// 결과값핸들링

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}

	}

	public static void deleteDept(int deptno) {
		String sql = "delete from dept where deptno=?";

		Connection con = null;
		// Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;

		try {
			con = JDBCUtil.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			// ? 세팅 작업
			ps.setInt(1, deptno);

			result = ps.executeUpdate(); // insert update delete

			// 결과값핸들링
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JDBCUtil.close(con, ps, rs);
		}

	}

	public static int insertDept(DeptVO vo) {
		return insertDept(vo.getDname(), vo.getLoc());
	}

	public static int insertDept(String dname, String loc) {
		String sql = "insert into dept (deptno,dname,loc) " +
			          "values(((select nvl(max(deptno),0)+1 from dept)),?,?)";

		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 세팅 작업
			// ps.setInt(1, deptno);
			ps.setString(1, dname);
			ps.setString(2, loc);

			result = ps.executeUpdate(); // insert update delete
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, null);
		}
		return result;
	}

	public static DeptVO getDept(int deptno) {
		DeptVO vo = null;
		String sql = "select * from dept where deptno = ? ";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 세팅 작업
			ps.setInt(1, deptno);
			rs = ps.executeQuery(); // select
			// 결과값핸들링
			while (rs.next()) {
				vo = new DeptVO();
				vo.setDeptno(rs.getInt("deptno"));
				vo.setDname(rs.getString("dname"));
				vo.setLoc(rs.getString("loc"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
		return vo;
	}

	public static List<DeptVO> getDeptAll() {
		List<DeptVO> list = new ArrayList<DeptVO>();
		String sql = "select * from dept ";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 세팅 작업

			rs = ps.executeQuery(); // select
			// 결과값핸들링
			while (rs.next()) {
				DeptVO vo = new DeptVO();
				vo.setDeptno(rs.getInt("deptno"));
				vo.setDname(rs.getString("dname"));
				vo.setLoc(rs.getString("loc"));

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
		return list;
	}

}
