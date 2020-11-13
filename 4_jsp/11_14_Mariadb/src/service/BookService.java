package service;

import java.util.List;

import vo.BookVO;

public interface BookService {

	public List<BookVO> bookList();

	public void bookAdd(BookVO vo);
	
	public void bookDelete(int bookno);
	
	public void bookUpdate(BookVO vo);
	
	public BookVO getBook(int bookno);
	
	public List<BookVO> searchBook(String condition , String keyword);

}
