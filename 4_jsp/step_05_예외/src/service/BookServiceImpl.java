package service;

import java.util.List;

import dao.BookDAO;
import vo.BookVO;

public class BookServiceImpl implements BookService{
    private BookDAO dao = null;
	
	public BookServiceImpl() {	}
	public BookServiceImpl(BookDAO dao) {
		this.dao = dao;
	}
	public BookDAO getDao() {
		return dao;
	}
	public void setDao(BookDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public List<BookVO> bookList() {
		return dao.bookList();
	}
	@Override
	public void bookAdd(BookVO vo) throws Exception{
		dao.bookAdd(vo);
	}
	@Override
	public void deleteBook(int bookno) {
		// TODO Auto-generated method stub
		dao.deleteBook(bookno);
	}
	@Override
	public void updateBook(BookVO vo) {
		// TODO Auto-generated method stub
		dao.updateBook(vo);
	}
	@Override
	public List<BookVO> searchBook(String condition, String keyword) {
		// TODO Auto-generated method stub
		return dao.searchBook(condition, keyword);
	}

}
