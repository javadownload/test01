package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.BookVO;

public class BookDAO_Mariadb {
     
    
	public  List<BookVO> bookList() {
		List<BookVO> list = new ArrayList<BookVO>();
		String sql ="select * from book order by bookno desc";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BookVO vo = new BookVO();
				vo.setBookno(rs.getInt("bookno"));
				vo.setPrice(rs.getInt("price"));
				vo.setPublisher(rs.getString("publisher"));
				vo.setTitle(rs.getString("title"));
				vo.setImg(rs.getString("img"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			System.out.println("error"+e);
		}finally {
			JDBCUtil.close(con, ps, rs);
		}
		
		return list;
	}
	public void bookAdd(BookVO vo) {
		System.out.println("add  "+vo);
		String sql =
		"insert into book (title,publisher,price,img) values (?,?,?,?)";
		//"insert into book (bookno,title,publisher,price)" 
		//" values (((select nvl(max(bookno),0)+1 from book)),?,?,?)";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅 
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getPublisher());
			ps.setInt(3, vo.getPrice());
			ps.setString(4, vo.getImg());
			
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
	
	public void bookDelete(int bookno) {
		
		String sql ="delete from book where bookno = ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅 
			ps.setInt(1, bookno);
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
	public void bookUpdate(BookVO vo) {
		
		String sql = "update book set price = ? where bookno = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅 
			ps.setInt(1, vo.getPrice());
			ps.setInt(2, vo.getBookno());
			
			
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
	public List<BookVO> bookSearch(String condition , String keyword) {
		
		String sql ="select * from book where "+condition+" like ? order by bookno desc";
		
//		select * from book where publisher like '%한%'
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BookVO> list = new ArrayList<BookVO>();
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅 
			ps.setString(1, "%"+keyword+"%");
			
			//실행
			rs = ps.executeQuery();
			
			//결과값 처리 
			while(rs.next()) {
				BookVO vo = new BookVO();
				vo.setBookno(rs.getInt("bookno"));
				vo.setPrice(rs.getInt("price"));
				vo.setPublisher(rs.getString("publisher"));
				vo.setTitle(rs.getString("title"));
				list.add(vo);
			}
			
		}catch (Exception e) {
		     e.printStackTrace();	
		}finally {
			JDBCUtil.close(con, ps, rs);
		}
		return list;
	}
	public BookVO getBook(int bookno) {
		String sql ="select * fro"
				+ "m book where bookno = ? ";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BookVO vo = null;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, bookno);
			rs = ps.executeQuery();
			//결과값 처리 
			while(rs.next()) {
				vo = new BookVO();
				vo.setBookno(rs.getInt("bookno"));
				vo.setPrice(rs.getInt("price"));
				vo.setTitle(rs.getString("title"));
				vo.setPublisher(rs.getString("publisher"));
				vo.setImg(rs.getString("img"));
			}
		}catch (Exception e) {
		     e.printStackTrace();	
		}finally {
			JDBCUtil.close(con, ps, rs);
		}
		return vo;
	}
}
