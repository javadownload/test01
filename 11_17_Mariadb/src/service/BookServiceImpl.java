package service;

import java.util.List;

import dao.BookDAO_Mariadb;
import vo.BookVO;

public class BookServiceImpl implements BookService{
	
	private BookDAO_Mariadb dao = null;
	
	public BookServiceImpl() {
		super();
	}
	public BookServiceImpl(BookDAO_Mariadb dao) {
		super();
		this.dao = dao;
	}
	public BookDAO_Mariadb getDao() {
		return dao;
	}
	public void setDao(BookDAO_Mariadb dao) {
		this.dao = dao;
	}
	
	@Override
	public List<BookVO> bookList() {
		return dao.bookList();
	}
	@Override
	public void bookAdd(BookVO vo) {
		dao.bookAdd(vo);
	}
	@Override
	public void bookDelete(int bookno) {
		dao.bookDelete(bookno);
	}
	@Override
	public void bookUpdate(BookVO vo) {
	    dao.bookUpdate(vo);	
	}
	@Override
	public BookVO getBook(int bookno) {
		return dao.getBook(bookno);
	}
	@Override
	public List<BookVO> searchBook(String condition, String keyword) {
		return dao.bookSearch(condition, keyword);
	}

}
