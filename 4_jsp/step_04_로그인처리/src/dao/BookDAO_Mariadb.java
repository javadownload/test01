package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.BookVO;

public class BookDAO_Mariadb implements BookDAO{

	@Override
	public List<BookVO> bookList() {
		List<BookVO> list = new ArrayList<BookVO>();
		String sql = "select * from book order by bookno desc";
		             //"select * from book order by bookno desc limit 1 , 3"

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BookVO b = new BookVO();
				b.setBookno(rs.getInt("bookno"));
				b.setPrice(rs.getInt("price"));
                b.setPublisher(rs.getString("publisher"));
                b.setTitle(rs.getString("title"));
                list.add(b);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			JDBCUtil.close(con , ps , rs);
		}
		return list;
	}

	@Override
	public void bookAdd(BookVO vo) throws Exception{
        String sql = "insert into book (title,publisher,price) " + 
        		"values (?,?,?)";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getPublisher());
			ps.setInt(3, vo.getPrice());
			
			int i = ps.executeUpdate();
			if(i == 0) throw new Exception("등록 실패");
		} catch (Exception e) {
			//System.out.println(e);
			throw e;
		}finally {
			JDBCUtil.close(con , ps , rs);
		}
	}
	@Override
	public BookVO getBook(int no) {
		String sql = "select * from book where bookno = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BookVO b = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			while(rs.next()) {
				b = new BookVO();
				b.setBookno(rs.getInt("bookno"));
				b.setPrice(rs.getInt("price"));
                b.setPublisher(rs.getString("publisher"));
                b.setTitle(rs.getString("title"));
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			JDBCUtil.close(con , ps , rs);
		}
		return b;
	}

	@Override
	public void deleteBook(int bookno) {
        String sql = "delete from book where bookno = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, bookno);
			
			int i = ps.executeUpdate();
			if(i == 0) throw new Exception("삭제 오류 발생 ");
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			JDBCUtil.close(con , ps , rs);
		}		
	}

	@Override
	public void updateBook(BookVO vo) {
		// TODO Auto-generated method stub
		String sql  = "UPDATE Book SET price = ?  WHERE bookno = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 세팅
			ps.setInt(1, vo.getPrice());
			ps.setInt(2, vo.getBookno());
			
			//실행 및 결과값 핸들링
            result = ps.executeUpdate();
            
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			JDBCUtil.close(con , ps , rs);
		}
		
	}

	@Override
	public List<BookVO> searchBook(String condition, String keyword) {
		List<BookVO> list = new ArrayList<BookVO>();

        String sql = "select * from Book where "+condition+" like ?  order by bookno desc";
        //select * from Book where publisher like '%명지%' order by bookno desc;
        

       // System.out.println(sql);
//        String sql =
//        	 	"select * from ( "+
//        	 	"select rownum row#,bookid, bookname, publisher, price , img "+
//        	 	"from (select * from Book where "+condition+" like '%'||?||'%' " +"order by bookid desc) "+ 
//        	 	 ") where row# between ? and ? ";
//        	 					    		
        	 			
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1,"%"+ keyword+"%");
			
			rs = ps.executeQuery();
            while (rs.next()) {
				BookVO vo = new BookVO();
				vo.setBookno(rs.getInt("bookno"));
				vo.setTitle(rs.getString("title"));
				vo.setPublisher(rs.getString("publisher"));
				vo.setPrice(rs.getInt("price"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			JDBCUtil.close(con , ps , rs);
		}
		
		return list;
	}
}
