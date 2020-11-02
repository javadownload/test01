package dao;

import java.util.List;

import vo.BookVO;

public interface BookDAO {
	 List<BookVO> bookList();
	 void bookAdd(BookVO vo) throws Exception;
	 void deleteBook(int bookno);
	 void updateBook(BookVO vo);
	 public int count();
	 List<BookVO> searchBook(String condition,String keyword);
}
