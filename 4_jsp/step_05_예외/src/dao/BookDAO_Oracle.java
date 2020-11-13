package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.BookVO;

public class BookDAO_Oracle  {
	public List<BookVO> searchBook(String condition, String keyword){
 		String sql =
 	"select * from ( "+
 	"select rownum row#,bookid, bookname, publisher, price , img "+
 	"from (select * from Book where "+condition+" like '%'||?||'%' " +"order by bookid desc) "+ 
 	 ") where row# between ? and ? ";
 					    		
 					    		
 		List<BookVO> list = new ArrayList<BookVO>();
 		
 		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 세팅
			ps.setString(1, keyword);
			ps.setInt(2, 1);
			ps.setInt(3, 5);
			
			//실행 및 결과값 핸들링
            rs = ps.executeQuery();
            while (rs.next()) {
				BookVO vo = new BookVO();
				vo.setBookno(rs.getInt("bookno"));
				vo.setTitle(rs.getString("title"));
				vo.setPublisher(rs.getString("publisher"));
				vo.setPrice(rs.getInt("price"));
				list.add(vo);
			}
            
		}catch (Exception e) {
           e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, ps, con);
		}
 		return list;
	}
	
 	public List<BookVO> getBookRec(){
 		String sql =
 				"select * from ( "+
 					   "select rownum row#,bookid, bookname, publisher, price , img "+
 					   "from (select * from Book order by bookid desc) "+ 
 					    ") where row# between ? and ? ";
 					    		
 					    		
 		List<BookVO> list = new ArrayList<BookVO>();
 		
 		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 세팅
			ps.setInt(1, 1);
			ps.setInt(2, 5);
			
			//실행 및 결과값 핸들링
            rs = ps.executeQuery();
            while (rs.next()) {
            	BookVO vo = new BookVO();
				vo.setBookno(rs.getInt("bookno"));
				vo.setTitle(rs.getString("title"));
				vo.setPublisher(rs.getString("publisher"));
				vo.setPrice(rs.getInt("price"));
				list.add(vo);
			}
            
		}catch (Exception e) {
           e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, ps, con);
		}
 		return list;
	}
	
	public int insertBook(BookVO vo) throws Exception {
		String sql= "INSERT INTO Book(bookid, bookname, publisher, price,img)" + 
				"    VALUES((select nvl(max(bookid),0)+1 from book),?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getPublisher());
			ps.setInt(3, vo.getPrice());
			
            result = ps.executeUpdate();
		}catch (Exception e) {
          throw e;
		}finally {
			JDBCUtil.close(null, ps, con);
		}
		return result;
	}
 	
	
	public int deleteBook(int bookid) {
		String sql = "delete from book where bookid = ? ";
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 세팅
			ps.setInt(1, bookid);
			//실행 및 결과값 핸들링
            result = ps.executeUpdate();
            
            
		}catch (Exception e) {
           e.printStackTrace();
		}finally {
			JDBCUtil.close(null, ps, con);
		}

		return result;
	}
	
	public int updateBook(BookVO vo) {
		String sql  = "UPDATE Book SET price = ?  WHERE bookid = ?";
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 세팅
			ps.setInt(1, vo.getPrice());
			ps.setInt(2, vo.getBookno());
			
			//실행 및 결과값 핸들링
            result = ps.executeUpdate();
            
            
		}catch (Exception e) {
           e.printStackTrace();
		}finally {
			JDBCUtil.close(null, ps, con);
		}

		return result;
	}
	
	
	/*
	INSERT INTO Book(bookid, bookname, publisher, price)
    VALUES(?,?,?,?);
	
	delete from book where bookid = ?
	
	UPDATE Book SET price = ?  WHERE bookid = ?;
	
	select * from (
   select rownum row#,bookid, bookname, publisher, price
   from (select * from Book order by bookid)
    ) where row# between ? and ?
	
	select * from Book order by bookid
	
	select count(*) from Book
	
	
	*/
	
	
}
