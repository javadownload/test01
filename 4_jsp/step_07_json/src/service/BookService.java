package service;

import java.util.List;

import vo.BookVO;

public interface BookService {
	
	 List<BookVO> bookList();
	 void bookAdd(BookVO vo) throws Exception;
	 void deleteBook(int bookno);
	 void updateBook(BookVO vo);
	 
	 List<BookVO> searchBook(String condition,String keyword);
}
