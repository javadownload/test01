package service;

import java.util.List;

import vo.BookVO;

public interface BookService {
	
	 List<BookVO> bookList();
	 void bookAdd(BookVO vo);
	 void deleteBook(int bookno);
	 void updateBook(BookVO vo);
	 BookVO getBook(int no) ;

	 List<BookVO> searchBook(String condition,String keyword);
}
